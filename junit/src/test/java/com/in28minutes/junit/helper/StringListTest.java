package com.in28minutes.junit.helper;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class StringListTest {
	
	List actualLst = Arrays.asList("Test","Prakash","Somu");
	
	List expectedLst = Arrays.asList("Test","Prakash","Somu");

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testStringList() {
		//compare the list
		Assert.assertEquals(expectedLst, actualLst);
		
		//validate list content
		Assert.assertTrue(actualLst.contains("Somuu"));
	}

}
