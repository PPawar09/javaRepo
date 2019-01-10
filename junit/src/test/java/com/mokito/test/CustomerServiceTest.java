package com.mokito.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.in28minutes.junit.model.Customer;
import com.mokito.dao.CustomerDao;
import com.mokito.service.CustomerService;

public class CustomerServiceTest {

    @Mock
    private CustomerDao daoMock;

    @InjectMocks
    private CustomerService service;

    @Before
    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {

         //assertion here
    }
    
    @Test
    public void testAddCustomer_returnsNewCustomer() {

        when(daoMock.save(any(Customer.class))).thenReturn(new Customer());

        Customer customer = new Customer();

        assertThat(service.addCustomer(customer), is(notNullValue()));
       
    }
    
    
    /*
     *  Let’s look at the role of the annotations in the above example.
		
		@Mock will create a mock implementation for the CustomerDao
		@InjectMocks will inject the mocks marked with @Mock to this instance when it is created.
		So when or where are these instances created? Well, it is done by this line which reside in the 
		setUp method.
		
		MockitoAnnotations.initMocks(this);
		So these instances would be created at the start of every test method of this test class.
     * 
     * 
     * 
     */

}
