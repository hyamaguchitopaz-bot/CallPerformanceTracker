package call_record;

class Result {
	private String name;
	private double rate;
	private int total;
	private int subCount;

	public Result(String name, double rate, int total, int subCount) {
		this.name = name;
		this.rate = rate;
		this.total = total;
		this.subCount = subCount;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSubCount() {
		return subCount;
	}

	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}

}
