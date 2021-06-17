package services;

import java.util.InputMismatchException;
import java.util.Scanner;

import DAOs.CustomerDAO;
import models.Account;
import models.Customer;
import utils.P;
import utils.TransactionException;

public class CustomerLoginService {
	public static void init() throws TransactionException {
		int menuSelection;
		Scanner inp= new Scanner(System.in);		
		boolean success=Login(inp);
		if(success) {
			P.prompt("Login Successful!");
			do {
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
					P.prompt("to transfer money to another account",
						"we need three things:",
						"the amount, your account number,other account number");
					Integer srcAcct_id=inp.nextInt();
					Integer dstAcct_id=inp.nextInt();
					Double amt1=inp.nextDouble();
					AccountTransferService.To(srcAcct_id,dstAcct_id, amt1);
					break;
				case 6:
				//accept money from another account
				P.prompt("to accept money from another account",
						"we need three things:",
						"your account number,other account number, transaction id");
				Integer srcAccount_id1=inp.nextInt();
				Integer dstAccount_id2=inp.nextInt();
				Integer amt2=inp.nextInt();
				AccountTransferService.From(srcAccount_id1,dstAccount_id2,amt2);
				break;
			case 7:
				return;
			case 8:
				break;
			default:
				break;
		}
	}while(menuSelection != 8);
		}else {
			P.prompt("login unsuccessful");
			return;
		}

	}
	public static Boolean Login(Scanner inp) {
		P.prompt("you chose to login to customer account");
		P.prompt("Please type your customerId passWord");
		Integer cust_id1=inp.nextInt();
		String passwd1=inp.next();
		//create customer object using cust_id and passwd
				Customer c= new Customer(cust_id1,passwd1);
				//get cust_id and passwd from db using DAO
				CustomerDAO cdao= new CustomerDAO();
				// if the info is correct these two objects should be equal
				if (c.equals(cdao.getByIdPass(cust_id1, passwd1))){
					return true;
				}else
					
					return false;
				
	}
	public static void CustomerMenu() {
		P.prompt("Here are your options",
		" 1. Open a new Account",
		" 2. View the Balance on a specified account",
		" 3. Make withdrawal to a specific account",
		" 4. Make deposit to a specific account",
		" 5. Transfer money to a specific account",
		" 6. Accept money from a specific account",
		" 7. Back",
		" 8. Quit",
		" Please type any one of the numbers 1, 2, 3, 4, 5, 6, 7, 8");
	}
	public static int receiveMenuInput() {
		try {
			Scanner input= new Scanner(System.in);
			Integer nextInt=input.nextInt();
			//if not false
			return nextInt;
			}catch(InputMismatchException e) {
				System.out.println("Bad Input! try again.");
			}
			return -1;
	}
}