package com.apap.HrPayrollSystem.Utility;

import java.util.ArrayList;
import java.util.List;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;

public class PegawaiProyekWrapper {
	private List<PegawaiOutsourcingModel> listPegawai;

	
	public PegawaiProyekWrapper() {
		super();
		this.listPegawai = new ArrayList<PegawaiOutsourcingModel>();
	}

	public void addListPegawai(PegawaiOutsourcingModel listPegawai) {
		this.listPegawai.add(listPegawai);
	}

	public List<PegawaiOutsourcingModel> getListPegawai() {
		return listPegawai;
	}

	public void setListPegawai(List<PegawaiOutsourcingModel> listPegawai) {
		this.listPegawai = listPegawai;
	}
	
	
	
	
}
