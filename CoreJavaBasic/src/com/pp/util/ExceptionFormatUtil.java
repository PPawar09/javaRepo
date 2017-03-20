package com.pp.util;

import java.text.MessageFormat;

/**
 * This is utility class to format the Exception Message instead of printStack trace via different operation
 * @author Prakash Pawar
 */

public class ExceptionFormatUtil {
	
	
	/**
	 * Take the error message template and insert the class and method name into the message and return as a String.
	 * @return String Formatted message for the Exception type specified by the caller.
	 */
	public static String getMessage(final String messageType) {
		return getStackTraceMessage(messageType);
	}

	/**
	 * Common method used to get the stack trace data and format it. Used by the public methods in this class which can
	 * decorate the message returned.
	 * 
	 * @param messageType
	 * @return
	 */
	private static final String getStackTraceMessage(final String messageType) {
		final StackTraceElement stackTraceElements[] = getStackTraceElements();
		final StringBuffer exceptionCaughtLocation = new StringBuffer(100);
		exceptionCaughtLocation.append(stackTraceElements[3].getClassName());
		exceptionCaughtLocation.append('.');
		exceptionCaughtLocation.append(stackTraceElements[3].getMethodName());
		final MessageFormat formatter = new MessageFormat(messageType);
		final Object[] parameter = new Object[] { exceptionCaughtLocation.toString() };
		final String errorMessage = formatter.format(parameter);
		return errorMessage;
	}

	/**
	 * Utility method to return the class name where the exception is caught.
	 * 
	 * @return
	 */
	public static final String getMethodName() {
		final StackTraceElement stackTraceElements[] = getStackTraceElements();
		return stackTraceElements[2].getMethodName();
	}

	/**
	 * Returns an array of the stack trace elements for use by other methods in this class.
	 * @return
	 */
	private static StackTraceElement[] getStackTraceElements() {
		return (new Throwable()).getStackTrace();
	}

}
