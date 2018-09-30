package com.sales.message.processing.model.message;

import java.util.List;
import java.util.Map;

import com.sales.message.processing.constant.SalesProcessingConstants;
import com.sales.message.processing.model.sales.Sale;
import com.sales.message.processing.model.sales.SalesRecorded;
import com.sales.message.processing.validator.MessagePatternValidator;

/**
 * contains the details of a sale and the number of occurrences of that sale. E.g 20 sales of apples at 10p each.
 * @author Shruti Agarwal
 * 
 */
public class MessageTypeTwo implements Message {
	
	public boolean process(String message, Map<String, SalesRecorded> salesRecordedMap, int messageCount) {

		List<String> msgTokens = MessagePatternValidator.getMessageTokens(SalesProcessingConstants.MsgType2_PATTERN, message);

		int noOfSales = Integer.parseInt(msgTokens.get(1));
		String productName = msgTokens.get(2);

		if (!salesRecordedMap.containsKey(productName))			//if Sales not existing- creating a new Sales and Adding to map
			salesRecordedMap.put(productName, new SalesRecorded());
		SalesRecorded productSales = salesRecordedMap.get(productName);
		int saleValue = Integer.parseInt(msgTokens.get(3));
		productSales.addDuplicatedSalesToProduct(new Sale(productName,saleValue), noOfSales);

		String historyRecordLine = "At Message#" + messageCount + " Added (" + noOfSales + ") sales to: " + productName
				+ " with value: " + saleValue;
		productSales.addHistoryRecord(historyRecordLine);

		return true;
	}
	
	public boolean isMsgValid(String messageLine) {
		boolean isValid = MessagePatternValidator.isPatternMatch(SalesProcessingConstants.MsgType2_PATTERN, messageLine);
		return isValid;
	}
}
