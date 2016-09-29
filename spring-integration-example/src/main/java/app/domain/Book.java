package app.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import app.enums.BookType;

@Entity
public class Book {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  Long id;

  String name;

  String author;

  double price;

  @Enumerated(EnumType.STRING)
  BookType type;

  public Book() {}

  public Book(String name, String author, double price) {
    this.name = name;
    this.author = author;
    this.price = price;
  }

  public Long getId() {
    return id;
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

  @Override
  public String toString() {
    return "Book [id=" + id + ", name=" + name + ", author=" + author + ", price=" + price
        + ", type=" + type + "]";
  }
  
  
}
