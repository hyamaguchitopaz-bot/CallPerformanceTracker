package call_record.condition;

import call_record.model.Order;

public class SearchCondition {
	private String targetMonth;
	private String targetName;
	private String targetPro;

	public SearchCondition(String targetMonth, String targetName, String targetPro) {
		this.targetMonth = targetMonth;
		this.targetPro = targetPro;
		this.targetName = targetName;
	}

	public boolean matches(Order o) {
				//nullか指定した条件があるか
		return (targetMonth == null || o.getMonth().equals(targetMonth))
				&& (targetName == null || o.getName().equals(targetName))
				&& (targetPro == null || o.getProduct().equals(targetPro));
	}

	public String getTargetMonth() {
		return targetMonth;
	}

	public void setTargetMonth(String targetMonth) {
		this.targetMonth = targetMonth;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTargetPro() {
		return targetPro;
	}

	public void setTargetPro(String targetPro) {
		this.targetPro = targetPro;
	}
}
