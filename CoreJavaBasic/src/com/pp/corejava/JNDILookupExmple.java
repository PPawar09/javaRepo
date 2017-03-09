package com.pp.corejava;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

/**
 * This is Java test client class using JNDI API and steps for connecting different kinds of naming and directory services from Java Application.
 * JNDI API is not specific to a particular naming or directory service, it can be used to access many different kinds of systems including file 
 * systems; distributed objects systems like CORBA, Java RMI, and EJB; and directory services like LDAP, Novell NetWare, and NIS+.
 * 
 * JNDI is similar to JDBC in that they are both Object-Oriented Java APIs that provide a common abstraction for accessing services from different 
 * vendors. While JDBC can be used to access a variety of relational databases, JNDI can be used to access a variety of of naming and directory 
 * services.
 * 
 * It is having different method using lookup for name binding.
 * 
 * High Level Steps for JNDI lookup are 
 * step1 Create Hashtable env variable with key value required for getting Context. 
 * 	     > INITIAL_CONTEXT_FACTORY = com.sun.jndi.ldap.LdapCtxFactory
 *       > PROVIDER_URL = url of binding 
 *       > Context context = new InitialContext(env)
 *       
 * Step2 This context can be used to query the data.
 * 		 > eg getting data source DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyLocalDB");
 * 
 * Step3 Now we got the object from JNDI to use in Application.
 *
 * @author  Prakash Pawar
 */
public class JNDILookupExmple {

	static Logger logger = Logger.getLogger(JNDILookupExmple.class.getName());
	public static void main(String [] rgstring) {
		try {
			// Create the initial context.  The environment information specifies the JNDI provider to use
			// and the initial URL to use (in our case, a directory in URL form -- file:///...).

			Hashtable env = new Hashtable();
			String url = "ldap://localhost:10389";
			env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL,url);

			Context context = new InitialContext(env);

			// If you provide no other command line arguments,list all of the names in the specified context and
			// the objects they are bound to.
			NamingEnumeration namingenumeration = context.listBindings("");
			while (namingenumeration.hasMore()) {
				Binding binding = (Binding)namingenumeration.next();
				System.out.println(binding.getName() + " " + binding.getObject());
			}
			context.close();
		}catch (NamingException namingexception) {
			logger.log(Level.INFO,"NamingException|Class=JNDILookupExmple ",namingexception);
		}catch (Exception e) {
			logger.log(Level.INFO,"Exception|Class=JNDILookupExmple ",e);
		}
	}
}