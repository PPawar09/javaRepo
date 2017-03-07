package com.util;

import java.io.File;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.bo.Event;
import com.bo.Location;

public class Test extends GenericDAOHibernateImpl<ViewAddOnJournalDetail>
{


    static Session session = null;

    public Test()
    {
        super(ViewAddOnJournalDetail.class);
    }

    public static void main(String args[]){

        Test test = new Test();




        session = test.openSession();

        test.retlocation(session);

    }



    public Session openSession()
    {

        try
        {
            File f = new File("C:\\hib\\test.cfg.xml");
            /*
             * Configuration cnf = new Configuration(); SessionFactory sf =
             * cnf.configure(f).buildSessionFactory(); session =
             * sf.openSession();
             */

            session = getSession();

            System.out.println("Configuration loaded");

        }
        catch (Exception e)
        {
            System.out.println("configuration not loaded");
            e.printStackTrace();
        }

        return session;
    }

    void retlocation(Session ses){
        try{

            Transaction tx = null;

            tx = ses.beginTransaction();/*
            Event event = (Event) session.load(Event.class, 12l);
            Location l = event.getLocation();*/
            
            // Using Criteria Object
            
            Criteria crt = ses.createCriteria(Event.class);
            crt.add(Restrictions.eq("name", "sas"));
            
            List lst = crt.list();
            
            for(Object obj : lst){
                
                Event et = (Event) obj;
                
                System.out.println("********** ID :"+ et.getId());
                
            }
            /*
            System.out.println("***********"+ l.getName());
            System.out.println("***********"+ event.getName());*/
            tx.commit();

        }catch(Exception e){
            e.printStackTrace();
        }



    }

    void insert(Session ses){
        Transaction tx = null;
        SessionFactory sessionFactory = null;
        Location location = new Location();
        location.setName("Hilton Convention Center");
        location.setAddress("950 North Stafford St.");
        location.setId(1234l);

        Event event = new Event();
        event.setId(54321l);
        event.setName("Annual Meeting");
        event.setDuration(60);
        event.setStartDate("33u");
        event.setLocation(location);
        try {

            tx = ses.beginTransaction();
            ses.save(location);
            ses.save(event);
            ses.flush();
            tx.commit();
            System.out.println("Event and location saved!");
        } catch (HibernateException e) {

            e.printStackTrace();

        }finally {
            ses.close();
        }

    }
}
