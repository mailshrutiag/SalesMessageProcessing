package com.sales.message.processing.processor;

import java.util.Map;

import com.sales.message.processing.ap.MessageWriter;
import com.sales.message.processing.constant.SalesProcessingConstants;
import com.sales.message.processing.model.report.Report;
import com.sales.message.processing.model.report.ReportAfter10Msgs;
import com.sales.message.processing.model.report.ReportAfter50Msgs;
import com.sales.message.processing.model.sales.SalesRecorded;

/**
 * Report Processor Class   
 * @author Shruti Agarwal
 */
public class ReportProcessor {

	private Report reportAfter10Msg;
	private Report reportAfter50Msg;

	private Map<String, SalesRecorded> salesRecordedMap;
	private MessageWriter messageWriter;

	public ReportProcessor(MessageWriter messageWriter) {
		this.messageWriter = messageWriter;
		initalizeReports();
	}

	private void initalizeReports() {
		reportAfter10Msg = new ReportAfter10Msgs();
		reportAfter50Msg = new ReportAfter50Msgs();
	}

	public void processReportAfter10Msgs(int count) {
		if (count % SalesProcessingConstants.MSG_10 == 0) {
			reportAfter10Msg.process(salesRecordedMap, count, this.messageWriter);
		}
	}

	public void processReportAfter50Msgs(int count) {
		reportAfter50Msg.process(salesRecordedMap, count, this.messageWriter);
	}

	public Report getReportAfter10Msg() {
		return reportAfter10Msg;
	}

	public Map<String, SalesRecorded> getSalesRecordedMap() {
		return salesRecordedMap;
	}

	public void setProductsList(Map<String, SalesRecorded> productsList) {
		this.salesRecordedMap = productsList;
	}

}
