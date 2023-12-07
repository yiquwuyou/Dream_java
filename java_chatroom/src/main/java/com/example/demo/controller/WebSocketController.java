package com.example.demo.controller;

import com.example.demo.component.OnlineUserManager;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.mapper.MessageSessionMapper;
import com.example.demo.model.*;
import com.example.demo.service.FriendService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@Api(tags = "websocket")
@CrossOrigin(origins="*")
public class WebSocketController extends TextWebSocketHandler {

    @Autowired
    private OnlineUserManager onlineUserManager;

    @Autowired
    private MessageSessionMapper messageSessionMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    // String 自带的，所以不需要注入
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("[ WebSocketController] 连接成功！    999999");
        // 前面注册了拦截器，就可以让每个往 HttpSession 中加入的 Attribute 在 WebSocketSession 也被加入一份了
        // 此处与 http 的 getAttributes 不太一样  http 中直接调用 getAttributes 即可
        // 此处 getAttributes 这样的方法返回的是一个 Map
        // 然后再通过 Map 的 get 方法获取到 key 对应的 value4
        // 从 token 中获取用户信息
        User user = new User();
        user.setUserId(UserJwt.getUserId());
        user.setUsername(UserJwt.getUsername());
        log.info("获取到的 userId：" + user.getUserId());
        // 把这个键值对给存起来
        onlineUserManager.online(user.getUserId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("[ WebSocketController] 收到消息！" + message.toString());
        // 1、先获取到当前用户的信息，后续要进行消息转发啥的
        // 从 token 中获取用户信息
        User user = new User();
        user.setUserId(UserJwt.getUserId());
        user.setUsername(UserJwt.getUsername());
        if (user == null) {
            System.out.println("[WebSocketAPI] user == null! 未登录用户，无法进行消息转发");
            return;
        }
        // 2、针对请求进行解析，把 json 格式的字符串，转成一个 java 中的对象
        // 使用一个叫做 objectMapper 的对象来进行反序列化的操作
        // objectMapper 是一个能够将 JSON 字符串或数据转换为具体的 Java 类型对象的工具
        // 它接受两个参数：
        // 要解析的 JSON 数据字符串（在这里是 message.getPayload()）和
        // 要转换为的目标 Java 类型（在这里是 MessageRequest.class）。
        MessageRequest request = objectMapper.readValue(message.getPayload(), MessageRequest.class);
        if (request.getType().equals("message")) {
            // 就进行消息转发
            transferMessage(user, request);
        } else if (request.getType().equals("friend")) {
            // 发送加好友请求
            transferfriend(user, request);
        } else if (request.getType().equals("agreefriend")) {
            // 同意加好友
            transferAgreeFriend(user, request);
        } else if (request.getType().equals("refusefriend")) {
            // 拒绝加好友
            transferRefuseFriend(user, request);
        } else {
            System.out.println("[WebSocketAPI] request.type 有误！" + message.getPayload());
        }
    }

    // 通过这个方法来完成加群聊的查询，转发，存储操作
    // 通过这个方法来进行同意加入群聊后的数据处理
    // 通过这个方法来进行拒绝加入群聊后的数据处理
    // 通过这个方法进行同意好友后的数据处理
    private void transferAgreeFriend(User fromUser, MessageRequest request) throws IOException {
        // 1、先构造一个待转发的响应对象。 MessageResponse
        MessageResponse response = new MessageResponse();
        response.setType("agreefriend");
        response.setFromId(fromUser.getUserId());
        response.setFromName(fromUser.getUsername());
        response.setToName(request.getToUsername());
        response.setIsAgree(1);
        response.setToId(userService.selectIdByUserName(request.getToUsername()));
        // 把这个 java 对象转成 json 格式字符串
        String responseJson = objectMapper.writeValueAsString(response);
        log.info("[transferMessage] responseJson:" + responseJson);
        // 修改add_friend中的数据
        AddFriend addFriend = new AddFriend();
        BeanUtils.copyProperties(response, addFriend);
        friendService.updateAgreeFriend(addFriend);
        // 添加friend表中的数据
        friendService.insertfriend(addFriend.getFromId(), addFriend.getToId());
        // 转发
        WebSocketSession webSocketSession1 = onlineUserManager.getSession(response.getFromId());
        WebSocketSession webSocketSession2 = onlineUserManager.getSession(response.getToId());
        if (webSocketSession1 != null) {
            // 如果该用户在线，则发送
            webSocketSession1.sendMessage(new TextMessage(responseJson));
        }
        if (webSocketSession2 != null) {
            // 如果该用户在线，则发送
            webSocketSession2.sendMessage(new TextMessage(responseJson));
        }
        // 前端接收同意后，主动发起http请求，重加载好友列表

    }
    // 通过这个方法进行拒绝好友后的数据处理
    private void transferRefuseFriend(User fromUser, MessageRequest request) throws IOException {
        // 1、先构造一个待转发的响应对象。 MessageResponse
        MessageResponse response = new MessageResponse();
        response.setType("refusefriend");
        response.setFromId(fromUser.getUserId());
        response.setFromName(fromUser.getUsername());
        response.setToName(request.getToUsername());
        response.setIsAgree(0);
        response.setToId(userService.selectIdByUserName(request.getToUsername()));
        // 把这个 java 对象转成 json 格式字符串
        String responseJson = objectMapper.writeValueAsString(response);
        log.info("[transferMessage] responseJson:" + responseJson);
        // 转发
        WebSocketSession webSocketSession1 = onlineUserManager.getSession(response.getFromId());
        WebSocketSession webSocketSession2 = onlineUserManager.getSession(response.getToId());
        if (webSocketSession1 != null) {
            // 如果该用户在线，则发送
            webSocketSession1.sendMessage(new TextMessage(responseJson));
        }
        if (webSocketSession2 != null) {
            // 如果该用户在线，则发送
            webSocketSession2.sendMessage(new TextMessage(responseJson));
        }
    }
    // 通过这个方法来完成加好友信息的查询，转发，存储操作
    private void transferfriend(User fromUser, MessageRequest request) throws IOException {
        // 1、先构造一个待转发的响应对象。 MessageResponse
        MessageResponse response = new MessageResponse();
        response.setType("friend");
        response.setFromId(fromUser.getUserId());
        response.setFromName(fromUser.getUsername());
        response.setToName(request.getToUsername());
        response.setIsAgree(2);
        response.setToId(userService.selectIdByUserName(request.getToUsername()));
        // 把这个 java 对象转成 json 格式字符串
        String responseJson = objectMapper.writeValueAsString(response);
        log.info("[transferMessage] responseJson:" + responseJson);
        // 存储
        AddFriend addFriend = new AddFriend();
        BeanUtils.copyProperties(response, addFriend);
        friendService.addFriendAn(addFriend);
        // 转发
        WebSocketSession webSocketSession1 = onlineUserManager.getSession(response.getFromId());
        WebSocketSession webSocketSession2 = onlineUserManager.getSession(response.getToId());
        if (webSocketSession1 != null) {
            // 如果该用户在线，则发送
            webSocketSession1.sendMessage(new TextMessage(responseJson));
        }
        if (webSocketSession2 != null) {
            // 如果该用户在线，则发送
            webSocketSession2.sendMessage(new TextMessage(responseJson));
        }
    }
    // 通过这个方法来完成消息实际的转发操作
    // 第一个参数就表示这个要转发的消息，是从谁那里来的
    private void transferMessage(User fromUser, MessageRequest request) throws IOException {
        // 1、先构造一个待转发的响应对象。 MessageResponse
        MessageResponse response = new MessageResponse();
        response.setType("message");   // 这里不设置也行，默认就是 message
        response.setFromId(fromUser.getUserId());
        response.setFromName(fromUser.getUsername());
        response.setSessionId(request.getSessionId());
        response.setContent(request.getContent());
        // 把这个 java 对象转成 json 格式字符串
        String responseJson = objectMapper.writeValueAsString(response);
        System.out.println("[transferMessage] responseJson:" + responseJson);
        // 2、根据请求中的 sessionId，获取到这个 MessageSession 里都有哪些用户，通过查询数据库就知道了
        List<Friend> friends = messageSessionMapper.getFriendsBySessionId(request.getSessionId(), fromUser.getUserId());
        // 此处注意！！！上述数据库查询，会把当前发消息的用户给排除掉，而最终转发的时候，则需要也把发送消息的人也发一次
        // 把当前用户也添加到上述 List 里面
        Friend myself = new Friend();
        myself.setFriendId(fromUser.getUserId());
        myself.setFriendName(fromUser.getUsername());
        friends.add(myself);
        // 3、循环遍历上述的这个列表，给列表中的每个用户都发一份响应信息
        //    注意：这里除了给查询到的好友们发，也要给自己也发一个，方便实现在自己的客户端上显示自己发送的信息
        //    注意：一个会话中，可能有多个用户（群聊），虽然客户端是没有支持群聊的（前端写起来相应麻烦），后端无论是 API 还是 数据库
        //    都是支持群聊的，此处的转发逻辑也一样让他支持群聊
        for (Friend friend : friends) {
            //    知道了每个用户的 userId，进一步的查询刚才准备好的 OnlineUserMapper，就知道了对应的 WebSocketSession
            //    从而进行转发消息
            WebSocketSession webSocketSession = onlineUserManager.getSession(friend.getFriendId());
            if (webSocketSession == null) {
                // 如果该用户未在线，则不发送
                continue;
            }
            webSocketSession.sendMessage(new TextMessage(responseJson));
        }
        // 4、转发的消息，还需要放到数据库里，后续用户如果下线之后，重新上线，还可以通过历史消息的方式拿到之前的消息
        //       需要往 message 表中写入一条记录
        Message message = new Message();
        message.setFromId(fromUser.getUserId());
        message.setSessionId(request.getSessionId());
        message.setContent(request.getContent());
        // 像自增主键，还有时间这样的属性，都可以让 SQL 在数据库中生成
        messageMapper.add(message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("[ WebSocketController] 连接异常！" + exception.toString());

        // 从 token 中获取用户信息
        User user = new User();
        user.setUserId(UserJwt.getUserId());
        user.setUsername(UserJwt.getUsername());
        if (user == null) {
            return;
        }
        onlineUserManager.offline(user.getUserId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("[ WebSocketController] 连接关闭！" + status.toString());
        // 从 token 中获取用户信息
        User user = new User();
        user.setUserId(UserJwt.getUserId());
        user.setUsername(UserJwt.getUsername());
        if (user == null) {
            return;
        }
        onlineUserManager.offline(user.getUserId(), session);
    }

//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        System.out.println("[ WebSocketController] 连接成功！    999999");
//        System.out.println("333");        // 前面注册了拦截器，就可以让每个往 HttpSession 中加入的 Attribute 在 WebSocketSession 也被加入一份了
//        // 此处与 http 的 getAttributes 不太一样  http 中直接调用 getAttributes 即可
//        // 此处 getAttributes 这样的方法返回的是一个 Map
//        // 然后再通过 Map 的 get 方法获取到 key 对应的 value4
//        User user = (User) session.getAttributes().get("user");
//        System.out.println("获取到的 userId：" + user.getUserId());
//        // 把这个键值对给存起来
//        onlineUserManager.online(user.getUserId(), session);
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        System.out.println("[ WebSocketController] 收到消息！" + message.toString());
//        // 1、先获取到当前用户的信息，后续要进行消息转发啥的
//        User user = (User) session.getAttributes().get("user");
//        if (user == null) {
//            System.out.println("[WebSocketAPI] user == null! 未登录用户，无法进行消息转发");
//            return;
//        }
//        // 2、针对请求进行解析，把 json 格式的字符串，转成一个 java 中的对象
//        // 使用一个叫做 objectMapper 的对象来进行反序列化的操作
//        // objectMapper 是一个能够将 JSON 字符串或数据转换为具体的 Java 类型对象的工具
//        // 它接受两个参数：
//        // 要解析的 JSON 数据字符串（在这里是 message.getPayload()）和
//        // 要转换为的目标 Java 类型（在这里是 MessageRequest.class）。
//        MessageRequest request = objectMapper.readValue(message.getPayload(), MessageRequest.class);
//        if (request.getType().equals("message")) {
//            // 就进行消息转发
//            transferMessage(user, request);
//        } else if (request.getType().equals("friend")) {
//            // 发送加好友请求
//            transferfriend(user, request);
//        } else if (request.getType().equals("agreefriend")) {
//            // 同意加好友
//            transferAgreeFriend(user, request);
//        } else if (request.getType().equals("refusefriend")) {
//            // 拒绝加好友
//            transferRefuseFriend(user, request);
//        } else {
//            System.out.println("[WebSocketAPI] request.type 有误！" + message.getPayload());
//        }
//    }
//
//    // 通过这个方法来完成加群聊的查询，转发，存储操作
//    // 通过这个方法来进行同意加入群聊后的数据处理
//    // 通过这个方法来进行拒绝加入群聊后的数据处理
//    // 通过这个方法进行同意好友后的数据处理
//    private void transferAgreeFriend(User fromUser, MessageRequest request) throws IOException {
//        // 1、先构造一个待转发的响应对象。 MessageResponse
//        MessageResponse response = new MessageResponse();
//        response.setType("agreefriend");
//        response.setFromId(fromUser.getUserId());
//        response.setFromName(fromUser.getUsername());
//        response.setToName(request.getToUsername());
//        response.setIsAgree(1);
//        response.setToId(userService.selectIdByUserName(request.getToUsername()));
//        // 把这个 java 对象转成 json 格式字符串
//        String responseJson = objectMapper.writeValueAsString(response);
//        log.info("[transferMessage] responseJson:" + responseJson);
//        // 修改add_friend中的数据
//        AddFriend addFriend = new AddFriend();
//        BeanUtils.copyProperties(response, addFriend);
//        friendService.updateAgreeFriend(addFriend);
//        // 添加friend表中的数据
//        friendService.insertfriend(addFriend.getFromId(), addFriend.getToId());
//        // 转发
//        WebSocketSession webSocketSession1 = onlineUserManager.getSession(response.getFromId());
//        WebSocketSession webSocketSession2 = onlineUserManager.getSession(response.getToId());
//        if (webSocketSession1 != null) {
//            // 如果该用户在线，则发送
//            webSocketSession1.sendMessage(new TextMessage(responseJson));
//        }
//        if (webSocketSession2 != null) {
//            // 如果该用户在线，则发送
//            webSocketSession2.sendMessage(new TextMessage(responseJson));
//        }
//        // 前端接收同意后，主动发起http请求，重加载好友列表
//
//    }
//    // 通过这个方法进行拒绝好友后的数据处理
//    private void transferRefuseFriend(User fromUser, MessageRequest request) throws IOException {
//        // 1、先构造一个待转发的响应对象。 MessageResponse
//        MessageResponse response = new MessageResponse();
//        response.setType("refusefriend");
//        response.setFromId(fromUser.getUserId());
//        response.setFromName(fromUser.getUsername());
//        response.setToName(request.getToUsername());
//        response.setIsAgree(0);
//        response.setToId(userService.selectIdByUserName(request.getToUsername()));
//        // 把这个 java 对象转成 json 格式字符串
//        String responseJson = objectMapper.writeValueAsString(response);
//        log.info("[transferMessage] responseJson:" + responseJson);
//        // 转发
//        WebSocketSession webSocketSession1 = onlineUserManager.getSession(response.getFromId());
//        WebSocketSession webSocketSession2 = onlineUserManager.getSession(response.getToId());
//        if (webSocketSession1 != null) {
//            // 如果该用户在线，则发送
//            webSocketSession1.sendMessage(new TextMessage(responseJson));
//        }
//        if (webSocketSession2 != null) {
//            // 如果该用户在线，则发送
//            webSocketSession2.sendMessage(new TextMessage(responseJson));
//        }
//    }
//    // 通过这个方法来完成加好友信息的查询，转发，存储操作
//    private void transferfriend(User fromUser, MessageRequest request) throws IOException {
//        // 1、先构造一个待转发的响应对象。 MessageResponse
//        MessageResponse response = new MessageResponse();
//        response.setType("friend");
//        response.setFromId(fromUser.getUserId());
//        response.setFromName(fromUser.getUsername());
//        response.setToName(request.getToUsername());
//        response.setIsAgree(2);
//        response.setToId(userService.selectIdByUserName(request.getToUsername()));
//        // 把这个 java 对象转成 json 格式字符串
//        String responseJson = objectMapper.writeValueAsString(response);
//        log.info("[transferMessage] responseJson:" + responseJson);
//        // 存储
//        AddFriend addFriend = new AddFriend();
//        BeanUtils.copyProperties(response, addFriend);
//        friendService.addFriendAn(addFriend);
//        // 转发
//        WebSocketSession webSocketSession1 = onlineUserManager.getSession(response.getFromId());
//        WebSocketSession webSocketSession2 = onlineUserManager.getSession(response.getToId());
//        if (webSocketSession1 != null) {
//            // 如果该用户在线，则发送
//            webSocketSession1.sendMessage(new TextMessage(responseJson));
//        }
//        if (webSocketSession2 != null) {
//            // 如果该用户在线，则发送
//            webSocketSession2.sendMessage(new TextMessage(responseJson));
//        }
//    }
//    // 通过这个方法来完成消息实际的转发操作
//    // 第一个参数就表示这个要转发的消息，是从谁那里来的
//    private void transferMessage(User fromUser, MessageRequest request) throws IOException {
//        // 1、先构造一个待转发的响应对象。 MessageResponse
//        MessageResponse response = new MessageResponse();
//        response.setType("message");   // 这里不设置也行，默认就是 message
//        response.setFromId(fromUser.getUserId());
//        response.setFromName(fromUser.getUsername());
//        response.setSessionId(request.getSessionId());
//        response.setContent(request.getContent());
//        // 把这个 java 对象转成 json 格式字符串
//        String responseJson = objectMapper.writeValueAsString(response);
//        System.out.println("[transferMessage] responseJson:" + responseJson);
//        // 2、根据请求中的 sessionId，获取到这个 MessageSession 里都有哪些用户，通过查询数据库就知道了
//        List<Friend> friends = messageSessionMapper.getFriendsBySessionId(request.getSessionId(), fromUser.getUserId());
//        // 此处注意！！！上述数据库查询，会把当前发消息的用户给排除掉，而最终转发的时候，则需要也把发送消息的人也发一次
//        // 把当前用户也添加到上述 List 里面
//        Friend myself = new Friend();
//        myself.setFriendId(fromUser.getUserId());
//        myself.setFriendName(fromUser.getUsername());
//        friends.add(myself);
//        // 3、循环遍历上述的这个列表，给列表中的每个用户都发一份响应信息
//        //    注意：这里除了给查询到的好友们发，也要给自己也发一个，方便实现在自己的客户端上显示自己发送的信息
//        //    注意：一个会话中，可能有多个用户（群聊），虽然客户端是没有支持群聊的（前端写起来相应麻烦），后端无论是 API 还是 数据库
//        //    都是支持群聊的，此处的转发逻辑也一样让他支持群聊
//        for (Friend friend : friends) {
//            //    知道了每个用户的 userId，进一步的查询刚才准备好的 OnlineUserMapper，就知道了对应的 WebSocketSession
//            //    从而进行转发消息
//            WebSocketSession webSocketSession = onlineUserManager.getSession(friend.getFriendId());
//            if (webSocketSession == null) {
//                // 如果该用户未在线，则不发送
//                continue;
//            }
//            webSocketSession.sendMessage(new TextMessage(responseJson));
//        }
//        // 4、转发的消息，还需要放到数据库里，后续用户如果下线之后，重新上线，还可以通过历史消息的方式拿到之前的消息
//        //       需要往 message 表中写入一条记录
//        Message message = new Message();
//        message.setFromId(fromUser.getUserId());
//        message.setSessionId(request.getSessionId());
//        message.setContent(request.getContent());
//        // 像自增主键，还有时间这样的属性，都可以让 SQL 在数据库中生成
//        messageMapper.add(message);
//    }
//
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//        System.out.println("[ WebSocketController] 连接异常！" + exception.toString());
//
//        User user = (User) session.getAttributes().get("user");
//        if (user == null) {
//            return;
//        }
//        onlineUserManager.offline(user.getUserId(), session);
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        System.out.println("[ WebSocketController] 连接关闭！" + status.toString());
//        User user = (User) session.getAttributes().get("user");
//        if (user == null) {
//            return;
//        }
//        onlineUserManager.offline(user.getUserId(), session);
//    }
}
