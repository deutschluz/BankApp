package DAOs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import models.Customer;
import utils.JDBCConnection;
import utils.P;

public class CustomerDAO implements GenericRepository<Customer> {
	private Connection conn = JDBCConnection.getConnection();
	
	public Customer add(Customer c) {          //this is serial
		//add customer c into the customers table     customer_id,firstN,lastN,email,isEmployee,pass
		String query = "insert into customers (customer_id, firstN, lastN, email,isEmployee,passwd)"
				+ " values(default,?,?,?,?,?) returning*;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			//ps.setInt(1, c.getId());
			ps.setString(1, c.getFirstN());
			ps.setString(2, c.getLastN());
			ps.setString(3, c.getEmail());
			ps.setString(4,c.getEmployeeStatus());	
			ps.setString(5, c.getPasswd());
			ResultSet rs = ps.executeQuery();	
			if (rs.next()) {
				c.setId(rs.getInt("customer_id"));
				return c;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Customer getByIdPass(Integer id,String passwd) {
		//query db by customer_id
		String query = "select c.customer_id, c.firstN, c.lastN, c.email, c.isEmployee, c.passwd from customers c where c.customer_id = ?;";
		
		try {		
			PreparedStatement ps = conn.prepareStatement(query);
			//fill query
			ps.setInt(1, id);
			//run query
			ResultSet rs = ps.executeQuery();		
			Customer c = new Customer();
			if(rs.next()) {	
				c.setId(rs.getInt("customer_id"));
				c.setPasswd(rs.getString("passwd"));	
				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(Customer t) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(Customer t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
