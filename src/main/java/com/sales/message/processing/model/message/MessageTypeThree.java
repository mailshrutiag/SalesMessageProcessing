package com.sales.message.processing.model.message;

import java.util.List;
import java.util.Map;

import com.sales.message.processing.constant.SalesProcessingConstants;
import com.sales.message.processing.model.sales.SalesRecorded;
import com.sales.message.processing.model.sales.SalesRecorded.UpdateType;
import com.sales.message.processing.validator.MessagePatternValidator;

/**
 * contains the details of a sale and an adjustment operation E.g Add 20p apples
 * @author Shruti Agarwal
 * 
 */
public class MessageTypeThree implements Message {
	
	public boolean process(String message, Map<String, SalesRecorded> salesRecordedMap, int messageCount) {
		List<String> msgTokens = MessagePatternValidator.getMessageTokens(SalesProcessingConstants.MsgType3_PATTERN, message);
		UpdateType updateType = SalesRecorded.UpdateType.valueOf(msgTokens.get(1).toUpperCase());

		String productName = msgTokens.get(3);
		
		if (!salesRecordedMap.containsKey(productName)) {		// if Sales not existing- Notify User and Ask to add the Sales Product before making any Adjustment
			System.err.println("Sales Product- \"" + productName + "\" does not Exist. Please Add the Product before making Adjustments..");
			return false;
		}
		SalesRecorded productSales = salesRecordedMap.get(productName);

		int changeValue = Integer.parseInt(msgTokens.get(2));
		productSales.updateAllSales(updateType, changeValue);

		String historyRecordLine = "At Message#" + messageCount + " " + updateType.toString() + " previous ("
				+ productSales.getNumberOfSales() + ") sales to: " + productName + " with value: " + changeValue;
		productSales.addHistoryRecord(historyRecordLine);

		return true;
	}
	
	public boolean isMsgValid(String messageLine) {
		boolean isValid = MessagePatternValidator.isPatternMatch(SalesProcessingConstants.MsgType3_PATTERN, messageLine);
		return isValid;
	}
}
