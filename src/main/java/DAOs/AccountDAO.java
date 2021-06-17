package DAOs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import models.Account;
import utils.JDBCConnection;
import utils.P;

public class AccountDAO implements GenericRepository<Account>{
	private Connection conn = JDBCConnection.getConnection();
	
	//use cust_id to query accounts table
	public List<Account> getAllByCustId(Integer cust_id) {
		List<Account> actLst=new ArrayList<Account>();
		
		String sql = "select * from accounts where customer_id = ?;";
		
		try {
			//encapsulate query
			PreparedStatement ps = conn.prepareStatement(sql);
			//fill out query with cust_id
			ps.setInt(1, cust_id);
			//run query and save into rs
			ResultSet rs = ps.executeQuery();
			//iterate over the data stream
			while (rs.next()) {
				//create account ob
				Account a = new Account();
				a.setBalance(rs.getDouble("balance"));
				a.setCustomer_id(cust_id);
				a.setAcct_id(rs.getInt("acct_id"));
				a.setStatus(rs.getString("status"));
				
				actLst.add(a);
			}
			return actLst;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Account add(Account a) {
		//add account a into the accounts table acct_id,balance,status,cust_id
				String query = "insert into accounts values(default, ?,?,?) returning *;";				
				try {
					//encapsulate query
					PreparedStatement ps = conn.prepareStatement(query);
					//fill the query

					ps.setDouble(1, a.getBalance());
					ps.setString(2, a.getStatus());
					ps.setInt(3, a.getCustomer_id());
					//execute the quey	
					ResultSet rs = ps.executeQuery();
					
					if (rs.next()) {
						P.prompt("Account Successfully Created!");
						rs = ps.getResultSet();			
						a = new Account();
						a.setBalance(rs.getDouble("balance"));
						a.setCustomer_id(rs.getInt("customer_id"));
						a.setAcct_id(rs.getInt("acct_id"));
						a.setStatus(rs.getString("status"));
							return a;
						}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		return null;
	}

	public Account getByAcctId(Integer acct_id) {
		//query db by acct_id
				String query = "select a.acct_id, a.balance, a.status, a.customer_id from accounts a where a.acct_id = ?;";
				
				try {		
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setInt(1, acct_id);
					ResultSet rs = ps.executeQuery();		

					if(rs.next()) {
						Account a=new Account();
						a.setAcct_id(rs.getInt("acct_id"));
						a.setBalance(rs.getDouble("balance"));
						a.setStatus(rs.getString("status"));
						a.setCustomer_id(rs.getInt("customer_id"));
						return a;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		return null;
	}

	@Override
	public List<Account> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateBalance(Account a) {
		//query db by acct_id
		String query = "update accounts set balance = ?	where accounts.acct_id = ? returning *;";
	try {
		//encapsulate query
		PreparedStatement ps = conn.prepareStatement(query);
		//fill the query
		ps.setDouble(1, a.getBalance());
		ps.setInt(2, a.getAcct_id());
		//execute query and store resultset
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			rs = ps.getResultSet();			
			a = new Account();
			a.setBalance(rs.getDouble("balance"));
			a.setCustomer_id(rs.getInt("customer_id"));
			a.setAcct_id(rs.getInt("acct_id"));
			a.setStatus(rs.getString("status"));
			P.prompt("your account balance is now: ", a.getBalance().toString());
			return true;
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public boolean updateStatus(Account a) {
		switch(a.getStatus()) {
		case "active":
			//query db by acct_id
			String query = "update accounts set status='active'	where accounts.acct_id = ? returning *;";
		try {
			//encapsulate query
			PreparedStatement ps = conn.prepareStatement(query);
			//fill the query
			ps.setInt(1, a.getAcct_id());
			//execute query and store resultset
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {		
				a = new Account();
				a.setBalance(rs.getDouble("balance"));
				a.setCustomer_id(rs.getInt("customer_id"));
				a.setAcct_id(rs.getInt("acct_id"));
				a.setStatus(rs.getString("status"));
				P.prompt("your account status is now: ", a.getStatus());
				return true;
				}
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
		break;	
		case "rejected":
			//query db by acct_id
			query = "update accounts set status='rejected' where accounts.acct_id = ? returning *;";
		try {
			//encapsulate query
			PreparedStatement ps = conn.prepareStatement(query);
			//fill the query
			ps.setInt(1, a.getAcct_id());
			//execute query and store resultset
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {			
				a = new Account();
				a.setBalance(rs.getDouble("balance"));
				a.setCustomer_id(rs.getInt("customer_id"));
				a.setAcct_id(rs.getInt("acct_id"));
				a.setStatus(rs.getString("status"));
				P.prompt("your account status is now: ", a.getStatus());
				return true;
				}
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;
		case "pending":
			//query db by acct_id
            query = "update accounts set status='pending'	where accounts.acct_id = ? returning *;";
		try {
			//encapsulate query
			PreparedStatement ps = conn.prepareStatement(query);
			//fill the query
			ps.setInt(1, a.getAcct_id());
			//execute query and store resultset
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				rs = ps.getResultSet();			
				a = new Account();
				a.setBalance(rs.getDouble("balance"));
				a.setCustomer_id(rs.getInt("customer_id"));
				a.setAcct_id(rs.getInt("acct_id"));
				a.setStatus(rs.getString("status"));
				P.prompt("your account status is now: ", a.getStatus());
				return true;
				}
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			break;
	}return false;
}

	@Override
	public boolean delete(Account t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Account t) {
		// TODO Auto-generated method stub
		return false;
	}

}
