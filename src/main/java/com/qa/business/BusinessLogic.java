package com.qa.business;

import com.qa.domain.Account;

public class BusinessLogic {
			
	public boolean blockedAccount(Account account) {
		boolean valid = false;
		if(account.getAccountNumber().equals("9999")) {
			valid = true;
		}
		
		return valid;
	}
}
