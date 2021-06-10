package models;

import DAOs.AccountDAO;
import utils.P;
import utils.TransactionException;

public class Account {
	private Integer acct_id; 
	private Double Balance;
	private String status;
	private Integer customer_id;
		
	
	public Account() {
		super();
	}
	public Account(Integer cust_id,Integer acct_id) {
		this.customer_id=cust_id;
		this.acct_id=acct_id;
	}
	public Account(Integer cust_id,Integer acct_id,String newStat) {
		this.customer_id=cust_id;
		this.acct_id=acct_id;
		this.status=newStat;
	}
	public Account(Integer cust_id,Double bal) {
		this.customer_id=cust_id;
		this.Balance=bal;
		this.status="pending";
	}
	public Account(Integer acct_id, Double balance, String status, Integer customer_id) {
		super();
		this.acct_id = acct_id;
		Balance = balance;
		this.status = status;
		this.customer_id = customer_id;
	}
	public Integer getAcct_id() {
		return acct_id;
	}
	public void setAcct_id(Integer acct_id) {
		this.acct_id = acct_id;
	}
	public Double getBalance() {
		return Balance;
	}
	public void setBalance(Double balance) {
		Balance = balance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Balance == null) ? 0 : Balance.hashCode());
		result = prime * result + ((acct_id == null) ? 0 : acct_id.hashCode());
		result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Balance == null) {
			if (other.Balance != null)
				return false;
		} else if (!Balance.equals(other.Balance))
			return false;
		if (acct_id == null) {
			if (other.acct_id != null)
				return false;
		} else if (!acct_id.equals(other.acct_id))
			return false;
		if (customer_id == null) {
			if (other.customer_id != null)
				return false;
		} else if (!customer_id.equals(other.customer_id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [acct_id=" + acct_id + ", Balance=" + Balance + ", status=" + status + ", customer_id="
				+ customer_id + "]";
	}
	
	
}
