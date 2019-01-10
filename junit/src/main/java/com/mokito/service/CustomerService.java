package com.mokito.service;

import com.in28minutes.junit.model.Customer;
import com.mokito.dao.CustomerDao;

public class CustomerService {

	private CustomerDao customerDao;

	public boolean addCustomer(Customer customer){

		if(customerDao.exists(customer.getPhone())){
			return false;
		}

		return customerDao.save(customer);
	}
	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

}