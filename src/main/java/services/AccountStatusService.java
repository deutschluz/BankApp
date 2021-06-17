package services;

import DAOs.AccountDAO;
import models.Account;
import utils.P;

public class AccountStatusService {
	public static Account init(Integer cust_id, Integer acct_id) {
		//create account obj
		Account a= new Account(cust_id,acct_id);
		//create accountDAO to communicate with DB
		AccountDAO acdao= new AccountDAO();
		//get the info from DB and store in a
		a=acdao.getById(acct_id);
		return a;
	}
	public static void view(Integer cust_id,Integer acct_id) {
		//create account
		Account a=new Account(cust_id,acct_id);
		AccountDAO acdao= new AccountDAO();
		a=acdao.getByAcctId(acct_id);
		P.prompt("here's your account's status",
				a.getStatus().toString());
	}
	public static void activate(Integer cust_id,Integer acct_id,String newStat) {
		//create account
		Account a=new Account(cust_id,acct_id,newStat);
		AccountDAO acdao= new AccountDAO();
		if(acdao.updateStatus(a)) {
		}else 
			P.prompt("status update failed!");

	}
	public static void reject(Integer cust_id,Integer acct_id,String newStat) {
		//create account
		Account a=new Account(cust_id,acct_id,newStat);
		AccountDAO acdao= new AccountDAO();
		if(acdao.updateStatus(a)) {
			
		}else 
			P.prompt("status update failed!");

	}
}
