package app.domain;

import app.domain.enums.BookType;

public class Book {

	String name;
	
	String author;
	
	double price;
	
	BookType type;
	
	public Book() {
    }

	public Book(String name, String author, double price) {
		this.name = name;
		this.author = author;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public BookType getType() {
      return type;
    }
	
	public void setType(BookType type) {
      this.type = type;
    }
}
