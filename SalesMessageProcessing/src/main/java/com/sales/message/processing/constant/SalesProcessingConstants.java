package com.sales.message.processing.constant;

/**
 * Class to define Constants- Message Reporting Intervals- 10 and 50. 
 * @author Shruti Agarwal 
 */
public class SalesProcessingConstants {
	
	public static final int MSG_10 = 10;		//After every 10th message, application to log a report of sales
	public static final int MSG_50 = 50;		//After every 50th message, application to pause and log complete report of sales
	
	public static final String MsgType1_PATTERN = "(\\w+)\\sat\\s(\\d+)p$"; 	// Allowed pattern for Message Type 1 for validation
	public static final  String MsgType2_PATTERN = "(\\d+)\\ssales\\sof\\s(\\w+)s\\sat\\s(\\d+)p\\seach$";		// Allowed pattern for Message Type 2 for validation
	public static final String MsgType3_PATTERN = "(Add|Subtract|Multiply)\\s(\\d+)p?\\s(\\w+)s$";		// Allowed pattern for Message Type 3 for validation

}
