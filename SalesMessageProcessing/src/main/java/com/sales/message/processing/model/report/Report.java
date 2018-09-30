package com.sales.message.processing.model.report;

import java.util.Map;

import com.sales.message.processing.api.MessageWriter;
import com.sales.message.processing.model.sales.SalesRecorded;

/**
 * Report Interface
 * @author Shruti Agarwal
 */
public interface Report {
	
	/**
	 * @param salesRecordedMap: Map of recorded sales
	 * @param messageCount: messages count
	 * @param messageWriter
	 */
	public void process(Map<String, SalesRecorded> salesRecordedMap, int messageCount, MessageWriter messageWriter);
}
