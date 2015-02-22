package com.bcsg.creditcard.card;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that contains the credit card information.
 * 
 * @author michelesartini
 *
 */
public class CreditCard implements Comparable<CreditCard> {
	
	private final String bank;
	private final String name;
	private final CardType type;
	private final int ccv;
	private final String cardNumber;
	private final String maskedCardNumber;
	private final static String cardPattern = "^([0-9]{4})([0-9]{4})([0-9]{4})([0-9]{3,4})$";
	private final String expiryDate;
	private final Date expDate;
	
	public CreditCard(CreditCardBuilder builder) throws CreditCardException {
		this.bank = builder.bank;
		this.name = builder.name;
		this.type = builder.type;
		this.ccv = builder.ccv;
		this.cardNumber = normaliseCardNumber(builder.cardNumber, builder.type);
		this.maskedCardNumber = maskNumber();
		this.expiryDate = builder.expiryDate;
		this.expDate = builder.expDate;
	}
	
	/**
	 * Normalises the card number to respect the following patterns:
	 * [0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}
	 * [0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{3}
	 * 
	 * @param cardNumber
	 * @return
	 * @throws CreditCardException 
	 */
	public static String normaliseCardNumber(String cardNumber, CardType type) throws CreditCardException {
		
		if (!cardNumber.matches(type.getCardPattern())) {
			throw new CreditCardException();
		}
		
		String toReturn = cardNumber;
		
		if (cardNumber != null && cardNumber.length() >= 15 ) {
			StringBuffer sb = new StringBuffer();
			Pattern pattern = Pattern.compile(cardPattern);
			Matcher matcher = pattern.matcher(cardNumber);
			if (matcher.find()) {
				sb.append(matcher.group(1) + "-");
				sb.append(matcher.group(2) + "-");
				sb.append(matcher.group(3) + "-");
				sb.append(matcher.group(4));
				toReturn = sb.toString();
			}
		}
		return toReturn;
	}

	/**
	 * Helper method that returns a masked card number.
	 * 
	 * @param cardNumber
	 * @param type
	 * @return
	 */
	protected String maskNumber() {
		Pattern pattern = Pattern.compile(this.type.getMaskedValuePattern());
		Matcher matcher = pattern.matcher(this.cardNumber);
		matcher.find();
		String value = matcher.group();
		return type.getMaskingPattern().replace(this.type.REPLACING_PLACE_HOLDER, value);
	}
	
	public int compareTo(CreditCard card) {
		int toReturn = 1;
		if (card != null && card.getExpDate() != null) {
			Date thisExpDate = this.getExpDate();
			toReturn = card.getExpDate().compareTo(thisExpDate);
		}
		return toReturn;
	}
	
	public String getBank() {
		return bank;
	}
	
	public String getName() {
		return name;
	}
	
	public CardType getType() {
		return type;
	}
	
	public int getCcv() {
		return ccv;
	}

	public String getCardNumber() {
		return cardNumber;
	}
	
	public String getMaskedCardNumber() {
		return maskedCardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}
	
	public Date getExpDate() {
		return expDate;
	}
	
	/**
	 * Builder pattern to create CreditCard objects.
	 * 
	 * @author michelesartini
	 *
	 */
	public static class CreditCardBuilder {
		private String bank;
		private String name;
		private CardType type;
		private int ccv;
		private String cardNumber;
		private String expiryDate;
		private Date expDate;
		private SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
		
		public CreditCardBuilder bank(String bank) {
			this.bank = bank;
			return this;
		}
		
		public CreditCardBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public CreditCardBuilder cartType(CardType type) {
			this.type = type;
			return this;
		}
		
		public CreditCardBuilder ccv(int ccv) {
			this.ccv = ccv;
			return this;
		}
		
		public CreditCardBuilder cardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
			return this;
		}
		
		public CreditCardBuilder expiryDate(Date expDate) {
			this.expiryDate = expDate != null ? sdf.format(expDate) : "";
			this.expDate = expDate;
			return this;
		}
		
		public CreditCard build() throws CreditCardException {
			return new CreditCard(this);
		}
		
	}
}
