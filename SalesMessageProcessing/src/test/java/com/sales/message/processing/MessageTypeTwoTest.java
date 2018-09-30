package com.sales.message.processing;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.sales.message.processing.model.message.Message;
import com.sales.message.processing.model.message.MessageTypeTwo;
import com.sales.message.processing.model.sales.SalesRecorded;

/**
 * JUNIT Test Class for Message Type 2
 * @author Shruti Agarwal
 */
public class MessageTypeTwoTest {
	Message messageTypeTwo;
	private Map<String, SalesRecorded> salesRecordedMap;

	@Before
	public void setUp() {
		messageTypeTwo = new MessageTypeTwo();
		salesRecordedMap = new LinkedHashMap<String, SalesRecorded>();
	}

	@Test
	public void Test1ValidMessageTypeTwo() {
		String messageLine = "asdsds";
		boolean valid = messageTypeTwo.isMsgValid(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void Test2ValidMessageTypeTwo() {
		String messageLine = "apple at 10p";
		boolean valid = messageTypeTwo.isMsgValid(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void Test3ValidMessageTypeTwo() {
		String messageLine = "Multiply 20p apples";
		boolean valid = messageTypeTwo.isMsgValid(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void Test4ValidMessageTypeTwo() {
		String messageLine = "20 sales of oranges at 10p each";
		boolean valid = messageTypeTwo.isMsgValid(messageLine);
		assertEquals(valid, true);
	}

	@Test
	public void Test5ValidMessageTypeTwo() {
		String messageLine = "20 sales of oranges at 10 each";
		boolean valid = messageTypeTwo.isMsgValid(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void Test6ValidMessageTypeTwo() {
		String messageLine = "20 sales of oranges at 10p eachs";
		boolean valid = messageTypeTwo.isMsgValid(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void Test7ValidMessageTypeTwo() {
		String messageLine = " sales of oranges at 10p each";
		boolean valid = messageTypeTwo.isMsgValid(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void Test8MessageTypeTwo() {
		String messageLine = "20 sales of apples at 10p each";
		salesRecordedMap.put("apple", new SalesRecorded());
		messageTypeTwo.process(messageLine, salesRecordedMap, 1);
		int numberOfSales = salesRecordedMap.get("apple").getNumberOfSales();
		int totalValue = salesRecordedMap.get("apple").getTotalValue();
		assertEquals(numberOfSales, 20);
		assertEquals(totalValue, 200);
	}
}
