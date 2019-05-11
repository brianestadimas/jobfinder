package com.apap.HrPayrollSystem.Utility;

import java.util.ArrayList;
import java.util.List;

import com.apap.HrPayrollSystem.Model.GajiModel;

public class PenggajianWrapper {


	private List<GajiModel> daftar_penggajian;
	
	public PenggajianWrapper() {
		super();
		this.daftar_penggajian = new ArrayList<GajiModel>();
	}
	
	public void add_penggajian(GajiModel penggajian) {
		this.daftar_penggajian.add(penggajian);
	}
	
	public List<GajiModel> getDaftar_penggajian() {
		return daftar_penggajian;
	}

	public void setDaftar_penggajian(List<GajiModel> daftar_penggajian) {
		this.daftar_penggajian = daftar_penggajian;
	}

	
}
