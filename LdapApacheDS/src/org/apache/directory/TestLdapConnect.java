package org.apache.directory;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

public class TestLdapConnect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try{
		Hashtable env = new Hashtable();
		 
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        //env.put(Context.PROVIDER_URL, "ldap://localhost:10389/o=sevenseas");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
 
        //env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
        env.put(Context.SECURITY_PRINCIPAL, "cn=James Hook,ou=people,o=sevenseas");
        env.put(Context.SECURITY_CREDENTIALS, "peterPan");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
 
        InitialDirContext ctx = new InitialDirContext(env);
        
        NamingEnumeration enm = ctx.list("");
 
       // Attributes attrs = ctx.getAttributes("");
       // NamingEnumeration enm = attrs.getAll();
        while (enm.hasMore()) {
            System.out.println(enm.next());
        }
		}catch(Exception e){
			e.printStackTrace();
		}
    }
}