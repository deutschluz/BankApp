package services;

import java.util.Scanner;

import models.Account;
import utils.P;

public class ViewStatusService {
	public static void init() {
		P.prompt("To view status",
				"please enter your customer_id acct_id");
		Scanner inp=new Scanner(System.in);
		Integer cust_id=inp.nextInt();
		Integer acct_id=inp.nextInt();

		//note: this static init method returns a Account ob
		AccountStatusService.view(cust_id,acct_id);
		

	}
}
