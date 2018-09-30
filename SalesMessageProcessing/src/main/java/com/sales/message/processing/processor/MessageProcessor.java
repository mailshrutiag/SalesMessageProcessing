package com.sales.message.processing.processor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sales.message.processing.api.MessageReader;
import com.sales.message.processing.api.MessageWriter;
import com.sales.message.processing.api.reader.impl.TextFileMsgReader;
import com.sales.message.processing.constant.SalesProcessingConstants;
import com.sales.message.processing.model.message.Message;
import com.sales.message.processing.model.message.MessageTypeOne;
import com.sales.message.processing.model.message.MessageTypeThree;
import com.sales.message.processing.model.message.MessageTypeTwo;
import com.sales.message.processing.model.sales.SalesRecorded;

import java.util.concurrent.TimeUnit;

/**
 * Message Processor Class   
 * @author Shruti Agarwal
 */
public class MessageProcessor {

	private MessageReader messageReader;
	private MessageWriter messgeWriter;
	int appMessageCount = 0;
	boolean exitAppStatus = false;
		
	private ReportProcessor reportProcessor;
	private List<Message> messagesList;
	private Map<String, SalesRecorded> salesRecordedMap;

	public MessageProcessor(MessageReader messageReader, MessageWriter messgeWriter, ReportProcessor reportProcessor) {
		initalizeMessages();
		this.messageReader = messageReader;
		this.messgeWriter = messgeWriter;
		this.reportProcessor = reportProcessor;
		salesRecordedMap = new LinkedHashMap<String, SalesRecorded>();
		this.reportProcessor.setProductsList(salesRecordedMap);
	}

	/**
	 * This method initializes Messages and its type
	 */
	private void initalizeMessages() {
		messagesList = new ArrayList<Message>();
		Message messageTypeOne = new MessageTypeOne();
		Message messageTypeTwo = new MessageTypeTwo();
		Message messageTypeThree = new MessageTypeThree();

		messagesList.add(messageTypeOne);
		messagesList.add(messageTypeTwo);
		messagesList.add(messageTypeThree);
	}

	public void startMessageProcessing() throws InterruptedException {
		int messageCount = 0;
		while ((messageCount < SalesProcessingConstants.MSG_50) && messageReader.hasNext()) {
			String messageLine = messageReader.getNextMessageLine();
			if(messageLine.equals("exit")) {
				System.err.println("\"exit\" encountered, Ending the Application!!");
				this.exitAppStatus = true;
				return;
			}
			appMessageCount++;
			messageCount++;
			boolean validMessage = false;
			for (Message message : messagesList) {
				if (message.isMsgValid(messageLine))
					if (message.process(messageLine, salesRecordedMap, appMessageCount)) {
						validMessage = true;
						break;
					}
			}
			
			if (!validMessage) {
				messageCount--;
				appMessageCount--;
				this.messgeWriter.writeMessageLine("\nInvalid Message Type.!!! Please enter again...\n");
			}
			
			// check if reached 10 number of messages
			reportProcessor.processReportAfter10Msgs(appMessageCount);
		}
		// check if reached 50 number of messages
		if ((messageCount == SalesProcessingConstants.MSG_50)) {
			this.messgeWriter.writeMessageLine("\n**** 50 Messages Received! Reporting the Processed Sales-");
			reportProcessor.processReportAfter50Msgs(appMessageCount);
			System.out.println("\nApplication is Pausing for 1 minute. Please wait...");
			TimeUnit.SECONDS.sleep(60);			// Application sleeps for 1 minute
		}
		this.exitAppStatus = (messageReader instanceof TextFileMsgReader) ? !messageReader.hasNext() : false;
	}

	public ReportProcessor getReportProcessor() {
		return reportProcessor;
	}

	public MessageReader getMessageReader() {
		return messageReader;
	}

	public MessageWriter getMessgeWriter() {
		return messgeWriter;
	}

	public boolean isExitAppStatus() {
		return exitAppStatus;
	}
	
}
