package app.service;

import java.io.Serializable;
import java.util.List;

public interface BaseRepo<T, ID extends Serializable> {
  
  T save(T object);
  T findOne(ID id);
  void delete(ID id);
  List<T> findAll();
}
