package com.sales.message.processing.model.report;

import java.util.Map;
import java.util.Map.Entry;

import com.sales.message.processing.api.MessageWriter;
import com.sales.message.processing.model.sales.SalesRecorded;

/**
 * Class to log a report after every 10 sales- detailing the number of sales of each product and their total value.
 * @author Shruti Agarwal
 * 
 */
public class ReportAfter10Msgs implements Report {

	public void process(Map<String, SalesRecorded> salesRecordedMap, int messageCount, MessageWriter messageWriter) {
		String logId = "\n** Sales Details After: " + messageCount + " messages-";
		messageWriter.writeMessageLine(logId);
		
		for (Entry<String, SalesRecorded> productSales : salesRecordedMap.entrySet()) {
			String Line = "Number of Sales:" + productSales.getValue().getNumberOfSales()
							+ ", Product Type: " + productSales.getKey() + ", Total Value: " + productSales.getValue().getTotalValue();
			messageWriter.writeMessageLine(Line);
		}
	}
}
