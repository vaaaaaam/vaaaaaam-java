package by.grsu.dbobovik.phonestat.db.model;

public class Service {
	@Override
	public String toString() {
		return "service [id=" + id + ", name=" + name + ", cost=" + cost + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	private Integer id;
	private String name;
	private Double cost;

}
