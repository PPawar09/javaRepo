package com.pp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;
import org.togglz.core.repository.mem.InMemoryStateRepository;
import org.togglz.core.spi.FeatureManagerProvider;
import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.NoOpUserProvider;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;
import org.togglz.servlet.util.HttpServletRequestHolder;

public class SingletonFeatureManagerProvider implements FeatureManagerProvider {

    private static FeatureManager featureManager;

    public int priority() {
        return 30;
    }

    public synchronized FeatureManager getFeatureManager() {

        if (featureManager == null) {
            featureManager = new FeatureManagerBuilder()
                    .featureClass(MyFeatures.class)
                    .stateRepository(new InMemoryStateRepository())
                    .userProvider(getUserProvider())
                    .build();
        }

        return featureManager;

    }
    
    public UserProvider getUserProvider() {
      	 return new UserProvider() {
               
      		 public FeatureUser getCurrentUser() {
               
                   HttpServletRequest request = HttpServletRequestHolder.get();
                   HttpSession session = request.getSession();
                   String username1 = (String) request.getParameter("username");
                   String username = (String) session.getAttribute("username");
                  
                   //else{
                  //	 username2 = request.getUserPrincipal().getName();
                   //}
                   System.out.println(username +", username="+ username1);
                   boolean isAdmin = "admin".equals(username);
                  // if(isAdmin)
                   return new SimpleFeatureUser(username, isAdmin);
                   
               }
           };
      }

}
