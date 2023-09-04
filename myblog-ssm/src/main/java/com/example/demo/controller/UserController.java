package com.example.demo.controller;

import com.example.demo.common.AjaxResult;
import com.example.demo.common.ApplicationVariable;
import com.example.demo.common.PasswordTools;
import com.example.demo.common.UserSessionTools;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;

// 所有的 url 尽量都用小写 因为linux严格区分大小写，但windows不区分，防止windows调试正常，linux出错的情况
@RestController
// 上面这个注解是 Controller 和 ResponseBody 两个注解的结合体
// Controller 作用为将修饰的 类 标记为 控制器，用于处理用户请求并生成响应
// ResponseBody 表示将方法的返回值 直接作为 响应体 返回给客户端 ，不再向前端直接返回一个界面（不加注解时是直接返回一个界面）
@RequestMapping("/user")
// RequestMapping 用于将请求映射到控制器上，分为类级别和方法级别
// 这里的请求简单理解为 url地址
public class UserController {

    // 该注解的意思是 根据修饰项自动实现 依赖注入
    // 依赖注入是一种设计模式
    // 在使用依赖注入的模式中，依赖关系被外部容器负责管理，组件只需要声明自己需要哪些依赖，而不需要主动创建或查找它们。 好处：松耦合，提高可维护性等
    @Autowired
    private UserService userService;
    // 注册
    @RequestMapping("/reg")
    // 这是一个统一的对象，返回的时候就要尽量返回这个统一的对象  保底策略能不用则不用，不够灵活
    public AjaxResult reg(UserInfo userInfo) {
        // 铁律：后端开发：永远不要相信前端！！！
        // 即便前端已经进行非空校验了，后端依然要进行非空校验
        // 1、进行非空判断
        // StringUtils.hasLength(userInfo.getUsername()) 相当于userInfo.getUsername()==null || userInfo.getUsername().equals("")
        // 既判断 null ，又判断空
        if(userInfo==null || !StringUtils.hasLength(userInfo.getUsername()) ||
                !StringUtils.hasLength(userInfo.getPassword()))
            return AjaxResult.fail(-1,"参数有误");
        // 2、调用 UserService 执行添加方法，并将返回的结果添加 AjaxResult.data 进行返回
        // 将密码进行加盐加密
        userInfo.setPassword(PasswordTools.encrypt(userInfo.getPassword()));
        int result = userService.reg(userInfo);
        return AjaxResult.success(result);
    }

    // 这里的 request 参数主要为为了获得用户的请求信息（账号密码等），然后存到 session（request的属性） 里
    @RequestMapping("/login")
    public AjaxResult login(String username, String password, HttpServletRequest request){
        // 这里不需要对 UserInfo 进行非空判断，主要原因是没传这个参数，
        // 其次来到这里UserInfo也不可能为空，且就算为空，判断用户名和密码也自带对 UserInfo 的判断
        if(!StringUtils.hasLength(username) ||
                !StringUtils.hasLength(password))
            return AjaxResult.fail(-1,"参数有误");
        // 根据用户名获取 UserInfo 对象
        UserInfo userInfo = userService.login(username);
        if(userInfo == null || userInfo.getId() <= 0)
            return AjaxResult.fail(-2,"用户名或密码输入错误！");

        if(!PasswordTools.decrypt(password, userInfo.getPassword()))
            return AjaxResult.fail(-2,"用户名或密码输入错误！");
        // 来到这里说明密码正确，可返回1
        // 将当前成功登录的用户信息存储到session 为拦截器做准备  因为有些后续的界面，如增减删除文章是需要确保用户在登录状态才可操作的
        HttpSession session = request.getSession();
        // 将 userinfo 储存到SESSION_KEY_USERINFO中，将SESSION_KEY_USERINFO作为属性名，
        // 后续可以在session.setAttribute里直接调用ApplicationVariable.SESSION_KEY_USERINFO
        session.setAttribute(ApplicationVariable.SESSION_KEY_USERINFO, userInfo);
        return AjaxResult.success(1);
    }

    // 退出
    // 此操作不需要操作数据库，所以只在控制器里写的有代码，mapper，Service等没有写相关代码
    @RequestMapping("/logout")
    public AjaxResult logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(ApplicationVariable.SESSION_KEY_USERINFO);
        return AjaxResult.success(1);
    }

    // 判断用户是否已登录
    @RequestMapping("/islogin")
    public AjaxResult isLogin(HttpServletRequest request){
        if(UserSessionTools.getLoginUser(request) == null) {
            // 未登录
            return AjaxResult.success(0);
        }
        return AjaxResult.success(1);
    }
}
