package services;

import models.Transaction;
import utils.P;

import java.util.ArrayList;
import java.util.List;

import DAOs.TransactionDAO;

public class TransactionLogService {
	//all this has to do is add a new entry to the db
	public static void Log(Integer transaxn_id, Integer srcAcct_id, Integer dstAcct_id, Double amount) {
		//create transaction object
		Transaction t=new Transaction(transaxn_id, srcAcct_id, dstAcct_id, amount);
		//create tDAO
		TransactionDAO tdao = new TransactionDAO();
		//add to DB through tdao
		tdao.add(t);
	}
	public static void view() {
		//create Transaction list object
		List<Transaction> tlst=new ArrayList<Transaction>();
		//creat transactionDAO
		TransactionDAO tdao=new TransactionDAO();
		//get the transaction throught tdao
		//and save them to tlst
		tlst=tdao.getAll();
		//now print them out
		P.prompt(tlst.toString());
	}
}
