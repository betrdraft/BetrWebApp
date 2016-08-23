package com.betr.server.payment.domain;

public class PaymentTransaction {

	private long amount;
	private String description;
	private PaymentTransactionType type;
	
	public PaymentTransaction(long amount, String description, PaymentTransactionType type) {
		this.amount = amount;
		this.description = description;
		this.type = type;
	}
	public long getAmount() {
		return amount;
	}
	public String getDescription() {
		return description;
	}
	public PaymentTransactionType getType() {
		return type;
	}
	
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setType(PaymentTransactionType type) {
		this.type = type;
	}
}
