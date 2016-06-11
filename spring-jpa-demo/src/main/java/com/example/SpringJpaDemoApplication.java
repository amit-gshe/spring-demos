package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.dao.GoodDao;
import com.example.entity.Good;

@SpringBootApplication
public class SpringJpaDemoApplication implements CommandLineRunner{

	GoodDao goodDao;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Good g = new Good();
		g.setName("小米5");
		goodDao.add(g);
		
	}
	
	@Autowired
	public void setGoodDao(GoodDao goodDao) {
		this.goodDao = goodDao;
	}
}
