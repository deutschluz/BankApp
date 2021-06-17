package DAOtests;

import static org.junit.Assert.*;

import org.junit.Test;

import DAOs.AccountDAO;

import org.junit.Assert;
import org.junit.Test;
import models.Account;

public class AccountDAOTest {

	@Test
	public void test() {
		//instantiate account object
		Account test=new Account(1,900.00,"active",1);
		//instantiate accountDAO
		Account other= new Account();
		AccountDAO acdao = new AccountDAO();
		//an account with this info should be in the DB
		other=acdao.getByAcctId(1);
		//so these two Account objects should be equal
		Assert.assertEquals(test, other);
	}

}
