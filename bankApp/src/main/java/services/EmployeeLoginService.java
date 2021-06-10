package services;

import java.util.Scanner;

import DAOs.CustomerDAO;
import models.Account;
import models.Customer;
import utils.P;

public class EmployeeLoginService {
	public static void init() {
		P.prompt("you chose to login to employee account");
		P.prompt("Please type your customerId passWord");
		Scanner inp1=new Scanner(System.in);
		Integer cust_id1=inp1.nextInt();
		String passwd1=inp1.next();
		if(Login(cust_id1, passwd1)) {
			EmployeeMenu();
			int menuSelection;
			menuSelection=receiveMenuInput();
			switch(menuSelection) {
			case 1:
				P.prompt("you have chosen to approve an account");
				//Approve an Account
				P.prompt("Enter the customer_id and account_id for the account you wish to change");			
				Integer acct_id=inp1.nextInt();
				Integer cust_id=inp1.nextInt();
				AccountStatusService.change(cust_id,acct_id,"approved");
				break;
			case 2:
				P.prompt("you have chosen to reject an account");
				//Reject an account
				P.prompt("Enter the customer_id and account_id for the account you wish to change");
				Integer cust_id11 = inp1.nextInt();
				Integer acct_id1=inp1.nextInt();
				AccountStatusService.change(cust_id11,acct_id1,"rejected");
				break;
			case 3:
				//View List of Customer Accounts
				P.prompt("You have chosen to view a customers accounts!",
						"Please enter a customer_id to view the associated accounts");
				Integer cust_id111=inp1.nextInt();
				AccountListingService.init(cust_id111);
		
				break;
			case 4:
				//View Transactions Log
				P.prompt("you have chosen to view transaction logs",
						"May God have mercy on your soul!",
						"here ya go");
				TransactionLogService.view();
				break;
	
			default:
				break;
		}
	}
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
	
	public static void EmployeeMenu() {
		P.prompt("Here are your options",
				"1. Approve an Account",
				"2. Reject an Account",
				"3. View a Customer's Accounts",
				"4. View Transactions Log");
	}
	public static int receiveMenuInput() {
		Scanner input= new Scanner(System.in);
		int nextInt=input.nextInt();
		//input.close();
		return nextInt;
	}
}
