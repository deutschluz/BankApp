package services;

import java.util.Scanner;

import DAOs.CustomerDAO;
import models.Customer;
import utils.P;

public class RegisterService {
	public static void init() {
		P.prompt("You have chosen to register or create a new account");
		P.prompt("to register/create account ",
				"you need to provide:",
				"firstname lastname email password");
		Scanner inp=new Scanner(System.in);
		String firstName=inp.next();
		String lastName = inp.next();
		String email=inp.next();
		String passwd=inp.next();
		Register(new Customer(firstName, lastName, email, passwd,"false"));//0 == false in SQL
		return;
	}
	public static void Register(Customer c) {
				//pass customer ob to the DAO
				CustomerDAO cdao= new CustomerDAO();
				//have the DAO pass to DB
				c=cdao.add(c);
				//if cdao does NOT return null 
				if (!c.equals(null)) {
					P.prompt("here is your customer id:"+
							c.getId().toString()+
						"remember that and your password");
					return;
				} 
				
	}
}
