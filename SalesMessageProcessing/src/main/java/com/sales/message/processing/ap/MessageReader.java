package com.sales.message.processing.ap;

/**
 * Input Interface
 * @author Shruti Agarwal 
 */
public interface MessageReader {

	public String getNextMessageLine();

	/**
	 * check if message reader has next line 
	 * @return boolean
	 */
	public boolean hasNext();

	/**
	 * The method is called to close any opened buffer
	 */
	public void close();
}
