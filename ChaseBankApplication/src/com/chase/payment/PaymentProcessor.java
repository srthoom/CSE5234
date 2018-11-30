package com.chase.payment;

public class PaymentProcessor {

	public String ping() {
		return "Pay";
	}
	
	public String processPayment(CreditCardPayment ccp) {
		String success="1";
		String failure="0";
		return success;
	}
}
