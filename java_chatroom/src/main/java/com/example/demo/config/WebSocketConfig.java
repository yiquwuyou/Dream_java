package com.example.demo.config;

import com.example.demo.Controller.TestWebSocketController;
import com.example.demo.Controller.WebSocketController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.annotation.Resource;

@Configuration
@EnableWebSocket   // 让 spring 知道这个类开启了 websocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private TestWebSocketController testWebSocketController;

    @Autowired
    private WebSocketController webSocketController;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 通过这个方法，把刚才创建好的 Handler 类给注册到具体的路径上
        // 此时当浏览器， websocket 的请求路径是 "/test" 的时候，就会调用到 TestWebSocketAPI 这个类里的方法
//        registry.addHandler(testWebSocketController, "/test");
        registry.addHandler(webSocketController, "/WebSocketMessage")
                // 通过注册这个特定的 HttpSession 拦截器，就可以把用户给 HttpSession 中添加的 Attribute 键值对
                // 往我们的 WebSocketSession 里也添加一份
                .addInterceptors(new HttpSessionHandshakeInterceptor());
    }
}
