package call_record.model;

public class Order {
	private String month;
	private String name;
	private String product;
	private boolean isSub;

	public Order(String month, String name, String product, boolean isSub) {
		this.month = month;
		this.name = name;
		this.product = product;
		this.isSub = isSub;
	}
	
	public Order() {
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public boolean getSub() {
		return isSub;
	}

	public void setSub(boolean isSub) {
		this.isSub = isSub;
	}

}
