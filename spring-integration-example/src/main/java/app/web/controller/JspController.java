package app.web.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

@Controller
public class JspController {

  private static Logger logger = LoggerFactory.getLogger(JspController.class);

  @Autowired
  ViewResolver viewResolver;

  @RequestMapping("/welcome")
  @ResponseBody
  public String welcome(HttpServletRequest request) {
    try {
      Map<String, Object> model = new HashMap<>();

      MockHttpServletResponse response = new MockHttpServletResponse();
      View v = viewResolver.resolveViewName("welcome", Locale.getDefault());
      v.render(model, request, response);

      String result = response.getContentAsString();
      logger.info(result);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "welcome";
  }
  
  @GetMapping("/apis")
  public String apiCallTest(){
    return "api";
  }
}
