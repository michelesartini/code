package com.bcsg.creditcard.card;

/**
 * Enum that maps the accepted credit cards, along with the credit card patterns.
 * and the wanted masking pattern.
 * 
 * @author michelesartini
 *
 */
public enum CardType {
	VISA("^4[0-9]{3}-[0-9]{4}-[0-9]{4}-[0-9]{4}$|^4[0-9]{3}[0-9]{4}[0-9]{4}[0-9]{4}", "^[0-9]{4}", "${value}-xxxx-xxxx-xxxx"), 
	MASTERCARD("^5[0-9]{3}-[0-9]{4}-[0-9]{4}-[0-9]{4}$|^5[0-9]{3}[0-9]{4}[0-9]{4}[0-9]{4}$", "^[0-9]{2}", "${value}xx-xxxx-xxxx-xxxx"),
	AMEX("^3[0-9]{3}-[0-9]{4}-[0-9]{4}-[0-9]{3}$|^3[0-9]{3}[0-9]{4}[0-9]{4}[0-9]{3}$", "[0-9]{3}$", "xxxx-xxxx-xxxx-${value}");
	
	private final String cardPattern;
	private final String maskedValuePattern;
	private final String maskingPattern;
	
	public static final String REPLACING_PLACE_HOLDER = "${value}";
	
	CardType(String cardPattern, String maskedValuePattern, String maskingPattern) {
		this.cardPattern = cardPattern;
		this.maskedValuePattern = maskedValuePattern;
		this.maskingPattern = maskingPattern;
	}
	
	public String getCardPattern() {
		return this.cardPattern;
	}
	
	public String getMaskedValuePattern() {
		return this.maskedValuePattern;
	}
	
	public String getMaskingPattern() {
		return this.maskingPattern;
	}
}
