package com.qa.application;

import com.qa.domain.Account;
import com.qa.service.AccountService;
import com.qa.util.JSONUtil;

public class App {

	// added a comment
	public static void main(String[] args) {
		AccountService service = new AccountService();
		JSONUtil util = new JSONUtil();
		Account joeBloggs = new Account("Joe", "Bloggs", "1234");
		Account janeBloggs = new Account("Jane", "Bloggs", "1234");
		service.createAccount(util.getJSONForObject(joeBloggs));
		service.createAccount(util.getJSONForObject(janeBloggs));
		String mapAsJSON = util.getJSONForObject(service.getAccountMap());
		System.out.println("This is the account map as JSON " + mapAsJSON);

	}

}
