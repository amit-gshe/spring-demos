package app.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.domain.Book;

@Secured("ROLE_USER")
@RestController
public class BookController {

	@RequestMapping("/user")
	public String user(){
		return "This is content that can be accessed by normal user";
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/admin")
	public String admin(){
		return "This is the protected content that can only be accessed by admin";
	}
	
	
	@RequestMapping("/books")
	public List<Book> bookList(){
		Book b1 = new Book("生命的季节", "罗素‧怀斯特", 26);
		Book b2 = new Book("神奇的人体生物钟", "蒂尔‧伦内伯格", 33);
		return Arrays.asList(b1,b2);
	}
	
}
