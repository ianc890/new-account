package com.qa.service;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import com.qa.business.BusinessLogic;
import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@ApplicationScoped
@Alternative
public class AccountService implements ServiceInterface{

	private Map<Integer, Account> accountMap;
	
	private int count = 1;
	
	@Inject
	private JSONUtil util;
	
	@Inject
	private BusinessLogic business;

	public AccountService() {
		accountMap = new HashMap<Integer, Account>();
		initAccountMap();
	}
	
	public void initAccountMap() {
		Account account = new Account("Ian", "Doe", "12348");
		accountMap.put(0, account);
	}

	/*public void addAccountFromMap(Account userAccount) {
		accountMap.put(count, userAccount);
		count++;
	}

	public void removeAccountFromMap(Integer accountToRemove) {
		boolean countExists = accountMap.containsKey(accountToRemove);
		if (countExists) {
			accountMap.remove(accountToRemove);
		}
	}*/

	public Map<Integer, Account> getAccountMap() {
		return accountMap;
	}

	/*public int getNumberOfAccountWithFirstName(String firstNameOfAccount) {
		return (int) accountMap.values().stream()
				.filter(eachAccount -> eachAccount.getFirstName().equals(firstNameOfAccount)).count();
	}*/

	@Override
	public String createAccount(String account) {
		Account newAccount = util.getObjectForJSON(account, Account.class);
		boolean valid = business.blockedAccount(newAccount);
		if(!valid) {
			accountMap.put(count, newAccount);
			count++;
			return "{\"message\": \"account has been sucessfully added to the map\"}";
		}

		return "{\"message\": \"This account is blocked\"}";
	}

	@Override
	public String getAllAccounts() {
		return util.getJSONForObject(accountMap);
	}

	@Override
	public String findAccount(Long id) {
		return util.getJSONForObject(accountMap.get(id.intValue()));
	}

	@Override
	public String updateAccount(Long id, String accountToUpdate) {
		Account updateAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		accountMap.put(id.intValue(), updateAccount);
		// count++;
		return "{\"message\": \"account has been sucessfully updated on the map\"}";
	}

	@Override
	public String deleteAccount(Long id) {
		accountMap.remove(id.intValue());
		return "{\"message\": \"account sucessfully deleted\"}";
	}

}
