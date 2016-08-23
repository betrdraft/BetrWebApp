package com.betr.server.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betr.server.domain.User;
import com.betr.server.exception.PaymentException;
import com.betr.server.payment.PaymentExternalAPI;
import com.betr.server.payment.domain.PaymentEntry;
import com.betr.server.payment.domain.PaymentTransaction;
import com.betr.server.util.UserUtil;

@Controller
public class PaymentResource {

	@RequestMapping(value = "/api/payment/register", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> registerPaymentEntry(PaymentEntry entry) {
		User user = UserUtil.getUser();
		
		PaymentExternalAPI paymentExternalAPI = new PaymentExternalAPI(user.getId());
		
		try {
			paymentExternalAPI.processPaymentEntry(entry);
		} catch (PaymentException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@RequestMapping(value = "/api/payment/transaction", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> processPaymentTransaction(PaymentTransaction transaction) {
		User user = UserUtil.getUser();
		
		PaymentExternalAPI paymentExternalAPI = new PaymentExternalAPI(user.getId());
		
		try {
			paymentExternalAPI.processTransaction(transaction);
		} catch (PaymentException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@RequestMapping(value = "/api/payment/transaction", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> getPaymentTransactions() {
		User user = UserUtil.getUser();
		
		PaymentExternalAPI paymentExternalAPI = new PaymentExternalAPI(user.getId());
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(paymentExternalAPI.getTransactions().toString());
		} catch (PaymentException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
}
