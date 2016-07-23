package boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import boot.service.UserService;

@Controller
public class UserController {

	UserService userService;

	@RequestMapping(path="/signin",method=RequestMethod.POST)
	public String signin(@RequestParam String username, @RequestParam String password) {
		userService.signin(username, password);
		return "redirect:/welcome";
	}

	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/user")
	@ResponseBody
	public String user(){
		return "this is the protected content should not be accessed by a normal user;";
	}
	
	@RequestMapping("/admin")
	@ResponseBody
	public String admin(){
		return "this is the protected content should only be accessed by the admin;";
	}
	
	@RequestMapping("/welcome")
	public String welcome(){
		return "welcome";
	}
	
	@RequestMapping("/protected")
	public void forbidden(){
		userService.protectedMethod();
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
