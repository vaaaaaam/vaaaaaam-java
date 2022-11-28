package by.grsu.dbobovik.phonestat.db.model;
import java.sql.Timestamp;
public class Connection1 {
	@Override
	public String toString() {
		return "connection [id=" + id + ", serviceId=" + serviceId + ", userId=" + userId + ", date=" + date + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	private Integer id;
	private Integer serviceId;
	private Integer userId;
	private Timestamp date;
	

}
