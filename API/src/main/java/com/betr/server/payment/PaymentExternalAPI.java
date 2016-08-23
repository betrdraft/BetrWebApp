package com.betr.server.payment;

import java.util.List;

import com.betr.server.exception.PaymentException;
import com.betr.server.game.domain.NFLContestEntry;
import com.betr.server.payment.domain.PaymentEntry;
import com.betr.server.payment.domain.PaymentTransaction;

public class PaymentExternalAPI {

	//Stubs to implement the API
	public PaymentExternalAPI(int userId) {
		
	}
	
	public void processContestEntry(NFLContestEntry entry) throws PaymentException {
		
	}
	
	public boolean userHasFunds(int buyIn) throws PaymentException {
		return false;
	}
	
	public void processTransaction(PaymentTransaction transaction) throws PaymentException {
		
	}
	
	public List<PaymentTransaction> getTransactions() throws PaymentException {
		return null;
	}
	
	public void processPaymentEntry(PaymentEntry entry) throws PaymentException {
		
	}
	
	public PaymentEntry retrievePaymentEntry() throws PaymentException {
		return null;
	}
}
