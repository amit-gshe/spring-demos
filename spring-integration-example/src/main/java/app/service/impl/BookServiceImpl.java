package app.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.domain.Book;
import app.service.BookRepo;

@Service("bookService")
@Transactional
public class BookServiceImpl extends BaseRepoImpl<Book, Long> implements BookRepo {
  
}
