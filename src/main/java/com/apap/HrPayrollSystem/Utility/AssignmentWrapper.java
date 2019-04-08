package com.apap.HrPayrollSystem.Utility;

import java.util.ArrayList;
import java.util.List;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.ProyekModel;

public class AssignmentWrapper {
	private List<PegawaiOutsourcingModel> listOfPegawai;
	private List<ProyekModel> listOfProyek;
	
	public AssignmentWrapper() {
		super();
		this.listOfPegawai = new ArrayList<PegawaiOutsourcingModel>();
		this.listOfProyek = new ArrayList<ProyekModel>();
	}

	public void add_pegawai(PegawaiOutsourcingModel pegawai) {
		this.listOfPegawai.add(pegawai);
	}

	public List<PegawaiOutsourcingModel> getListOfPegawai() {
		return listOfPegawai;
	}

	public void setListOfPegawai(List<PegawaiOutsourcingModel> listOfPegawai) {
		this.listOfPegawai = listOfPegawai;
	}

	public List<ProyekModel> getListOfProyek() {
		return listOfProyek;
	}

	public void setListOfProyek(List<ProyekModel> listOfProyek) {
		this.listOfProyek = listOfProyek;
	}
	
}
