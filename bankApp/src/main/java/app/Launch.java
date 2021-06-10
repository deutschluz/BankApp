package app;

import java.util.Scanner;

import utils.Log;
import utils.P;
import utils.TransactionException;
import services.LoginService;
import services.RegisterService;
import services.ViewStatusService;

public class Launch {

	public static void main(String[] args) throws TransactionException {
		Log.ger.info("App launched!");
		int menuSelection;
		do {
			P.prompt("Welcome to Crooks & Robbers Bank",
					" Here are your options",
					" 1. Register a new account",
					" 2. Login to your Account",
					" 3. View Account Status",
					" 4. Quit",
					" Please type any one of the numbers 1, 2, 3 or 4");
			menuSelection=receiveMenuInput();
			switch(menuSelection) {
			case 1:
				 //Register orCreate Account
				
				RegisterService.init();
				break;
			case 2:
				//Login
				LoginService.init();			
				break;
			case 3:
				//View Account Status
				ViewStatusService.init();
				break;
			case 4: 
				break;
			default: 
				break;	
	        }
		}while(menuSelection != 4); 


}

	
	public static int receiveMenuInput() {
		Scanner input= new Scanner(System.in);
		int nextInt=input.nextInt();
		//input.close();
		return nextInt;
	}
	
}
