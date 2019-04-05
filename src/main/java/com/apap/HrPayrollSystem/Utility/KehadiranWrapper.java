package com.apap.HrPayrollSystem.Utility;

import java.util.ArrayList;
import java.util.List;

import com.apap.HrPayrollSystem.Model.KehadiranModel;

public class KehadiranWrapper {
	private List<KehadiranModel> daftar_kehadiran;

	
	public KehadiranWrapper() {
		super();
		this.daftar_kehadiran = new ArrayList<KehadiranModel>();
	}

	public void add_kehadiran(KehadiranModel daftar_kehadiran) {
		this.daftar_kehadiran.add(daftar_kehadiran);
	}

	public List<KehadiranModel> getDaftar_kehadiran() {
		return daftar_kehadiran;
	}

	public void setDaftar_kehadiran(List<KehadiranModel> daftar_kehadiran) {
		this.daftar_kehadiran = daftar_kehadiran;
	}
	
	
	
	
}
