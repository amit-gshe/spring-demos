package app.service;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import app.domain.Reader;

public interface ReaderService extends CrudRepository<Reader, Long>,QueryDslPredicateExecutor<Reader> {

  Reader findByName(String name);
  
}
