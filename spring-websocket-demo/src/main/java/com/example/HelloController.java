package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

  private static Logger logger = LoggerFactory.getLogger(HelloController.class);

  @Autowired
  SimpMessageSendingOperations messagingTemplate;

  @RequestMapping("")
  public String index() {
    return "index";
  }

  @MessageMapping("/hello")
  @SendToUser("/queue/hello")
  public String testMessage(String name) {
    return "Oh ... " + name + ", welcome!";
  }

  @Scheduled(fixedRate = 5000)
  @SendToUser("/queue/hello")
  public void sendMessages() {
    logger.info("sent a message");
    messagingTemplate.convertAndSendToUser("test", "/queue/horray", "Horray, welcome!");
  }
}
