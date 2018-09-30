package com.sales.message.processing.manager;

import com.sales.message.processing.ap.MessageReader;
import com.sales.message.processing.ap.MessageWriter;
import com.sales.message.processing.ap.reader.impl.ConsoleMsgReader;
import com.sales.message.processing.ap.reader.impl.TextFileMsgReader;
import com.sales.message.processing.ap.writer.impl.MsgWriter;
import com.sales.message.processing.processor.MessageProcessor;
import com.sales.message.processing.processor.ReportProcessor;

/**
 * Manager for Sales Application
 *   1. Define Input/Output methods (Console/File)
 *   2. Define Message/Report processors
 *   
 * @author Shruti Agarwal
 */
public class SaleApplicationManager {
	
	private MessageProcessor messageProcessor;
	private ReportProcessor reportProcessor;
	private MessageReader msgReader;
	private MessageWriter msgWriter;
	
	public SaleApplicationManager(String inputType) {
		this.msgReader = inputType.equals("file") ? new TextFileMsgReader() : new ConsoleMsgReader();
		this.msgWriter = new MsgWriter();
		this.reportProcessor = new ReportProcessor(msgWriter);
		this.messageProcessor = new MessageProcessor(msgReader, msgWriter, reportProcessor);
	}

	public void process() throws InterruptedException {
		messageProcessor.startMessageProcessing();
	}

	public MessageProcessor getMessageProcessor() {
		return messageProcessor;
	}	
}
