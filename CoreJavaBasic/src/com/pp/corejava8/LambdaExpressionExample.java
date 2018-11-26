package com.pp.corejava8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ppawar2
 * 
 * 	Java Lambda Expressions : 
   	Lambda expression is a new and important feature of Java which was included in Java SE 8. It provides a clear and concise way to represent 
   	one method interface using an expression. It is very useful in collection library. It helps to iterate, filter and extract data from collection.

   	The Lambda expression is used to provide the implementation of an interface which has functional interface. 
   	It saves a lot of code. In case of lambda expression, we don't need to define the method again for providing the implementation. 
   	Here, we just write the implementation code.

	Java lambda expression is treated as a function, so compiler does not create .class file.

   	Functional Interface : 
   	Lambda expression provides implementation of functional interface. An interface which has only one abstract method is called functional 
   	interface. Java provides an anotation @FunctionalInterface, which is used to declare an interface as functional interface.

   	Why use Lambda Expression : 
	To provide the implementation of Functional interface.
	Less coding.

	Java Lambda Expression Syntax		(argument-list) -> {body}  
	Java lambda expression is consisted of three components.

	1) Argument-list: It can be empty or non-empty as well.

	2) Arrow-token: It is used to link arguments-list and body of expression.

	3) Body: It contains expressions and statements for lambda expression.
 *
 */

interface Drawable{  
    public void draw();  
}

public class LambdaExpressionExample {

	public static void main(String[] args) {  
		final int width=10;  

		//without lambda, Drawable implementation using anonymous class  
		Drawable d=new Drawable(){  
			public void draw(){System.out.println("WithOutLambda Exp Drawing "+width);
			}  
		};  
		d.draw();  
		
		//with lambda  
        Drawable d2=()->{  
            System.out.println("WithLambda Exp Drawing "+width);  
        };  
        d2.draw();  
        
        //Lambda Expression Example: Foreach Loop
        List<String> list=new ArrayList<String>();  
        list.add("ankit");  
        list.add("mayank");  
        list.add("irfan");  
        list.add("jai");  
        
        System.out.println("*** Lambda Expression Example: Foreach Loop");
        list.forEach(  
            (n)->System.out.println(n)  
        );
	} 
}
