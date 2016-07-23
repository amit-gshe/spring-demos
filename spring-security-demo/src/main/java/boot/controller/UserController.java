package boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import boot.service.UserService;

@RestController
public class UserController {

	UserService userService;

	@RequestMapping("/signin")
	public void signin(@RequestParam String username, @RequestParam String password) {
		userService.signin(username, password);
	}

	@Secured("ROLE_USER")
	@RequestMapping("/oh")
	public String protectedMethod(){
		return "this is the protected content should not be accessed by a non-login user;";
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
