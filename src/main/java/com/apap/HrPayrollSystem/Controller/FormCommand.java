package com.apap.HrPayrollSystem.Controller;

import java.util.ArrayList;
import java.util.List;

import com.apap.HrPayrollSystem.Model.PengalamanPelamarModel;

public class FormCommand {

	private List<PengalamanPelamarModel> pengalamanList;

	public FormCommand() {
		super();
		this.pengalamanList = new ArrayList<PengalamanPelamarModel>();
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
