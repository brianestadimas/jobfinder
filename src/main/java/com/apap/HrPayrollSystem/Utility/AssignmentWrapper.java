package com.apap.HrPayrollSystem.Utility;

import java.util.ArrayList;
import java.util.List;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.ProyekModel;

public class AssignmentWrapper {
	private List<PegawaiOutsourcingModel> daftar_pegawai;
	private List<ProyekModel> daftar_proyek;
	
	public AssignmentWrapper() {
		super();
		this.daftar_pegawai = new ArrayList<PegawaiOutsourcingModel>();
		this.daftar_proyek = new ArrayList<ProyekModel>();
	}

	public void add_pegawai(PegawaiOutsourcingModel daftar_pegawai) {
		this.daftar_pegawai.add(daftar_pegawai);
	}

	public List<PegawaiOutsourcingModel> getDaftar_pegawai() {
		return daftar_pegawai;
	}

	public void setDaftar_pegawai(List<PegawaiOutsourcingModel> daftar_pegawai) {
		this.daftar_pegawai = daftar_pegawai;
	}

	public List<ProyekModel> getDaftar_proyek() {
		return daftar_proyek;
	}

	public void setDaftar_proyek(List<ProyekModel> daftar_proyek) {
		this.daftar_proyek = daftar_proyek;
	}


	
	
}
