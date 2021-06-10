package services;

import DAOs.TransactionDAO;
import models.Transaction;

public class AccountTransferService {
	public static void To(Integer srcAcct_id, Integer dstAcct_id,Double amt) {
		//create transaction object
		Transaction t=new Transaction(srcAcct_id,dstAcct_id,amt);
		//create transactionDAO to communicate with DB
		TransactionDAO tdao=new TransactionDAO();
		//add the new transaxn
		tdao.add(t);
		
	}
	//this just has to change the status of the transaction
	//to accepted
	public static void From(Integer srcAcct_id,Integer dstAcct_id, Double amt) {
		//create transaction object from the info above
		Transaction t=new Transaction(srcAcct_id,dstAcct_id,amt);
		//get the transaction from the db through the transactionDAO
		TransactionDAO tdao=new TransactionDAO();
		t=tdao.getBySrcDst(srcAcct_id, dstAcct_id);
		//change status
		t.setStatus("valid");
		//update the db
		tdao.add(t);
	}
}
