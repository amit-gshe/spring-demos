package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

  @Autowired
  WebSocketHandler websocketHander;
  
  @Autowired
  HttpSessionHandshakeInterceptor httpSessionHandshakeInterceptor;
  
  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    
    registry.addHandler(websocketHander, "/ws").addInterceptors(httpSessionHandshakeInterceptor).withSockJS();
  }

}
