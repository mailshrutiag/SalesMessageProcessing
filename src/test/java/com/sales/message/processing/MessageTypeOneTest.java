package com.sales.message.processing;

import static org.junit.Assert.*;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.sales.message.processing.model.message.Message;
import com.sales.message.processing.model.message.MessageTypeOne;
import com.sales.message.processing.model.sales.SalesRecorded;

/**
 * JUNIT Test Class for Message Type 1 
 * @author Shruti Agarwal
 */
public class MessageTypeOneTest {
	private Message messageTypeOne;
	private Map<String, SalesRecorded> salesRecordedMap;

	@Before
	public void setUp() {
		messageTypeOne = new MessageTypeOne();
		salesRecordedMap = new LinkedHashMap<String, SalesRecorded>();
	}

	@Test
	public void Test1ValidMessageTypeOne() {
		String messageLine = "apple at 10p";
		boolean valid = messageTypeOne.isMsgValid(messageLine);
		assertEquals(valid, true);
	}
	
	@Test
	public void Test2ValidMessageTypeOne() {
		String messageLine = "abcd";
		boolean valid = messageTypeOne.isMsgValid(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void Test3ValidMessageTypeOne() {
		String messageLine = "apple at 10";
		boolean valid = messageTypeOne.isMsgValid(messageLine);
		assertEquals(valid, false);
	}
	
	@Test
	public void Test4ValidMessageTypeOne() {
		String messageLine = "20 sales of oranges at 41p each";
		boolean valid = messageTypeOne.isMsgValid(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void Test5ValidMessageTypeOne() {
		String messageLine = "Multiply 20p apples";
		boolean valid = messageTypeOne.isMsgValid(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void Test6MessageTypeOne() {
		String messageLine = "orange at 10p";
		salesRecordedMap.put("orange", new SalesRecorded());
		messageTypeOne.process(messageLine, salesRecordedMap, 1);
		messageLine = "orange at 10p";
		messageTypeOne.process(messageLine, salesRecordedMap, 2);
		int numberOfSales = salesRecordedMap.get("orange").getNumberOfSales();
		int totalValue = salesRecordedMap.get("orange").getTotalValue();
		assertEquals(numberOfSales, 2);
		assertEquals(totalValue, 20);
	}
}
