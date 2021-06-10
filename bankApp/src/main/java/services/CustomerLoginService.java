package services;

import java.util.Scanner;

import DAOs.CustomerDAO;
import models.Account;
import models.Customer;
import utils.P;
import utils.TransactionException;

public class CustomerLoginService {
	public static void init() throws TransactionException {
	P.prompt("you chose to login to customer account");
	P.prompt("Please type your customerId passWord");
	Scanner inp=new Scanner(System.in);
	Integer cust_id=inp.nextInt();
	String passwd=inp.next();
	int menuSelection;
	if(Login(cust_id, passwd)) {
		CustomerMenu();
		menuSelection=receiveMenuInput();
		switch(menuSelection) {
			case 1:
				//open new account
				P.prompt("Enter your customer_id and starting balance:");
				Integer cust_id1=inp.nextInt();
				Double bal= inp.nextDouble();
				AccountOpenNewService.init(cust_id1,bal);
				break;
			case 2:
				//view balance on account
				P.prompt("Enter your customer_id and acct_id:");
				Integer cust_id2=inp.nextInt();
				Integer acct_id2 = inp.nextInt();
				AccountViewBalanceService.init(cust_id2,acct_id2);
				break;
			case 3:
				P.prompt("enter an account number");
				Integer acct_id1=inp.nextInt();
				P.prompt("How much do you want to withdraw(format: 123.45)");
				Double amount=inp.nextDouble();
				//make withdrawal
				AccountWithdrawService.init(acct_id1,amount);
				break;
			case 4:
				//make deposit
				P.prompt("enter an account number");
				Integer acct_id21=inp.nextInt();
				P.prompt("How much do you want to deposit(format: 123.45");
				Double amt=inp.nextDouble();
				AccountDepositService.init(acct_id21,amt);
				break;
			case 5:
				//transfer money to another account
				P.prompt("to transfer money to another accoutn",
						"we need three things:",
						"your account number,other account number, amount");
				Integer srcAcct_id=inp.nextInt();
				Integer dstAcct_id=inp.nextInt();
				Double amt1=inp.nextDouble();
				AccountTransferService.To(srcAcct_id,dstAcct_id, amt1);
				break;
			case 6:
				//accept money from another account
				P.prompt("to accept money from another account",
						"we need three things:",
						"your account number,other account number, amount");
				Integer srcAccount_id1=inp.nextInt();
				Integer dstAccount_id2=inp.nextInt();
				Double amt2=inp.nextDouble();
				AccountTransferService.From(srcAccount_id1,dstAccount_id2,amt2);
			default:
				break;
		}
	}else 
		P.prompt("Login unsuccessfull!");

	}
	public static Boolean Login(Integer cust_id,String passwd) {
		//create customer object using cust_id and passwd
				Customer c= new Customer(cust_id,passwd);
				//get cust_id and passwd from db using DAO
				CustomerDAO cdao= new CustomerDAO();
				// if the info is correct these two objects should be equal
				if (c.equals(cdao.getByIdPass(cust_id, passwd))){
					P.prompt("Login Successful!");
					return true;
				}else
					P.prompt("login unsuccessful");
					return false;
				
	}
	public static void CustomerMenu() {
		P.prompt(" Here are your options",
		" 1. Open a new Account",
		" 2. View the Balance on a specified account",
		" 3. Make withdrawal to a specific account",
		" 4. Make deposit to a specific account",
		" 5. Transfer money to a specific account",
		" 6. Quit",
		" Please type any one of the numbers 1, 2, 3, 4, 5");
	}
	public static int receiveMenuInput() {
		Scanner input= new Scanner(System.in);
		int nextInt=input.nextInt();
		//input.close();
		return nextInt;
	}
}