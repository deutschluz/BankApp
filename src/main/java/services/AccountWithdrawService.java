package services;

import DAOs.AccountDAO;
import models.Account;
import utils.TransactionException;

public class AccountWithdrawService {
	public static void init(Integer acct_id, Double amt) throws TransactionException { 
		try {
		//get current balance from db
		AccountDAO acdao=new AccountDAO();
		Account a=acdao.getByAcctId(acct_id);
		//perform arithmetic on it
		Double newamt=a.getBalance()-amt;
		if (newamt<0) {
			throw new TransactionException("Insufficent Funds!");
		}
		a.setBalance(newamt);
		//post new balance to db--is this update?
		acdao.updateBalance(a);
		}catch(TransactionException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
