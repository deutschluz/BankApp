package services;

import DAOs.AccountDAO;
import models.Account;

public class AccountOpenNewService {
	public static void init(Integer cust_id,Double balance) {
		//create an new account object
		Account acct=new Account(cust_id,balance);
		//pass it DAO
		AccountDAO acdao=new AccountDAO();
		// DAO passes it db table
		acdao.add(acct);
	}
}
