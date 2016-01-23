package boot.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletRequestAttributes;

/*
 * @Controller public class CustomErrorController implements ErrorController {
 * 
 * private static final String PATH = "/error";
 * 
 * @Autowired private ErrorAttributes errorAttributes;
 * 
 * @RequestMapping(value = PATH)
 * 
 * @ResponseBody public Map<String, Object> error(HttpServletRequest request) {
 * return errorAttributes.getErrorAttributes(new
 * ServletRequestAttributes(request), true); }
 * 
 * @Override public String getErrorPath() { return PATH; }
 * 
 * }
 */