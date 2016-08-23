package app.service;

import org.springframework.data.repository.CrudRepository;

import app.domain.Reader;

public interface ReaderService extends CrudRepository<Reader, Long> {

}
