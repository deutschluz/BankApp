package services;

import java.util.ArrayList;
import java.util.List;

import DAOs.AccountDAO;
import models.Account;
import utils.P;

public class AccountListingService {
	public static void init(Integer cust_id) {
		//create account object
		List<Account> custAccts=new ArrayList<Account>();
		//create accountDAO
		AccountDAO acdao=new AccountDAO();
		//get accounts by cust_id
		custAccts=acdao.getAllByCustId(cust_id);
		P.prompt(custAccts.toString());
	}
	
}
