package DAOtests;

import static org.junit.Assert.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import DAOs.AccountDAO;
import DAOs.TransactionDAO;
import org.junit.Assert;

import models.Account;
import models.Transaction;
import utils.JDBCConnection;
import utils.P;

public class TransactionDAOApproveTest {

//	//create DAO
//   @ignore
//   public void testFrom() {
//	 //create transaction object from the info above
//	 		Transaction t=new Transaction(2,1,100.00);
//	 		//get the transaction from the db through the transactionDAO
//	 		TransactionDAO tdao=new TransactionDAO();
//	 		t=tdao.getById(4);
//	 		System.out.println(t.toString());
//	 		//check status
//	 		P.prompt("the status is now:",
//	 				t.getStatus());
//	 		//change status
//	 		t.setStatus("accepted");
//	 		P.prompt("the status is now: ",
//	 				t.getStatus());
//   }
//   @Test
//   public void test2() throws SQLException {
//	   final Connection conn = JDBCConnection.getConnection();
//	   String query="call transfer(?,?,?);"; //remember src,dst,amt
//		CallableStatement cs = conn.prepareCall(query);
//
//		//fill in  using data from db: 1,pending,100.00,2,1
//		Transaction t= new Transaction(1,"pending",100.00,2,1);
//
//			cs.setInt(1, t.getSrcAcct_id());
//	
//	
//			cs.setInt(2,t.getDstAcct_id());
//	
//
//			cs.setDouble(3, t.getAmount());
//	
//		try {
//			cs.execute();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();Account dst=new Account();
//			dst.setAcct_id(t.getDstAcct_id());
//			//create DAO
//			AccountDAO acdao= new AccountDAO();
//			dst=acdao.getById(dst.getAcct_id());//this is null
//			P.prompt("your new account balance is",
//				dst.getBalance().toString());	
//		}
//   }
//	
   @Test
   public void test3() {
	 //create DAO
	   AccountDAO acdao= new AccountDAO();
	 //fill in  using data from db: 1,pending,100.00,2,1   srcID
	 		Transaction t= new Transaction(1,"pending",100.00,2,1);
		//create 2 account objects
		Account dst=new Account();  //from db: acctID,balance,status,customerId
		Account otherDst=new Account(2,1100.00,"pending",2);
		//get the account in 2 ways
		dst.setAcct_id(t.getDstAcct_id());
		//get accounts by aact_Id
		dst=acdao.getById(dst.getAcct_id());
		Assert.assertEquals(dst, otherDst);
		P.prompt("your new account balance is",
			dst.getBalance().toString());
		//Assert.assertEquals(otherDst.getBalance(), dst.getBalance());
  }
}
   

