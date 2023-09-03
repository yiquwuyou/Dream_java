package com.example.demo.Controller;


import com.example.demo.mapper.MessageMapper;
import com.example.demo.mapper.MessageSessionMapper;
import com.example.demo.model.Friend;
import com.example.demo.model.MessageSession;
import com.example.demo.model.MessageSessionUserItem;
import com.example.demo.model.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class MessageSessionController {
    @Resource
    private MessageSessionMapper messageSessionMapper;

    @Resource
    private MessageMapper messageMapper;

    // 获取会话列表
    @GetMapping("/sessionList")
    @ResponseBody
    public Object getMessageSessionList(HttpServletRequest request){
        List<MessageSession> messageSessionList = new ArrayList<>();
        // 1、获取到当前用户的 userId (从 spring 的 session 中获取)
        HttpSession session = request.getSession(false);
        if (session == null) {
            System.out.println("[getMessageSessionList] session == null");
            return messageSessionList;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            System.out.println("[getMessageSessionList] user == null");
            return messageSessionList;
        }
        // 2、根据 userId 查询数据库，查出来有哪些会话id
        List<Integer> sessionIdlist = messageSessionMapper.getSessionIdsByUserId(user.getUserId());
        for (int sessionId : sessionIdlist) {
            MessageSession messageSession = new MessageSession();
            messageSession.setSessionId(sessionId);
            // 3、遍历会话id，查询出每个会话里涉及到的好友都有谁
            List<Friend> friends = messageSessionMapper.getFriendsBySessionId(sessionId, user.getUserId());
            messageSession.setFriends(friends);
            // 4、遍历会话id，查询出每个会话的最后一条信息
            // 有可能出现按照会话 id 查不到消息的情况（新创建的会话可能还没来得及发消息呢）
            String lastMessage = messageMapper.getLastMessageBySessionId(sessionId);
            if (lastMessage == null) {
                messageSession.setLastMessage("");
            } else {
                messageSession.setLastMessage(lastMessage);
            }
            messageSessionList.add(messageSession);
        }
        // 最终目标就是构造出一个 MessageSession 对象数组
        return messageSessionList;
    }

    // 如果没有会话，则创建会话 要对数据库进行以下操作
    @PostMapping("/session")
    @ResponseBody
    // 通过下面这个注解来让此方法引入到事务当中，变成类似于原子性的操作，更加安全   若中间出现异常，则会自动进行回滚
    @Transactional
    // 可以直接通过这个注解 @SessionAttribute("user") 来获取 session ，并放置到 user 对象里
    // 有缺点，不一定比上面那个原生的好
    public Object addMessageSession(int toUserId, @SessionAttribute("user") User user) {
        // 设置这个的目的是返回一个 json 字符串
        HashMap<String, Integer> resp = new HashMap<>();
        // 进行数据库的插入操作
        // 1、先给 message_session 表里插入记录，使用这个参数的目的是为了能够获取到会话的 sessionId
        //    换而言之， MessageSession 里的 friends 和 lastMessage 属性此处都用不上
        MessageSession messageSession = new MessageSession();
        messageSessionMapper.addMessageSession(messageSession);
        // 2、给 message_session_user 表插入记录
        MessageSessionUserItem item1 = new MessageSessionUserItem();
        item1.setSessionId(messageSession.getSessionId());
        item1.setUserId(user.getUserId());
        messageSessionMapper.addMessageSessionUser(item1);
        // 3、给 message_session_user 表插入记录
        MessageSessionUserItem item2 = new MessageSessionUserItem();
        item2.setSessionId(messageSession.getSessionId());
        item2.setUserId(toUserId);
        messageSessionMapper.addMessageSessionUser(item2);

        System.out.println("[addMessageSession] 新增会话成功！ sessionId=" + messageSession.getSessionId()
                + "userId1=" + user.getUserId() + "userId2=" + toUserId);

        resp.put("sessionId", messageSession.getSessionId());
        // 返回的对象是一个普通对象也可以， 或者是一个 Map 也可以，jackson 都能进行处理
        return resp;

    }
}
