package com.apap.HrPayrollSystem.Utility;

import java.util.ArrayList;
import java.util.List;

import com.apap.HrPayrollSystem.Model.GajiModel;
import com.apap.HrPayrollSystem.Model.KehadiranModel;
import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;

public class PenggajianWrapper {

	private List<GajiModel> daftar_penggajian;
	
	public PenggajianWrapper() {
		super();
		this.daftar_penggajian = new ArrayList<GajiModel>();
	}
	
	public void add_penggajian(GajiModel penggajian) {
		this.daftar_penggajian.add(penggajian);
	}
	
	public List<GajiModel> getPenggajian() {
		return daftar_penggajian;
	}

	public void setPenggajian(List<GajiModel> penggajian) {
		this.daftar_penggajian = penggajian;
	}
	
}
