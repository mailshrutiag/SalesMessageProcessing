package com.sales.message.processing.model.report;

import java.util.Map;
import java.util.Map.Entry;

import com.sales.message.processing.ap.MessageWriter;
import com.sales.message.processing.model.sales.SalesRecorded;

/**
 * Class to log a report after every 50 sales- the adjustments that have been made to each sale type while the application was running.
 * @author Shruti Agarwal
 * 
 */
public class ReportAfter50Msgs implements Report {
	
	public void process(Map<String, SalesRecorded> salesRecordedMap, int messageCount, MessageWriter messageWriter) {
		for (Entry<String, SalesRecorded> productSales : salesRecordedMap.entrySet()) {			// Messages History for each Sale
			String Line = "\nSales History of Product Type: " + productSales.getKey();
			messageWriter.writeMessageLine(Line);
			for (String history : productSales.getValue().getHistory()) {
				messageWriter.writeMessageLine(history);
			}
			Line = "## Summary of Product- Type: " + productSales.getKey() + ", Total Value: " + productSales.getValue().getTotalValue()
					+ ", Number of Sales:" + productSales.getValue().getNumberOfSales();
			messageWriter.writeMessageLine(Line);
		}
	}
}
