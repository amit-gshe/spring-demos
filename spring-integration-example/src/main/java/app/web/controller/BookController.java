package app.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.config.RealtimeAccessDecisionManager;
import app.domain.Book;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class BookController {

  @Autowired
  RealtimeAccessDecisionManager accessDecisionManager;

  @Secured({"ROLE_ADMIN"})
  @GetMapping("/admin")
  public String admin() {
    return "This is the protected content that can only be accessed by admin";
  }
  
  @GetMapping("/books")
  public List<Book> bookList() {
    Book b1 = new Book("生命的季节", "罗素‧怀斯特", 26);
    Book b2 = new Book("神奇的人体生物钟", "蒂尔‧伦内伯格", 33);
    return Arrays.asList(b1, b2);
  }

  @PostMapping("/book")
  @ApiOperation("Add a book")
  @ApiResponses({@ApiResponse(code=400,message="参数错误")})
  public Book add(@RequestBody Book book) {
    return book;
  }

  @GetMapping("/grant")
  public String grantAdmin(){
    accessDecisionManager.grantAdminAuthority("admin");
    return "you now are the admin";
  }

}
