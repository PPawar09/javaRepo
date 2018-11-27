package com.websystique.springmvc.service;

import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			SecretKey secretKey = KeyGenerator.getInstance("HmacSHA256").generateKey();
			// get base64 encoded version of the key
			String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
			System.out.println("**Key**" + encodedKey);
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	//e+DTa776pqAnyT91gx3V9Q==
	//Zo6g5Ltxk5fOuXvH+RJ7ZLxlsSqv5Uyaj3Nz2HGnkdg=
	public void keyGen(){
		try{
		SecretKey secretKey = KeyGenerator.getInstance("HmacSHA256").generateKey();
		// get base64 encoded version of the key
		String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
