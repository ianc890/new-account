package com.qa.service;

public interface ServiceInterface {

	public String createAccount(String account);
	public String getAllAccounts();
	public String findAccount(Long id);
	public String updateAccount(Long id, String accountToUpdate);
	public String deleteAccount(Long id);
}
