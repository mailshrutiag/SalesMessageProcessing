package com.sales.message.processing.ap.reader.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.sales.message.processing.ap.MessageReader;

/**
 * Class to Read input from File - Implements MessageReader 
 * @author Shruti Agarwal 
 */
public class TextFileMsgReader implements MessageReader {
	
	List<String> txtFileMsgs = new ArrayList<String>();
	private int position = 0;
	
	private static final String inputFilePath = "src/main/resources/messagesInput.txt";

	public TextFileMsgReader() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
			String line = reader.readLine();
			while (line != null) {
				txtFileMsgs.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public String getNextMessageLine() {
		return txtFileMsgs.get(position++);
	}

	public boolean hasNext() {
		return (position < txtFileMsgs.size());
	}

	public void close() {
		txtFileMsgs.clear();
	}
}
