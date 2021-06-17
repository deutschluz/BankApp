package models;

public class Customer {
	private Integer customer_id;
	private String firstN;
	private String lastN;
	private String email;
	private String passwd;
	private String isEmployee;
	
		
	
	public Customer(Integer customer_id, String firstN, String lastN, String email, String passwd, String isEmployee) {
		super();
		this.customer_id = customer_id;
		this.firstN = firstN;
		this.lastN = lastN;
		this.email = email;
		this.passwd = passwd;
		this.isEmployee = isEmployee;
	}

	public Customer(String firstN, String lastN, String email, String passwd,String stat) {
		super();
		this.firstN = firstN;
		this.lastN = lastN;
		this.email = email;
		this.passwd=passwd;
		this.isEmployee=stat;
	}

	public Customer() {
		super();
	}

	public Customer(Integer id, String passwd) {
		super();
		this.customer_id = id;
		this.passwd = passwd;
	}

	public Integer getId() {
		return customer_id;
	}

	public void setId(Integer id) {
		this.customer_id = id;
	}

	public String getFirstN() {
		return firstN;
	}

	public void setFirstN(String firstN) {
		this.firstN = firstN;
	}

	public String getLastN() {
		return lastN;
	}

	public void setLastN(String lastN) {
		this.lastN = lastN;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public void setEmployeeStatus(String stat) {
		this.isEmployee=stat;
	}
	public String getEmployeeStatus() {
		return isEmployee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstN == null) ? 0 : firstN.hashCode());
		result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
		result = prime * result + ((lastN == null) ? 0 : lastN.hashCode());
		result = prime * result + ((passwd == null) ? 0 : passwd.hashCode());
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
		Customer other = (Customer) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstN == null) {
			if (other.firstN != null)
				return false;
		} else if (!firstN.equals(other.firstN))
			return false;
		if (customer_id == null) {
			if (other.customer_id != null)
				return false;
		} else if (!customer_id.equals(other.customer_id))
			return false;
		if (lastN == null) {
			if (other.lastN != null)
				return false;
		} else if (!lastN.equals(other.lastN))
			return false;
		if (passwd == null) {
			if (other.passwd != null)
				return false;
		} else if (!passwd.equals(other.passwd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", firstN=" + firstN + ", lastN=" + lastN + ", email=" + email + ", passwd="
				+ passwd + "]";
	}
	
	
}
