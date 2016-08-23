package app.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import app.enums.Gender;

@Entity
public class Reader {

  @Id
  @GeneratedValue
  Long id;
  
  @Column(nullable=false,unique=true)
  String name;
  
  @Enumerated(EnumType.STRING)
  Gender gender;
  
  int age;
  
  Date registeredDate = new Date();
  
  public Reader() {
   
  }

  public Reader(String name, Gender gender, int age) {
    this.name = name;
    this.gender = gender;
    this.age = age;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  @Override
  public String toString() {
    return "Reader [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age
        + ", registeredDate=" + registeredDate + "]";
  }
  
  
}
