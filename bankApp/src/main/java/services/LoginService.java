package services;

import java.util.Scanner;

import utils.P;
import utils.TransactionException;

public class LoginService {
	
	public static void init() throws TransactionException {
	P.prompt("Here are your options",
			" 1. Login to Customer Account",
			" 2. Login to Employee Account",
			" 3. Quit",
			" Please type any one of the numbers 1, 2, or 3");
	int menuSelection;
	menuSelection=receiveMenuInput();
	switch(menuSelection) {
		case 1:
			//login as customer
			CustomerLoginService.init();
			break;
		case 2:
			//login as employee
			EmployeeLoginService.init();
			break;
		case 3:
			break;
		default:
			break;
	}
}
	public static int receiveMenuInput() {
		Scanner input= new Scanner(System.in);
		int nextInt=input.nextInt();
		//input.close();
		return nextInt;
	}

	
	
}
