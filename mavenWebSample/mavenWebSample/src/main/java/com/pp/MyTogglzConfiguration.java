package com.pp;

import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.jdbc.JDBCStateRepository;
import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;


public class MyTogglzConfiguration implements TogglzConfig {

    public Class<? extends Feature> getFeatureClass() {
        return MyFeatures.class;
    }

    /*public StateRepository getStateRepository() {
        return new FileBasedStateRepository(new File("C:/feature/features.properties"));
    }*/
    
    public StateRepository getStateRepository() {
        return new JDBCStateRepository(MyDataSourceFactory.getMySQLDataSource());
    }

  /*  public UserProvider getUserProvider() {
        return new ServletUserProvider();
    }*/
    
    public UserProvider getUserProvider() {
        return new UserProvider() {
            public FeatureUser getCurrentUser() {
                return new SimpleFeatureUser("admin", true);
            }
        };
    }

}
