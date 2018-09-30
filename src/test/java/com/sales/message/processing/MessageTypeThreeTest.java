package com.sales.message.processing;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.sales.message.processing.model.message.Message;
import com.sales.message.processing.model.message.MessageTypeOne;
import com.sales.message.processing.model.message.MessageTypeThree;
import com.sales.message.processing.model.message.MessageTypeTwo;
import com.sales.message.processing.model.sales.SalesRecorded;

/**
 * JUNIT Test Class for Message Type 3
 * @author Shruti Agarwal
 */
public class MessageTypeThreeTest {
	Message messageTypeThree, messageTypeTwo, messageTypeOne;
	private Map<String, SalesRecorded> salesRecordedMap;

	@Before
	public void setUp() {
		messageTypeThree = new MessageTypeThree();
		messageTypeTwo = new MessageTypeTwo();
		messageTypeOne = new MessageTypeOne();
		salesRecordedMap = new LinkedHashMap<String, SalesRecorded>();
	}

	@Test
	public void Test1ValidMessageTypeThree() {
		String messageLine = "";
		boolean valid = messageTypeThree.isMsgValid(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void Test2ValidMessageTypeThree() {
		String messageLine = "abcd";
		boolean valid = messageTypeThree.isMsgValid(messageLine);
		assertEquals(valid, false);
	}
	
	@Test
	public void Test3ValidMessageTypeThree() {
		String messageLine = "apple at 10p";
		boolean valid = messageTypeThree.isMsgValid(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void Test4ValidMessageTypeThree() {
		String messageLine = "20 sales of oranges at 10p each";
		boolean valid = messageTypeThree.isMsgValid(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void Test6ValidMessageTypeThree() {
		String messageLine = " 20p dates";
		boolean valid = messageTypeThree.isMsgValid(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void Test7MessageTypeThree() {			// Testing with Message Type 1
		String messageLine = "apple at 10p";
		salesRecordedMap.put("apple", new SalesRecorded());
		messageTypeOne.process(messageLine, salesRecordedMap, 1);
		messageLine = "Multiply 20p apples";
		messageTypeThree.process(messageLine, salesRecordedMap, 2);
		int numberOfSales = salesRecordedMap.get("apple").getNumberOfSales();
		int totalValue = salesRecordedMap.get("apple").getTotalValue();
		assertEquals(numberOfSales, 1);
		assertEquals(totalValue, 200);
	}

	@Test
	public void Test8MessageTypeThree() {			// Testing with Message Type 2
		String messageLine = "10 sales of oranges at 10p each";
		salesRecordedMap.put("orange", new SalesRecorded());
		messageTypeTwo.process(messageLine, salesRecordedMap, 1);
		messageLine = "Add 30p oranges";
		messageTypeThree.process(messageLine, salesRecordedMap, 2);
		int numberOfSales = salesRecordedMap.get("orange").getNumberOfSales();
		int totalValue = salesRecordedMap.get("orange").getTotalValue();
		assertEquals(numberOfSales, 10);
		assertEquals(totalValue, 400);
	}
}
