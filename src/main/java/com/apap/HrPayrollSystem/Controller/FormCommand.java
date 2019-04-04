package com.apap.HrPayrollSystem.Controller;

import java.util.ArrayList;
import java.util.List;

import com.apap.HrPayrollSystem.Model.PengalamanPelamarModel;

public class FormCommand {

	private String[] produkSelectedValues;

	private String genderSelectedValue;

	private String statusNikahSelectedValue;

	private List<PengalamanPelamarModel> pengalamanList;

	public FormCommand() {
		super();
		this.pengalamanList = new ArrayList<PengalamanPelamarModel>();
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

	public List<PengalamanPelamarModel> getPengalamanList() {
		return pengalamanList;
	}

	public void setPengalamanList(List<PengalamanPelamarModel> pengalamanList) {
		this.pengalamanList = pengalamanList;
	}

	public void addPengalamanToList(PengalamanPelamarModel pengalaman) {
		this.pengalamanList.add(pengalaman);
	}

	public void removeJadwalFromList(PengalamanPelamarModel pengalaman) {
		this.pengalamanList.remove(pengalaman);
	}

}
