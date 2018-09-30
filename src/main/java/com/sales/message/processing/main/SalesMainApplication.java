package com.sales.message.processing.main;

import java.util.Scanner;

import com.sales.message.processing.manager.SaleApplicationManager;

/**
 * Sales Notification Messages Processing Standalone Application
 * @author Shruti Agarwal
 */
public class SalesMainApplication {
	public static void main(String[] args) throws InterruptedException {
		Scanner in = new Scanner( System.in );
		int iterationsCount = 0;
		boolean exitFlag = false;
		
		System.out.println("Welcome to Sales Messages Processing Application!");
		System.out.println("\nPlease Select the mode of Message Input from the following-"
				+ "\n1) Process Messages from Resource file (messagesInput.txt)\n2) Process Messages from entered in Console");
		System.out.print("\nSelection: ");
		
		int selection = in.nextInt();	//User Selection from Menu
		SaleApplicationManager fileAppManager = new SaleApplicationManager("file");
		SaleApplicationManager consoleAppManager = new SaleApplicationManager("console");
		while(true) {					//Looks for Message text- "exit" to end Application
			if(iterationsCount > 0)
				System.out.println("\n####################### Resuming Application #######################");			
			switch (selection) {
		      case 1:
		    	  System.out.println("\nProcessing Received Messages from File");
		    	  fileAppManager.process();
		    	  exitFlag = fileAppManager.getMessageProcessor().isExitAppStatus();
		    	  if(exitFlag)  {
		    		  System.out.println("\nEnd of File Reached, Exiting the Application!");
		    	  }
		    	  break;
		      case 2:
		    	  System.out.print("\nPlease Enter Messages to be Processed-");
		    	  consoleAppManager.process();			
		    	  exitFlag = consoleAppManager.getMessageProcessor().isExitAppStatus();
		    	  break;
		      default:
		    	  System.err.println("Unrecognized option. Exiting Application!!!");
		    	  exitFlag = true;
		    	  break;
		    }
			iterationsCount++;
			if(exitFlag) {
				fileAppManager.getMessageProcessor().getMessageReader().close();
				consoleAppManager.getMessageProcessor().getMessageReader().close();
				break;
			}		
		}
		in.close();
	}
}
