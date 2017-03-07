/**
 * Copyright (c) 2006-2007 Citicorp Credit Services, Inc. 14000 Citi Cards Way,
 * Jacksonville, FL 32258, USA All Rights Reserved.
 * 
 * The software contained herein is proprietary and confidential and shall not
 * be duplicated, published or disclosed to any third party in whole or in part
 * without the prior written consent from Citicorp Credit Services, Inc., which
 * shall never be presumed. You shall use it only in accordance with the terms
 * of the NDA you entered into with Citi.
 * 
 */
package com.util;


import java.io.File;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The HibernateUtil class is a utility class which returns the hibernate
 * session and performs various operations on the session.
 */
public class HibernateUtil
{
    private final static String PACKAGE_NAME = HibernateUtil.class.getPackage()
            .getName();

    private final static String CLASS_NAME = HibernateUtil.class.getSimpleName();

    private static SessionFactory sessionFactory;

    public static final ThreadLocal session = new ThreadLocal();
    
    static File file = new File("C:\\hib\\test.cfg.xml");
    

    public static synchronized SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
        {
            sessionFactory = new Configuration().configure(file).buildSessionFactory();
        }
        return sessionFactory;
    }

    /*
     * public static void setSessionFactory(SessionFactory factory) {
     * HibernateUtil.factory = factory; }
     */
    /**
     * This method returns the current session
     * 
     * @return Session
     */
    public static Session currentSession()
    {
        final String METHOD_NAME = "currentSession";
        Session s = (Session) session.get();
        // Open a new Session, if this Thread has none yet
        if (s == null)
        {
            if (sessionFactory == null)
            {
                throw new RuntimeException(
                        "SessionFactory has to be initialized.");
            }
            try
            {
                s = sessionFactory.openSession();
            }
            catch (HibernateException e)
            {
                               throw new RuntimeException(e);
            }
            session.set(s);
        }
        return s;
    }

    /**
     * This method closes the session on which it is called.
     * 
     * @return Session
     */
    public static void closeSession()
    {
        final String METHOD_NAME = "closeSession";
        Session s = (Session) session.get();
        session.set(null);
        if (s != null)
        {
            try
            {
                s.close();
            }
            catch (HibernateException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * This method is used to flush the session on which it is called.
     * 
     * @return Session
     */
    public static void flushSession()
    {
        final String METHOD_NAME = "flushSession";
        Session s = (Session) session.get();
        if (s != null)
        {
            try
            {
                s.flush();
            }
            catch (HibernateException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}