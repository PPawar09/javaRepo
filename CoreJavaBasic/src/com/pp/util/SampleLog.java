package com.pp.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SampleLog {
	
	private static final Logger logger = Logger.getLogger(SampleLog.class.getName());
	
	public void dummy(){
		logger.log(Level.WARNING, "Class=HomeQuoteManagerBean|Method=processHomePropertyInfo|Quote Eligible for Fire Tool Functionality="+1111);
		logger.log(Level.WARNING, "Class=IncentiveServiceHelper|Method=callIncentivePrgmSrvc|IncentioveProgramProcess|START QT: "+1111+"|POLICY: "+1111);
	}
	
}
