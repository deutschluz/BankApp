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
							
					ResultSet rs = ps.executeQuery();
					
					if (rs.next()) {
						P.prompt("Account Successfully Created!");
 rs = ps.getResultSet();			
						if (rs.next()) {
							a.setAcct_id(rs.getInt("acct_id"));
							return a;
						}
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

	@Override
	public boolean update(Account t) {
		// TODO Auto-generated method stub
		return false;
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

}
