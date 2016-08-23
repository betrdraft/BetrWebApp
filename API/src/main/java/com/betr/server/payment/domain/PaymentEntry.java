package com.betr.server.payment.domain;

import java.util.Date;

//TODO SECURITY AROUND THIS!!! TODO
public class PaymentEntry {

	//TODO SECURITY AROUND THIS!!! TODO
	private String ccNumber;
	private Date expirationDate;
	private String fullName;
	
	public PaymentEntry(String ccNumber, Date expirationDate, String fullName) {
		this.ccNumber = ccNumber;
		this.expirationDate = expirationDate;
		this.fullName = fullName;
	}
	public String getCcNumber() {
		return ccNumber;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public String getFullName() {
		return fullName;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}
