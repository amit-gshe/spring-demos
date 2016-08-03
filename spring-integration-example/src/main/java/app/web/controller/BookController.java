package app.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/user")
	public String user(){
		return "This is content that can be accessed by normal user";
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/admin")
	public String admin(){
		return "This is the protected content that can only be accessed by admin";
	}
	
}
