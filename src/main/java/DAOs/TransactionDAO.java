package DAOs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Account;
import models.Transaction;
import utils.JDBCConnection;
import utils.Log;
import utils.P;

public class TransactionDAO implements GenericRepository<Transaction> {
	private Connection conn = JDBCConnection.getConnection();
	
	public Transaction getBySrcDst(Integer src,Integer dst) {
		//query transactions table by srcAcct_id and dstAcct_id
	String query = "select t.trans_id, t.src_id, t.dst_id, t.status,"
			+ " t.amt from transactions t where t.src_id = ? and t.dst_id = ?;";
				
				try {
					//encapsulate query
					PreparedStatement ps = conn.prepareStatement(query);
					//fill out query
					ps.setInt(1, src);
					ps.setInt(2, dst);
					//run query and save result set into rs
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						//create new transaction object
						Transaction t = new Transaction();
						//now get the info from the db and put it in transaction obj	
						t.setSrcAcct_id(rs.getInt("src_id"));
						t.setDstAcct_id(rs.getInt("dst_id"));
						t.setAmount(rs.getDouble("amt"));
						t.setId(rs.getInt("trans_id"));
						t.setStatus(rs.getString("status"));
						return t;
					}else {
						return null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		return null;
	}
	@Override
	public Transaction add(Transaction t) {               //trans_id,status,src_id,dst_id,amt,
		String query = "insert into transactions values (default, ?,?,?,?) returning *;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			//fill out query

			ps.setString(1,t.getStatus());//status
			ps.setInt(2,t.getSrcAcct_id());
			ps.setInt(3,t.getDstAcct_id());
			ps.setDouble(4,t.getAmount());
	
			//run query
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Log.ger.info("Transaction logged!");
				t.setId(rs.getInt("trans_id"));
				return t;
				}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override                       //this id refers to trans_id in the db table
	public Transaction getById(Integer id) {
		//query db by acct_id
		String query = "select t.trans_id, t.status, t.amt, t.src_id,t.dst_id from transactions t where t.trans_id = ?;";
		
		try {		
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();		

			if(rs.next()) {
				Transaction t= new Transaction();
				t.setId(rs.getInt("trans_id"));
				t.setAmount(rs.getDouble("amt"));
				t.setStatus(rs.getString("status"));
				t.setSrcAcct_id(rs.getInt("src_id"));
				t.setDstAcct_id(rs.getInt("dst_id"));
				return t;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Transaction> getAll() {
		List<Transaction> tLst=new ArrayList<Transaction>();
		
		String sql = "select * from transactions;";
		
		try {
			//encapsulate query
			PreparedStatement ps = conn.prepareStatement(sql);
			//run query and save into rs
			ResultSet rs = ps.executeQuery();
			//iterate over the data stream
			while (rs.next()) {
				//create transaction ob
				Transaction t = new Transaction();
				//fill in the data from the stream
				t.setId(rs.getInt("trans_id"));
				t.setAmount(rs.getDouble("amt"));
				t.setDstAcct_id(rs.getInt("dst_id"));
				t.setSrcAcct_id((rs.getInt("src_id")));
				t.setStatus((rs.getString("status")));
				tLst.add(t);
			}
			return tLst;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//this method get a full data set to specify transaction
	public boolean approve(Transaction t) {
		//query db by acct_id
		//status is still pending
		String stat="accepted";
		t.setStatus(stat);
		String query = "update transactions set status = ?	where transactions.trans_id = ? returning *;";
	try {
		//encapsulate query
		PreparedStatement ps = conn.prepareStatement(query);
		//fill the query
		ps.setString(1, t.getStatus());
		ps.setInt(2, t.getId());
		//execute query and store resultset
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {			
			t = new Transaction();
			//fill in with the data from DB
			t.setAmount(rs.getDouble("amt"));
			t.setDstAcct_id(rs.getInt("dst_id"));
			t.setSrcAcct_id(rs.getInt("src_id"));
			t.setStatus(rs.getString("status"));
			P.prompt("transaction status is now",
					t.getStatus().toString());
            //update account tables using callable statment
			query="call transfer(?,?,?);"; //remember src,dst,amt
			CallableStatement cs=conn.prepareCall(query);
			//fill in 
			cs.setInt(1, t.getSrcAcct_id());
			cs.setInt(2,t.getDstAcct_id());
			cs.setDouble(3, t.getAmount());
			cs.execute();
			//create 2 account objects
			Account dst=new Account();
			dst.setAcct_id(t.getDstAcct_id());
			//create DAO
			AccountDAO acdao= new AccountDAO();
			dst=acdao.getById(dst.getAcct_id());//this is null
			P.prompt("your new account balance is",
				dst.getBalance().toString());	
			return true;
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public boolean update(Transaction t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Transaction t) {
		// TODO Auto-generated method stub
		return false;
	}

}
