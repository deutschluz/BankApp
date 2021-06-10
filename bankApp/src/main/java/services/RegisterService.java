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
		Register(new Customer(2,firstName, lastName, email, passwd,"false"));//0 == false in SQL
	}
	public static void Register(Customer c) {
				//pass customer ob to the DAO
				CustomerDAO cdao= new CustomerDAO();
				//have the DAO pass to DB
				cdao.add(c);
	}
}
