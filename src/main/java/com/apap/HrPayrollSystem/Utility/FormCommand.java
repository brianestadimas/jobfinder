package com.apap.HrPayrollSystem.Utility;

import java.util.ArrayList;
import java.util.List;

import com.apap.HrPayrollSystem.Model.PengalamanPelamarModel;

public class FormCommand {

	private String[] selectedCheckboxProduk;

	private String selectedRadioGender;

	private String selectedRadioMarital;
	
	private List<PengalamanPelamarModel> pengalamanList;

	public FormCommand() {
		super();
		this.pengalamanList = new ArrayList<PengalamanPelamarModel>();
	}

	public String getSelectedRadioGender() {
		return selectedRadioGender;
	}

	public void setSelectedRadioGender(String selectedRadioGender) {
		this.selectedRadioGender = selectedRadioGender;
	}

	public String getSelectedRadioMarital() {
		return selectedRadioMarital;
	}

	public void setSelectedRadioMarital(String selectedRadioMarital) {
		this.selectedRadioMarital = selectedRadioMarital;
	}
	
	public String[] getSelectedCheckboxProduk() {
		return selectedCheckboxProduk;
	}

	public void setSelectedCheckboxProduk(String[] selectedCheckboxProduk) {
		this.selectedCheckboxProduk = selectedCheckboxProduk;
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

	public boolean checkForNull() {
		if(this.selectedCheckboxProduk==null||this.selectedCheckboxProduk.length==0||this.selectedRadioGender==null||this.selectedRadioMarital==null) {
			return true;
		}else
			return false;
	}
	public String checkForNullMsg () {
		if (this.selectedCheckboxProduk==null)
			return "Belum ada produk, hubungi admin untuk menambah produk !";
		else if(this.selectedCheckboxProduk.length==0)
			return "Minimal memilih satu jenis produk !";
		else if(this.selectedRadioGender==null)
			return "Jenis kelamin belum dipilih !";
		else if (this.selectedRadioMarital==null)
			return "Status pernikahan belum dipilih !";
		else
			return "";
	}
	
	
}
