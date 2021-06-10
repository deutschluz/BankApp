package services;

import DAOs.AccountDAO;
import models.Account;
import utils.TransactionException;

public class AccountDepositService {
	public static void init(Integer acct_id,Double amt) throws TransactionException{
		try {
		 if(amt<0) {
			 throw new TransactionException("Can not deposit Negative amounts");
		 }//get current balance from db
			AccountDAO acdao=new AccountDAO();
			Account a=acdao.getByAcctId(acct_id);
			Double amt2=a.getBalance()+amt;
			a.setBalance(amt2);
			acdao.update(a);
		}catch(TransactionException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
