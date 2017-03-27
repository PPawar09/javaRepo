package com.pp.bean;

import javax.ejb.Local;

@Local
public interface Cart {

	  void addProductToCart(Product product);
	  
	  void checkOut();

}