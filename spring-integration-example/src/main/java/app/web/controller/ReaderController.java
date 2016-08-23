package app.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import app.domain.Reader;
import app.service.ReaderService;

@RestController
public class ReaderController {

  ReaderService readerService;

  public ReaderController(ReaderService readerService) {
    this.readerService = readerService;
  }
  
  @GetMapping("/user/{id}")
  public Reader getAnReader(@PathVariable Long id){
    return readerService.findOne(id);
  }
  
}
