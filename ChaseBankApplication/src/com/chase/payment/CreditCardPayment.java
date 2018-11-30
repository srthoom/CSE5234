package com.chase.payment;

import java.io.Serializable;

public class CreditCardPayment implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	String cardHolderName;
	String cardNumber;
	String cvvCode;
	String expiryDate;
	
	public CreditCardPayment() {
		
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvvCode() {
		return cvvCode;
	}

	public void setCvvCode(String cvvCode) {
		this.cvvCode = cvvCode;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

}
