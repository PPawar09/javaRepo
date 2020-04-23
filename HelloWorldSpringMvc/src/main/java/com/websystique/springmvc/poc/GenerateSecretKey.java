package com.websystique.springmvc.poc;

import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.auth0.jwt.algorithms.Algorithm;

public class GenerateSecretKey {
	
  public String getRandomTimeKey(){
	 String token = null;
	 String my_big_bang = getSecretKey();
	 long currentTime = System.currentTimeMillis();
	 
	 try{
		 token = Algorithm.HMAC256(my_big_bang + (currentTime/60000)*60000).toString();
		 System.out.println("****" + token);
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	 
	 return token;
  }
  
  /*
   * This is better approach to generate key using API javax.crypto.KeyGenerator
   * and then encode it base64.
   */
  public String getSecretKey(){
	  String encodedKey = null;

	  try{

		  SecretKey secretKey = KeyGenerator.getInstance("HmacSHA256").generateKey();
		  System.out.println("**without base64 encode**");
		  // get base64 encoded version of the key
		  encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
		  System.out.println("**EncodedKey Via javax.crypto.KeyGenerator**" + encodedKey);

	  }catch(Exception e){
		  e.printStackTrace();
	  }

	  return encodedKey;
  }

}
