package by.grsu.dbobovik.phonestat.web.dto;

public class InvoiceDto {
    private Integer id;
	private Double invoice;
	private Integer userId;
    private String userData;

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", invoice=" + invoice + ", userId=" + userId + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getInvoice() {
		return invoice;
	}

	public void setInvoice(Double invoice) {
		this.invoice = invoice;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }
}
