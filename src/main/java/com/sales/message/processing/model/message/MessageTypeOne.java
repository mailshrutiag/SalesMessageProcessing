package com.sales.message.processing.model.message;

import java.util.List;
import java.util.Map;

import com.sales.message.processing.constant.SalesProcessingConstants;
import com.sales.message.processing.model.sales.Sale;
import com.sales.message.processing.model.sales.SalesRecorded;
import com.sales.message.processing.validator.*;

/**
 * Contains the details of 1 sale E.g apple at 10p
 * @author Shruti Agarwal
 * 
 */
public class MessageTypeOne implements Message {
	
	public boolean process(String message, Map<String, SalesRecorded> salesRecordedMap, int messageCount) {
		List<String> msgTokens = MessagePatternValidator.getMessageTokens(SalesProcessingConstants.MsgType1_PATTERN, message);
		String productName = msgTokens.get(1);
		
		if (!salesRecordedMap.containsKey(productName))			// if Sales not existing- creating a new Sales and Adding to map
			salesRecordedMap.put(productName, new SalesRecorded());
		SalesRecorded productSales = salesRecordedMap.get(productName);

		int saleValue = Integer.parseInt(msgTokens.get(2));
		productSales.addSaleToProduct(new Sale(productName,saleValue));

		String historyRecordLine = "At Message#" + messageCount + " Added one sale to: " + productName
				+ " with value: " + saleValue;
		productSales.addHistoryRecord(historyRecordLine);

		return true;
	}
	
	public boolean isMsgValid(String messageLine) {
		boolean isValid = MessagePatternValidator.isPatternMatch(SalesProcessingConstants.MsgType1_PATTERN, messageLine);
		return isValid;
	}
}
