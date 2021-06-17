package services;

import DAOs.AccountDAO;
import DAOs.TransactionDAO;
import models.Account;
import models.Transaction;
import utils.P;

public class AccountTransferService {
	public static void To(Integer srcAcct_id, Integer dstAcct_id,Double amt) {
		//create transaction object
		Transaction t=new Transaction(srcAcct_id,dstAcct_id,amt);
		//create transactionDAO to communicate with DB
		TransactionDAO tdao=new TransactionDAO();
		//add the new transaxn
		t=tdao.add(t);
		P.prompt("here is your transaction ID: ",
				t.getId().toString());
		
	}
	//this just has to change the status of the transaction
	//to accepted and update the two accounts in the accounts table
	public static void From(Integer srcAcct_id,Integer dstAcct_id, Integer trans_id) {
		//create transaction object from the info above
		Transaction t=new Transaction(trans_id);
		//get the transaction from the db through the transactionDAO
		TransactionDAO tdao=new TransactionDAO();
		t=tdao.getById(trans_id);
		//change status
		String stat="pending";
		if(stat.equals(t.getStatus())) {
			P.prompt("status should be pending");
		};
		//update the transaction tables
		//the approve method get the full data
		if(tdao.approve(t)) {
			P.prompt("transfer accepted");
		}
		//update accounts table.
		//AccountDAO acdao=new AccountDAO();
		//create the 2 account objects
//		Account src=new Account();
//		Account dst=new Account();
//		src.setBalance(src.getBalance()-amt);
//		dst.setBalance(dst.getBalance()+amt);
	}
}
