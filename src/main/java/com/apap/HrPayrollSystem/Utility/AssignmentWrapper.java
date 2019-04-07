package com.apap.HrPayrollSystem.Utility;

import java.util.ArrayList;
import java.util.List;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;

public class AssignmentWrapper {
	private List<PegawaiOutsourcingModel> listOfPegawai;

	
	public AssignmentWrapper() {
		super();
		this.listOfPegawai = new ArrayList<PegawaiOutsourcingModel>();
	}

	public void add_kehadiran(PegawaiOutsourcingModel pegawai) {
		this.listOfPegawai.add(pegawai);
	}

	public List<PegawaiOutsourcingModel> getListPegawai() {
		return listOfPegawai;
	}

	public void setListOfPegawai(List<PegawaiOutsourcingModel> pegawaiList) {
		this.listOfPegawai = pegawaiList;
	}
	
}
