/*
 * Copyright (c) 2006-2007 Citicorp Credit Services, Inc. 14000 Citi Cards Way,
 * Jacksonville, FL 32258, USA All Rights Reserved.
 * 
 * The software contained herein is proprietary and confidential and shall not
 * be duplicated, published or disclosed to any third party in whole or in part
 * without the prior written consent from Citicorp Credit Services, Inc., which
 * shall never be presumed. You shall use it only in accordance with the terms
 * of the NDA you entered into with Citi.
 */
package com.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;

public class ViewJournalDAO extends
        GenericDAOHibernateImpl<ViewAddOnJournalDetail>
{

    /**
     * this final variable is for logging purpose
     */
    public static final String PACKAGE_NAME = ViewJournalDAO.class.getPackage()
            .getName();

    /**
     * this final variable is for logging purpose
     */
    public static final String CLASS_NAME = ViewJournalDAO.class.getName();

    public ViewJournalDAO()
    {
        super(ViewAddOnJournalDetail.class);
    }

    /*
     * this method based on the given criteriaForData will fetch 1. the total
     * number of records in the DB which satisfy the given criteriaForData 2.
     * the records valid for the given page based on the page size.
     */

    public List findByCriteria()
            throws Exception
    {
        Criteria criteriaForData = null;
        Criteria criteriaForProjection = null;
        List addOnList = null;
        List returnList = null;
        int countOfRecords = 0;
     

        Calendar morning = Calendar.getInstance();
        Calendar evening = Calendar.getInstance();

        criteriaForData = getSession().createCriteria(
                ViewAddOnJournalDetail.class);
        criteriaForProjection = getSession().createCriteria(
                ViewAddOnJournalDetail.class);
        
        return new ArrayList();

    }




}
