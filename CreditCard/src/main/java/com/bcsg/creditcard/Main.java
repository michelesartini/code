package com.bcsg.creditcard;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.bcsg.creditcard.card.CardType;
import com.bcsg.creditcard.card.CreditCard;
import com.bcsg.creditcard.card.CreditCardException;

public class Main {

	
	public static void main(String[] args) throws ParseException, CreditCardException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
		
		Date visaDate = sdf.parse("Oct-2017");
		CreditCard visa = new CreditCard.CreditCardBuilder()
				.bank("Royal Bank of Canada").name("test visa")
				.cartType(CardType.VISA).ccv(123)
				.cardNumber("4519-4532-4524-2456").expiryDate(visaDate).build();
		
		Date mastercardDate = sdf.parse("Nov-2017");
		CreditCard mastercard = new CreditCard.CreditCardBuilder()
				.bank("HSBC Canada").name("test mastercard")
				.cartType(CardType.MASTERCARD).ccv(123)
				.cardNumber("5601-2345-3446-5678")
				.expiryDate(mastercardDate)
				.build();
		
		Date amexDate = sdf.parse("Dec-2018");
		CreditCard amex = new CreditCard.CreditCardBuilder()
				.bank("American Express").name("test amex")
				.cartType(CardType.AMEX).ccv(123)
				.cardNumber("3786-7334-8965-345").expiryDate(amexDate).build();
		
		List<CreditCard> list = new ArrayList<CreditCard>();
		list.add(visa);
		list.add(mastercard);
		list.add(amex);
		
		Collections.sort(list);
		String format = "%-30.20s  %-30.20s  %-30.20s%n";
		System.out.printf(format, "Bank", "Card Number", "Expiring date");
		System.out.printf(format, "--------------------", "-------------------", "-------------");
		
		for (CreditCard card : list) {
			System.out.printf(format, card.getBank(), card.getMaskedCardNumber(), card.getExpiryDate());
		}
	}
}
