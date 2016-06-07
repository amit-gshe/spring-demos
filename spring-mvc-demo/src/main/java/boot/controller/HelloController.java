package boot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thoughtworks.xstream.XStream;

@Controller
public class HelloController {

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
}
