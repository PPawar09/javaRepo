package com.websystique.springmvc.viewresolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class ExcelViewResolver implements ViewResolver{

	public View resolveViewName(String arg0, Locale arg1) throws Exception {
		ExcelView view = new ExcelView();
		return view;
	}
	
}