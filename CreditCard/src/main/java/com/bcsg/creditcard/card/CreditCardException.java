package com.bcsg.creditcard.card;

public class CreditCardException extends Exception {
	
	private static final String error = "Mismatch between credit card number and credit card type.";
	
	public CreditCardException(String message) {
		super(message);
	}
	
	public CreditCardException() {
		super(error);
	}
}
