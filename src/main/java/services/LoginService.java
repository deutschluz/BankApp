package services;

import java.util.InputMismatchException;
import java.util.Scanner;

import utils.P;
import utils.TransactionException;

public class LoginService {
	
	public static void init() throws TransactionException {
		int menuSelection;
		do {
			P.prompt("Here are your options",
			" 1. Login to Customer Account",
			" 2. Login to Employee Account",
			" 3. Back",
			" 4. Quit",
			" Please type any one of the numbers 1, 2, 3, 4");
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
					return;
				case 4:
					break;
			}
		}while(menuSelection !=4);
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
