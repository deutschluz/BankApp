package DAOs;

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
	String query = "select t.trans_id, t.src, t.dst, t.status,t.timestamp,"
			+ " t.amt from transactions t where t.src = ? and t.dst = ?;";
				
				try {
					//encapsulate query
					PreparedStatement ps = conn.prepareStatement(query);
					//fill out query
					ps.setInt(1, src);
					ps.setInt(2, dst);
					//run query and save result set into rs
					ResultSet rs = ps.executeQuery();
					//create new transaction object
					Transaction t = new Transaction();
					//now get the info from the db and put it in transaction obj	
					t.setSrcAcct_id(rs.getInt("src"));
					t.setDstAcct_id(rs.getInt("dst"));
					t.setAmount(rs.getDouble("amt"));
					t.setId(rs.getInt("id"));
					t.setStatus(rs.getString("status"));
					return t;
				} catch (SQLException e) {
					e.printStackTrace();
				}
		return null;
	}
	@Override
	public Transaction add(Transaction t) {               //trans_id,status,src_id,dst_id,amt,
		String query = "insert into transactions values (default, ?,?,?,?,?) returning *;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			//fill out query
			ps.setInt(1,t.getId());//id
			ps.setString(2,t.getStatus());//status
			ps.setInt(3,t.getSrcAcct_id());
			ps.setInt(4,t.getDstAcct_id());
			ps.setDouble(5,t.getAmount());
	
			//run query
			boolean success = ps.execute();
			
			if (success) {
				ResultSet rs = ps.getResultSet();
				Log.ger.info("Received ResultSet!");
				if (rs.next()) {
					t.setId(rs.getInt("trans_id"));
					return t;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Transaction getById(Integer id) {
		// TODO Auto-generated method stub
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
