package com.qa.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class AccountDBRepository {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	@Transactional(REQUIRED)
	public String createAccount(String account) {
		Account newAccount = util.getObjectForJSON(account, Account.class);
		manager.persist(newAccount);
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	public String getAllAccounts() {
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}

	@Transactional(REQUIRED)
	public String updateAccount(Long id, String accountToUpdate) {
		Account updatedAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		Account accountFromDB = util.getObjectForJSON(findAccount(id), Account.class);
		if (accountToUpdate != null) {
			//accountFromDB = updatedAccount;
			//manager.merge(accountFromDB);
			accountFromDB.setAccountNumber(updatedAccount.getAccountNumber());
			accountFromDB.setFirstName(updatedAccount.getFirstName());
			accountFromDB.setSecondName(updatedAccount.getSecondName());
			manager.merge(accountFromDB);
		}
		return "{\"message\": \"account sucessfully updated\"}";
	}

	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		Account accountInDB = util.getObjectForJSON(findAccount(id), Account.class);
		if (accountInDB != null) {
			manager.remove(manager.contains(accountInDB) ? accountInDB : manager.merge(accountInDB));
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}

	public String findAccount(Long id) {
		Account findAccount = manager.find(Account.class, id);
		return util.getJSONForObject(findAccount);
	}

}
