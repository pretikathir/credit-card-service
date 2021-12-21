package com.credit.card.validation;

public class CreditCardValidation {

	 public static  boolean checkLuhn(String cardNo) {
		int len = cardNo.length();
		int Sum = 0;
		boolean isSecond = false;
		for (int i = len - 1; i >= 0; i--) {
			int d = cardNo.charAt(i) - '0';
			if (isSecond)
				d = d * 2;
			Sum += d / 10;
			Sum += d % 10;
			isSecond = !isSecond;
		}
		return (Sum % 10 == 0);
	}

}
