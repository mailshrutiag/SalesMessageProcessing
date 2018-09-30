package com.sales.message.processing.model.message;

import java.util.Map;

import com.sales.message.processing.model.sales.SalesRecorded;

/**
 * Message Interface
 * @author Shruti Agarwal
 */
public interface Message {
	/**
	 * @param message
	 * @param recordSalesMap: Map of sales products
	 * @param appMessageCount: messages count while Application is Running
	 * @return
	 */
	public boolean process(String message, Map<String, SalesRecorded> salesRecordedMap, int appMessageCount);

	/**
	 * Method to validate message
	 * @param messageLine
	 * @return
	 */
	public boolean isMsgValid(String messageLine);
}
