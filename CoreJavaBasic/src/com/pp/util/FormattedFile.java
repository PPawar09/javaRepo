package com.pp.util;

import java.util.Date;

public class FormattedFile {
	
	String name = "name"; // length = 10; align left; fill with spaces
    Integer id = 123; // length = 5; align left; fill with spaces
    Integer serial = 321; // length = 5; align to right; fill with '0'
    Date register = new Date();// length = 8; convert to yyyyMMdd
    
    public static void main(String agrs[]){
    	
    	System.out.println("FormatFile : "+ new FormattedFile().asRecord());
    }
    
    String asRecord() {
    	return String.format("%-10s%-5d%05d%tY%<tm%<td",name,id,serial,register);
    }

}
