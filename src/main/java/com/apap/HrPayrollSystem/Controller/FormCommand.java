package com.apap.HrPayrollSystem.Controller;

public class FormCommand {

	private String[] produkSelectedValues;

	private String genderSelectedValue;

	private String statusNikahSelectedValue;

	public FormCommand() {
		super();
	}

	public String[] getProdukSelectedValues() {
		return produkSelectedValues;
	}

	public void setProdukSelectedValues(String[] produkSelectedValues) {
		this.produkSelectedValues = produkSelectedValues;
	}

	public String getGenderSelectedValue() {
		return genderSelectedValue;
	}

	public void setGenderSelectedValue(String genderSelectedValue) {
		this.genderSelectedValue = genderSelectedValue;
	}

	public String getStatusNikahSelectedValue() {
		return statusNikahSelectedValue;
	}

	public void setStatusNikahSelectedValue(String statusNikahSelectedValue) {
		this.statusNikahSelectedValue = statusNikahSelectedValue;
	}

}
