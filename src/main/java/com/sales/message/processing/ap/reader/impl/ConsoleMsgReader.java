package com.sales.message.processing.ap.reader.impl;

import java.util.Scanner;

import com.sales.message.processing.ap.MessageReader;

/**
 * Class to Read input from Console - Implements MessageReader 
 * @author Shruti Agarwal 
 */
public class ConsoleMsgReader implements MessageReader {
	private Scanner msgIn;

	public ConsoleMsgReader() {
		msgIn = new Scanner(System.in);
	}

	public String getNextMessageLine() {
		return msgIn.nextLine();
	}

	public boolean hasNext() {
		return msgIn.hasNext();
	}

	public void close() {
		msgIn.close();
	}
}
