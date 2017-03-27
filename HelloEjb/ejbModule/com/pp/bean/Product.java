package com.pp.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT", catalog = "dbo")
public class Product implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  //@GeneratedValue(strategy = GenerationType.TABLE)
  @Column(name = "ID", nullable = false)
  private int id = 1056;

  @Column(name = "TYPE", nullable = false)
  private String type;

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public void setType(String description) {
    this.type = description;
  }

}
