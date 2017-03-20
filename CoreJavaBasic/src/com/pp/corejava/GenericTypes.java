package com.pp.corejava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class explain the basics of Java Generic. Java Generics features
 * 
 * Generic in Collection : 
 *   > you can set the type of the collection to limit what kind of objects can be inserted into the collection. 
 *   > Additionally, you don't have to cast the values you obtain from the collection. 
 *   > generic uses <> diamond operator to specify generic object type.
 *   
 * Generic In Class : 
 *   > It is possible to generify your own Java classes. Generics is not restricted to the predefined classes in the Java API's.
 *   > below is example of generic class. GenericFactory<T> where The <T> is a type token that signals that this class 
 *     can have a type set when instantiated
 *     
 * Generic In Method : 
 *   > It is possible to generify methods in Java. below is an example
 *   > public static <T> T addAndReturn(T element, Collection<T> collection)
 * 
 * @author Prakash Pawar
 */
public class GenericTypes {

	public static void main(String[] args) throws Exception{
		//use of generic in List,Set,Map
		List lst = new ArrayList<String>();
		Set set = new HashSet<String>();
		Map map = new HashMap<String,String>();

		//Generic Class Use
		//Notice how it is not necessary to cast the object returned from the factory.createInstance() method. 
		//The compiler can deduct the type of the object from the generic type of the GenericFactory created, 
		//because you specified the type inside the <>.
		GenericFactory<GenericTypes> factory = new GenericFactory<GenericTypes>(GenericTypes.class);
		GenericTypes myClassInstance = factory.createInstance();

		//Each instance of the GenericFactory can be generified to different types.
		GenericFactory<Student> factory1 = new GenericFactory<Student>(Student.class);
		Student someObjectInstance = factory1.createInstance();

		//Generic method Use
		//Notice how we can call the addAndReturn() method using both String's and Integer's and 
		//their corresponding collections. The compiler knows from the type of the T parameter and collection<T> 
		//parameter definitions, that the type is to be taken from these parameters at call time (use time).
		String stringElement = "stringElement";
		List<String> stringList = new ArrayList<String>();
		String theElementStr = addAndReturn(stringElement, stringList);    

		Integer integerElement = new Integer(123);
		List<Integer> integerList = new ArrayList<Integer>();
		Integer theElementInt = addAndReturn(integerElement, integerList);

	}

	/**
	 * Generic in method example.
	 * This method specifies a type T which is used both as type for the element parameter and the generic type of the 
	 * Collection. Notice how it is now possible to add elements to the collection. This was not possible if you had 
	 * used a wildcard in the Collection parameter definition.So, how does the compiler know the type of T?
	 * The answer is, that the compiler infers this from your use of the method.
	 */
	public static <T> T addAndReturn(T element, Collection<T> collection){
		collection.add(element);
		return element;
	}
}

/**
 * Declaring and defining generic class.
 * 
 * @author Prakash Pawar
 * @param <T>
 */
class GenericFactory<T> {
    Class theClass = null;
    
    public GenericFactory(Class theClass) {
        this.theClass = theClass;
    }

    public T createInstance()
    throws IllegalAccessException, InstantiationException {
        return (T) this.theClass.newInstance();
    }
}
