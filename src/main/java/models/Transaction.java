package models;

public class Transaction {
	private Integer id;
	private String status;
	private Double Amount;
	private Integer srcAcct_id;
	private Integer dstAcct_id;

	
	
	public Transaction() {
		super();
	}
	public Transaction(Integer id, String status, Double amount, Integer srcAcct_id, Integer dstAcct_id) {
		super();
		this.id = id;
		this.status = status;
		this.Amount = amount;
		this.srcAcct_id = srcAcct_id;
		this.dstAcct_id = dstAcct_id;
	}
	public Transaction(Integer id, Integer srcAcct_id, Integer dstAcct_id, Double amount) {
		super();
		this.id = id;
		this.srcAcct_id = srcAcct_id;
		this.dstAcct_id = dstAcct_id;
		this.Amount = amount;
	}
	
	public Transaction(Integer id) {
		super();
		this.id = id;
	}
	
	public Transaction(Integer srcAcct_id, Integer dstAcct_id) {
		super();
		this.srcAcct_id = srcAcct_id;
		this.dstAcct_id = dstAcct_id;
	}

	public Transaction(Integer srcAcct_id, Integer dstAcct_id, Double amount) {
		super();
		this.status="pending";
		this.srcAcct_id = srcAcct_id;
		this.dstAcct_id = dstAcct_id;
		this.Amount = amount;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getAmount() {
		return Amount;
	}

	public void setAmount(Double amount) {
		this.Amount = amount;
	}

	public Integer getSrcAcct_id() {
		return srcAcct_id;
	}

	public void setSrcAcct_id(Integer srcAcct_id) {
		this.srcAcct_id = srcAcct_id;
	}

	public Integer getDstAcct_id() {
		return dstAcct_id;
	}

	public void setDstAcct_id(Integer dstAcct_id) {
		this.dstAcct_id = dstAcct_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Amount == null) ? 0 : Amount.hashCode());
		result = prime * result + ((dstAcct_id == null) ? 0 : dstAcct_id.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((srcAcct_id == null) ? 0 : srcAcct_id.hashCode());
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
		Transaction other = (Transaction) obj;
		if (Amount == null) {
			if (other.Amount != null)
				return false;
		} else if (!Amount.equals(other.Amount))
			return false;
		if (dstAcct_id == null) {
			if (other.dstAcct_id != null)
				return false;
		} else if (!dstAcct_id.equals(other.dstAcct_id))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (srcAcct_id == null) {
			if (other.srcAcct_id != null)
				return false;
		} else if (!srcAcct_id.equals(other.srcAcct_id))
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
		return "Transaction [id=" + id + ", status=" + status + ", Amount=" + Amount + ", srcAcct_id=" + srcAcct_id
				+ ", dstAcct_id=" + dstAcct_id + "]";
	}

	
	
	
}
