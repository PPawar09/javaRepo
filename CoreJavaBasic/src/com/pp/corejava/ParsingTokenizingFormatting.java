package com.pp.corejava;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingTokenizingFormatting {
	/**
	 * Formating String using Java API
	 * standard J2SE APIs in the java.util and java.util.regex packages to format or parse strings or streams. 
	 * 
	 * For strings, write code that uses the Pattern and Matcher classes and the String.split method. 
	 * Recognize and use regular expression patterns for matching 
	 * limited to: .(dot), *(star), +(plus), ?, \d, \s, \w, [ ], () ). 
	 * The use of *, + , and ? will be limited to greedy quantifiers, and the parenthesis 
	 * operator will only be used as a grouping mechanism, not for capturing content during matching. 
	 * 
	 * For streams, write code using the Formatter and Scanner classes and 
	 * the PrintWriter.format/printf methods. 
	 * Recognize and use formatting parameters (limited to: %b, %c, %d, %f, %s) in format Strings. */

	public static void main(String [] args) {
		ParsingTokenizingFormatting obj = new ParsingTokenizingFormatting();
		ParsingTokenizingFormatting obj2 = new ParsingTokenizingFormatting();
		//obj.searchPositionByValue();
		//obj.searchByMetaChar();
		obj.scanner();
	}
	
	
	/**
	 * This method uses Pattern and Matcher to find the user defined pattern position start index(based on o) 
	 * from give stream of String.
	 * 
	 * Eg Stream = abaaaballjjljlab
	 * pattern will be ab
	 * 
	 * out put will be 0 and 4 and 14
	 * 
	 */
	public void searchPositionByValue(){
		//In general, a regex search runs from left to right, and once a source's character has
		//been used in a match, it cannot be reused.
		Pattern p = Pattern.compile("ab"); // define the pattern you are searching.
		Matcher m = p.matcher("abaaaballjjljlab");  // The source from which we need to search.
		
		while(m.find()) {
			System.out.print(m.start() + " ");
		}
	}
	
	/**
	 * This method also uses Pattern and Matcher but along with Metacharacter like 
	 * 
	 * Regex provides a rich set of metacharacters that you can find described in the
	   API documentation for java.util.regex.Pattern.
	   \\d A digit
	   \\s A whitespace character
	   \\w A word character (letters, digits, or "_" (underscore))
	 * 
	 * 
	 */
	public void searchByMetaChar(){
		Pattern p = Pattern.compile("\\d"); // the expression
		Matcher m = p.matcher("a 1 56 _Z"); // the source
		boolean b = false;
		while(b = m.find()) {
			System.out.print(m.start() + " ");
		}
	}
	
	
	/**	Searching Using the Scanner Class 
	 * 
	 * This API will give you the value of the matching pattern based defined pattern using metacharacter
	 * 
	 * Eg Source "a 1 56 _Zjkljljl34kjkljjl"
	 * pattern "//d//d"  (//d will be searching for 2 digit)
	 * 
	 * output will be : 56 and 34
	 * 
	 * Although the java.util.Scanner class is primarily intended for tokenizing data 
	 * it can also be used to find stuff, just like the Pattern and Matcher classes. 
	 * While Scanner doesn't provide location information or search and replace functionality, 
	 * you can use it to apply regex expressions to source data to tell you how many instances of an
	 * expression exist in a given piece of source data.
	 * */
	
	public void scanner(){
		try {
			Scanner s = new Scanner("a 1 56 _Zjkljljl34kjkljjl");
			String token;
			do {
				token = s.findInLine("\\d\\d");
				System.out.println("found " + token);
			} while (token != null);
		} catch (Exception e) { System.out.println("scan exc"); }
	}
	
	
	/** Tokens and Delimiters
		When we talk about tokenizing, we're talking about data that starts out composed of
		two things: tokens and delimiters. Tokens are the actual pieces of data, and delimiters
		are the expressions that separate the tokens from each other. When most people
		think of delimiters, they think of single characters, like commas or backslashes or
		maybe a single whitespace. These are indeed very common delimiters, but strictly
		speaking, delimiters can be much more dynamic. In fact, as we hinted at a few sentences
		ago, delimiters can be anything that qualifies as a regex expression. Let's take
		a single piece of source data and tokenize it using a couple of different delimiters:
		
		source: "ab,cd5b,6x,z4"
		If we say that our delimiter is a comma, then our four tokens would be 
		ab
		cd5b
		6x
		z4
		If we use the same source, but declare our delimiter to be \d, we get three tokens:
		ab,cd
		b,
		x,z
	*/
	public void tokenizer(){
		try {
		} catch (Exception e) { System.out.println("scan exc"); }
	}


}
