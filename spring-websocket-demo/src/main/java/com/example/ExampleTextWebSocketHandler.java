package com.example;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ExampleTextWebSocketHandler extends TextWebSocketHandler {

  private Logger logger = LoggerFactory.getLogger(ExampleTextWebSocketHandler.class);
  Map<String, WebSocketSession> sessionMap = new HashMap<>();

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    logger.info("connection {} established", session.getId());
    sessionMap.put(session.getId(), session);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    logger.info("received message: {}", message.getPayload());
    session.sendMessage(new TextMessage("hello client"));
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    logger.info("connection {} closed!", session.getId());
    sessionMap.remove(session.getId());
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    session.close();
    sessionMap.remove(session.getId());
  }

  @Scheduled(fixedRate = 5000)
  public void broadcast() throws Exception {
    for (String id : sessionMap.keySet()) {
      sessionMap.get(id).sendMessage(new TextMessage(new Date().toString()));
    }
  }

}
