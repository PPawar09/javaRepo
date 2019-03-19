package com.pp.corejava;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionTestExample {

	public static void main(String args[]) throws Exception{

		Class c = Class.forName("com.pp.corejava.DemoTest");

		// Getting Constructor of the class
		System.out.println("------------Getting Constructor of the class-----------");
		Constructor[] cons =  c.getConstructors();
		System.out.println("Constructor Array : " + Arrays.toString(cons));

		// Getting all methods of class even inherited
		System.out.println("-----------Getting all methods of class even inherited------------");
		Method[] methods = c.getMethods();
		System.out.println("Method Array : " + Arrays.toString(methods));

		// Getting methods of class
		System.out.println("-----------Getting methods of class------------");
		Method[] decMethods = c.getDeclaredMethods();
		System.out.println("DecMethod Array : " + Arrays.toString(decMethods));

		// Getting field of the class
		System.out.println("-----------Getting field of the class------------");
		Field[] field = c.getDeclaredFields();
		System.out.println("DecField Array : " + Arrays.toString(field));
	}


}


class DemoTest {

	private String name = null;
	private String email = null;

	public DemoTest(String name,String email){
		super();
		this.email = email;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


}
