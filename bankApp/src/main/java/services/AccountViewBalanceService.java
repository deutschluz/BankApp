package services;

import DAOs.AccountDAO;
import models.Account;
import utils.P;

public class AccountViewBalanceService {
	public static void init(Integer cust_id,Integer acct_id) {
		//create a new account object 
		Account acct=new Account(cust_id,acct_id);
		//create acctDAO object to fetch acct info from db
		AccountDAO acdao=new AccountDAO();
		Account a=acdao.getByAcctId(acct_id);
		//now acct and a should be the same account
		if (acct.getAcct_id() == a.getAcct_id()){
			P.prompt("here's your balance: ");
			P.prompt(a.getBalance().toString());
		}
	}
}
