package boot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thoughtworks.xstream.XStream;

@Controller
public class HelloController{

  @Autowired
  XStream xStream;

  @RequestMapping("/hello")
  public String hello() {
    return "hello";
  }

  @SuppressWarnings("rawtypes")
  @RequestMapping(path = "/xml", method = RequestMethod.POST)
  @ResponseBody
  public Map request(@RequestBody String params) {
    return (Map) xStream.fromXML(params);
  }
  
  @RequestMapping("/erro")
  @ResponseBody
  public Map<String, Object> error(HttpServletRequest request){
	  Map<String, Object> map = new HashMap<String, Object>();
	  map.put("status", request.getAttribute("javax.servlet.error.status_code"));
      map.put("reason", request.getAttribute("javax.servlet.error.message"));
	  return map;
  }
}
