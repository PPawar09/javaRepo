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

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;


/**
 * The GenericDAOImpl class provides the implementation for hibernate operations
 * in the GenericDAO interface like create ,get ,update and delete persistant
 * objects.
 */
public class GenericDAOHibernateImpl<T>
{
    private Class<T> type;

    public GenericDAOHibernateImpl(Class<T> type)
    {
        this.type = type;
    }

    /** Persist the newInstance object into database */
    public String create(T o)
    {
        return String.valueOf(getSession().save(o));
    }

    /**
     * Retrieve an object that was previously persisted to the database using
     * the indicated id as primary key
     */
    public T get(String id)
    {
        return (T) getSession().get(type, id);
    }

    /** Save changes made to a persistent object. */
    public void update(T o)
    {
        getSession().update(o);
    }

    /** Save changes made to a persistent object. */
    public void delete(T o)
    {
        getSession().delete(o);
    }

    /**
     * This method returns the current session
     * 
     * @return Session
     */
    public Session getSession()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session;
    }

    /**
     * This methos returns List of object
     * 
     * @param criteria
     * @return List
     */
    public List<Object> getObjects(DetachedCriteria criteria)
    {
        Criteria executableCriteria = criteria
                .getExecutableCriteria(getSession());
        return executableCriteria.list();
    }
    /**
     * This methos returns List of object
     * 
     * @param criteria
     * @return List
     */
    public List<Object> getObjects(Criteria criteria)
    {
        return criteria.list();
    }

    /**
     * This method create or update of the object
     * 
     * @return Session
     */
    /** Save changes made to a persistent object. */
    public void saveOrUpdate(T o)
    {
        getSession().saveOrUpdate(o);

    }
}
