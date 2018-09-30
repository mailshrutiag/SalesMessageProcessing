package com.sales.message.processing.model.sales;

import java.util.ArrayList;
import java.util.List;

public class SalesRecorded {
	private List<Sale> sales;
	private List<String> history;

	public enum UpdateType {
		ADD, SUBTRACT, MULTIPLY
	}

	public SalesRecorded() {
		this.sales = new ArrayList<Sale>();
		this.history = new ArrayList<String>();
	}

	public void addSaleToProduct(Sale sale) {
		sales.add(sale);
	}

	public int getTotalValue() {
		int totalSales = 0;
		for (Sale sale : sales) {
			totalSales += sale.getValue();
		}
		return totalSales;
	}

	public int getNumberOfSales() {
		return sales.size();
	}

	public void addDuplicatedSalesToProduct(Sale sale, int n) {
		for (int i = 0; i < n; i++)
			sales.add(new Sale(sale));
	}

	public void addMultiplesSalesToProduct(List<Sale> sales) {
		this.sales.addAll(sales);
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void updateAllSales(UpdateType updateType, int changeValue) {
		switch (updateType) {
		case ADD:
			for (int i = 0; i < sales.size(); i++)
				sales.get(i).addValue(changeValue);
			break;
		case SUBTRACT:
			for (int i = 0; i < sales.size(); i++)
				sales.get(i).subValue(changeValue);
			break;
		case MULTIPLY:
			for (int i = 0; i < sales.size(); i++)
				sales.get(i).multValue(changeValue);
			break;
		}
	}

	public List<String> getHistory() {
		return history;
	}

	public void addHistoryRecord(String historyRecord) {
		this.history.add(historyRecord);
	}
}
