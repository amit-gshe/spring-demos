package com.example.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Good;

@Repository
@Transactional
public class GoodDao {
	
	@Autowired
	SessionFactory session;
	
	@SuppressWarnings("unchecked")
	public List<Good> findAll(){
		return session.getCurrentSession().createQuery("from good g").list();
	}
	
	public void add(Good g){
		session.getCurrentSession().saveOrUpdate(g);
	}
	
	public void delete(Good g){
		session.getCurrentSession().delete(g);
	}
}
