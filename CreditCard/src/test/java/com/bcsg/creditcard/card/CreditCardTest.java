package com.bcsg.creditcard.card;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.bcsg.creditcard.card.CardType;
import com.bcsg.creditcard.card.CreditCard;


public class CreditCardTest {
	
	private String visaCreditCardNumber = "4519-4532-4524-2456";
	private String visaCreditCardNumberMasked = "4519-xxxx-xxxx-xxxx";
	
	private String mastercardCreditCardNumber = "5601-2345-3446-5678";
	private String mastercardCreditCardNumberMasked = "56xx-xxxx-xxxx-xxxx";
	
	private String amexCreditCardNumber = "3786-7334-8965-345";
	private String amexCreditCardNumberMasked = "xxxx-xxxx-xxxx-345";
	
	// -------------------- Visa --------------------
	
	/**
	 * Verifies that the a Visa card number gets normalised properly when it is already in the right format.
	 * @throws CreditCardException 
	 */
	@Test
	public void normaliseCardNumberVisaTestCorrect() throws CreditCardException {
		String generatedNumber = CreditCard.normaliseCardNumber(visaCreditCardNumber, CardType.VISA);
		assertEquals(visaCreditCardNumber, generatedNumber);
	}
	
	/**
	 * Verifies that the a Visa card number gets normalised properly when it doesn't have "-" separators.
	 * @throws CreditCardException 
	 */
	@Test
	public void normaliseCardNumberVisaTestCorrect2() throws CreditCardException {
		String card = visaCreditCardNumber.replaceAll("-", "");
		String generatedNumber = CreditCard.normaliseCardNumber(card, CardType.VISA);
		assertEquals(visaCreditCardNumber, generatedNumber);
	}
	
	/**
	 * Verifies a condition error while normalising a credit card number with the wrong card type.
	 * @throws CreditCardException 
	 */
	@Test(expected=CreditCardException.class)
	public void normaliseCardNumberVisaTestNotCorrect() throws CreditCardException {
		CreditCard.normaliseCardNumber(visaCreditCardNumber, CardType.AMEX);
	}
	
	/**
	 * Verifies that the number is correctly masked.
	 * @throws CreditCardException 
	 */
	@Test
	public void maskCardNumberVisaTestCorrect() throws CreditCardException { 
		CreditCard visa = new CreditCard.CreditCardBuilder()
				.cartType(CardType.VISA).cardNumber(visaCreditCardNumber)
				.ccv(123).expiryDate(new Date()).name("test visa").build();
		
		String generatedNumber = visa.maskNumber();
		assertEquals(visaCreditCardNumberMasked, generatedNumber);
	}
	
	/**
	 * Verifies a condition error while masking a card number with a wrong card type.
	 * @throws CreditCardException 
	 */
	@Test(expected=CreditCardException.class)
	public void maskCardNumberVisaTestNotCorrect() throws CreditCardException { 
		CreditCard visa = new CreditCard.CreditCardBuilder()
				.cartType(CardType.AMEX).cardNumber(visaCreditCardNumber)
				.ccv(123).expiryDate(new Date()).name("test visa error")
				.build();
		
		visa.maskNumber();
	}

	// -------------------- Mastercard --------------------  
	
	/**
	 * Verifies that a Mastercard card number gets normalised properly when it is already in the right format.
	 * @throws CreditCardException 
	 */
	@Test
	public void normaliseCardNumberMastercardTestCorrect() throws CreditCardException {
		String generatedNumber = CreditCard.normaliseCardNumber(mastercardCreditCardNumber, CardType.MASTERCARD);
		assertEquals(mastercardCreditCardNumber, generatedNumber);
	}
	
	/**
	 * Verifies that a Mastercard card number gets normalised properly when it doesn't have "-" separators.
	 * @throws CreditCardException 
	 */
	@Test
	public void normaliseCardNumberMastercardTestCorrect2() throws CreditCardException {
		String card = mastercardCreditCardNumber.replaceAll("-", "");
		String generatedNumber = CreditCard.normaliseCardNumber(card, CardType.MASTERCARD);
		assertEquals(mastercardCreditCardNumber, generatedNumber);
	}
	
	/**
	 * Verifies a condition error while normalising a credit card number with the wrong card type.
	 * @throws CreditCardException 
	 */
	@Test(expected=CreditCardException.class)
	public void normaliseCardNumberMastercardTestNotCorrect() throws CreditCardException {
		CreditCard.normaliseCardNumber(mastercardCreditCardNumber, CardType.AMEX);
	}
	
	/**
	 * Verifies that the number is correctly masked.
	 * @throws CreditCardException 
	 */
	@Test
	public void maskCardNumberMastercardTestCorrect() throws CreditCardException { 
		CreditCard mastercard = new CreditCard.CreditCardBuilder()
				.cartType(CardType.MASTERCARD)
				.cardNumber(mastercardCreditCardNumber).ccv(123)
				.expiryDate(new Date()).name("test mastercard").build();
		
		String generatedNumber = mastercard.maskNumber();
		assertEquals(mastercardCreditCardNumberMasked, generatedNumber);
	}
	
	/**
	 * Verifies a condition error while masking a credit card number with a wrong card type.
	 * @throws CreditCardException 
	 */
	@Test(expected=CreditCardException.class)
	public void maskCardNumberMastercardTestNotCorrect() throws CreditCardException { 
		CreditCard mastercard = new CreditCard.CreditCardBuilder()
				.cartType(CardType.VISA)
				.cardNumber(mastercardCreditCardNumber).ccv(123)
				.expiryDate(new Date()).name("test mastercard error").build();
		
		mastercard.maskNumber();
	}
	
	// -------------------- Amex --------------------

	/**
	 * Verifies that an Amex card number gets normalised properly when it is already in the right format.
	 * @throws CreditCardException 
	 */
	@Test
	public void normaliseCardNumberAmexTestCorrect() throws CreditCardException {
		String generatedNumber = CreditCard.normaliseCardNumber(amexCreditCardNumber, CardType.AMEX);
		assertEquals(amexCreditCardNumber, generatedNumber);
	}
	
	/**
	 * Verifies that an Amex card number gets normalised properly when it doesn't have "-" separators.
	 * @throws CreditCardException 
	 */
	@Test
	public void normaliseCardNumberAmexTestCorrect2() throws CreditCardException {
		String card = amexCreditCardNumber.replaceAll("-", "");
		String generatedNumber = CreditCard.normaliseCardNumber(card, CardType.AMEX);
		assertEquals(amexCreditCardNumber, generatedNumber);
	}
	
	/**
	 * Verifies a condition error while normalising a credit card number with the wrong card type.
	 */
	@Test(expected=CreditCardException.class)
	public void normaliseCardNumberAmexTestNotCorrect() throws CreditCardException {
		CreditCard.normaliseCardNumber(amexCreditCardNumber, CardType.MASTERCARD);
	}
	
	/**
	 * Verifies that the number is correctly masked.
	 * @throws CreditCardException 
	 */
	@Test
	public void maskCardNumberAmexTestCorrect() throws CreditCardException { 
		CreditCard amex = new CreditCard.CreditCardBuilder()
				.cartType(CardType.AMEX).cardNumber(amexCreditCardNumber)
				.ccv(123).expiryDate(new Date()).name("test amex").build();
		
		String generatedNumber = amex.maskNumber();
		assertEquals(amexCreditCardNumberMasked, generatedNumber);
	}
	
	/**
	 * Verifies a condition error while masking a credit card number with a wrong card type.
	 * @throws CreditCardException 
	 */
	@Test(expected=CreditCardException.class)
	public void maskCardNumberAmexTestNotCorrect() throws CreditCardException { 
		CreditCard amex = new CreditCard.CreditCardBuilder()
				.cartType(CardType.MASTERCARD).cardNumber(amexCreditCardNumber)
				.ccv(123).expiryDate(new Date()).name("test amex error").build();
		
		amex.maskNumber();
	}
	
	// -------------------- Compare dates --------------------
	
	/**
	 * Verifies that "Nov-2017" > "Oct-2017" therefore the compareTo must return 1.
	 * 
	 * @throws CreditCardException
	 * @throws ParseException
	 */
	@Test
	public void compareToCorrect() throws CreditCardException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
		Date before = sdf.parse("Oct-2017");
		CreditCard amex = new CreditCard.CreditCardBuilder()
				.cartType(CardType.AMEX).cardNumber(amexCreditCardNumber)
				.ccv(123).expiryDate(before).name("test amex").build();
		
		Date after = sdf.parse("Nov-2017");
		CreditCard amex2 = new CreditCard.CreditCardBuilder()
				.cartType(CardType.AMEX).cardNumber(amexCreditCardNumber)
				.ccv(123).expiryDate(after).name("test amex").build();
		
		assertEquals(amex.compareTo(amex2), 1);
	}
	
	/**
	 * Verifies that "Oct-2017" == "Oct-2017" therefore the compareTo must return 0.
	 * 
	 * @throws CreditCardException
	 * @throws ParseException
	 */
	@Test
	public void compareToCorrectEqualDates() throws CreditCardException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
		Date before = sdf.parse("Oct-2017");
		CreditCard amex = new CreditCard.CreditCardBuilder()
				.cartType(CardType.AMEX).cardNumber(amexCreditCardNumber)
				.ccv(123).expiryDate(before).name("test amex").build();
		
		CreditCard amex2 = new CreditCard.CreditCardBuilder()
				.cartType(CardType.AMEX).cardNumber(amexCreditCardNumber)
				.ccv(123).expiryDate(before).name("test amex").build();
		
		assertEquals(amex.compareTo(amex2), 0);
		assertEquals(amex.compareTo(amex), 0);
		assertEquals(amex2.compareTo(amex), 0);
		assertEquals(amex2.compareTo(amex2), 0);
	}
	
	/**
	 * Verifies that in a error scenario where a card doesn't have an expiring date the value returned is 1.
	 *
	 * @throws CreditCardException
	 * @throws ParseException
	 */
	@Test
	public void compareToCorrectNullDate() throws CreditCardException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
		Date before = sdf.parse("Oct-2017");
		CreditCard amex = new CreditCard.CreditCardBuilder()
				.cartType(CardType.AMEX).cardNumber(amexCreditCardNumber)
				.ccv(123).expiryDate(before).name("test amex").build();
		
		CreditCard amex2 = new CreditCard.CreditCardBuilder()
				.cartType(CardType.AMEX).cardNumber(amexCreditCardNumber)
				.ccv(123).expiryDate(null).name("test amex").build();

		assertEquals(amex.compareTo(amex2), 1);
	}
	
	/**
	 * Verifies that comparing a card against null, the value returned is 1.
	 *
	 * @throws CreditCardException
	 * @throws ParseException
	 */
	@Test
	public void compareToCorrectNullCard() throws CreditCardException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
		Date before = sdf.parse("Oct-2017");
		CreditCard amex = new CreditCard.CreditCardBuilder()
				.cartType(CardType.AMEX).cardNumber(amexCreditCardNumber)
				.ccv(123).expiryDate(before).name("test amex").build();
		
		assertEquals(amex.compareTo(null), 1);
	}
}
