package com.pp.bean;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class AccountService
 */
@Stateless
public class AccountService implements AccountServiceRemote {

    /**
     * Default constructor. 
     */
    public AccountService() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public String createAccount(String firstName, String lastName) {
        String name = "Prakash Pawar";
        return name;
    }

    
    
}
