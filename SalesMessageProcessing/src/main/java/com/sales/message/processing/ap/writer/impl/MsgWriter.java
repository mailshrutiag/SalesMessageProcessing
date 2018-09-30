package com.sales.message.processing.ap.writer.impl;

import com.sales.message.processing.ap.MessageWriter;

/**
 * Class to output to Console (Common for Console and File Types) - Implements MessageWriter
 * @author Shruti Agarwal 
 */
public class MsgWriter implements MessageWriter {

	public void writeMessageLine(String messageLine) {
		System.out.println(messageLine);
	}
}
