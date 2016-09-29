package app.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.dto.RestObject;

@RestController
public class ApiController {

  private static Logger logger = LoggerFactory.getLogger(ApiController.class);

  @PostMapping("/api")
  public RestObject<String> test(@RequestParam String action,
      @RequestBody Map<String,Object> body) {
    logger.debug("action: {}", action);
    logger.debug("body: {}", body);
    return new RestObject<String>(false, "abc");
  }

}
