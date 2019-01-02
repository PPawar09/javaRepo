package com.mokito.service;

import com.mokito.dao.DatabaseDAO;
import com.mokito.dao.NetworkDAO;

public class RecordService
{
    DatabaseDAO database;
    NetworkDAO network;
     
    //setters and getters
 
    public boolean save(String fileName)
    {
        database.save(fileName);
        System.out.println("Saved in database in Main class");
         
        network.save(fileName);
        System.out.println("Saved in network in Main class");
         
        return true;
    }
}