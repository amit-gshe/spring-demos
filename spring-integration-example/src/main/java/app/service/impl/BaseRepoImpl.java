package app.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import app.service.BaseRepo;

public class BaseRepoImpl<T,ID extends Serializable> implements BaseRepo<T, ID >{

  private Class<T> type;
  
  @Autowired
  EntityManager entityManager;
  
  @Override
  public T save(T object) {
    entityManager.persist(object);
    return object;
  }

  @Override
  public void delete(ID id) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<T> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public T findOne(ID id) {
    return (T) entityManager.find(getType(), id);
  }
  
  @SuppressWarnings("unchecked")
  public Class<T> getType() {

    if (this.type == null) {

        ParameterizedType parameterizedType = (ParameterizedType) (this
                .getClass().getGenericSuperclass());

//        while (!(parameterizedType instanceof ParameterizedType)) {
//            parameterizedType = (ParameterizedType) parameterizedType
//                    .getClass().getGenericSuperclass();
//        }

        this.type = (Class<T>) parameterizedType.getActualTypeArguments()[0];

    }

    return this.type;
}

}
