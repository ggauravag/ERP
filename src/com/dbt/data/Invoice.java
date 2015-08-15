package com.dbt.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dbt.support.Email;
import com.dbt.support.NumberToWord;
import com.dbt.support.Utils;

public class Invoice {
	Order order;
	double tax, total, grandTotal;
	double vatPercent;
	boolean hasVat;
	private int id;
	Date printDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPrintDate() {
		return printDate;
	}

	public void setPrintDate(String date) {
		if (date == null || "".equals(date)) {
			this.printDate = new java.util.Date();
		} else {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			try {
				printDate = format.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				this.printDate = new java.util.Date();
				e.printStackTrace();
				Email.sendExceptionReport(e);
			}
		}
	}

	public String getTotalInWords() {
		return new NumberToWord().convertNumberToWords((int) grandTotal);
	}

	public String getPrintableDate() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String date = format.format(printDate);
		return date;
	}

	public boolean hasVAT() {
		return hasVat;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
		this.total = order.getAmount();
		this.grandTotal = this.total;
	}

	public String getTax() {
		return Utils.customFormat("###,###,###.###", tax);
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public String getTotal() {
		return Utils.customFormat("###,###,###.###", total);
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getGrandTotal() {
		return Utils.customFormat("###,###,###.###", grandTotal);
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public double getVatPercent() {
		return vatPercent;
	}

	public void setVatPercent(double vatPercent) {
		this.vatPercent = vatPercent;
		this.tax = (this.total * this.vatPercent) / 100.0;
		this.grandTotal = this.total + this.tax;
	}

	public void setHasVat(boolean hasVat) {
		this.hasVat = hasVat;
	}

}
