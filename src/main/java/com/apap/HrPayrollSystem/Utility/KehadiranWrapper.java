package com.apap.HrPayrollSystem.Utility;

import java.util.ArrayList;
import java.util.List;

import com.apap.HrPayrollSystem.Model.GajiModel;
import com.apap.HrPayrollSystem.Model.KehadiranModel;
import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;

public class KehadiranWrapper {
	private List<KehadiranModel> daftar_kehadiran;
	private List<PegawaiOutsourcingModel> daftar_pegawai_outsourcing;
	private List<Penggajian> gaji;
	

	
	public KehadiranWrapper() {
		super();
		this.daftar_kehadiran = new ArrayList<KehadiranModel>();
		this.daftar_pegawai_outsourcing = new ArrayList<PegawaiOutsourcingModel>();
		this.gaji = new ArrayList<Penggajian>();
	}

	public void add_kehadiran(KehadiranModel daftar_kehadiran) {
		this.daftar_kehadiran.add(daftar_kehadiran);
	}
	
	public void add_pegawai_outsourcing(PegawaiOutsourcingModel pegawai_outsourcing) {
		this.daftar_pegawai_outsourcing.add(pegawai_outsourcing);
	}


	
	public void add_gaji(Penggajian penggajian) {
		this.gaji.add(penggajian);
	}
	
	public List<Penggajian> getGaji() {
		return gaji;
	}

	public void setGaji(List<Penggajian> gaji) {
		this.gaji = gaji;
	}



	public List<PegawaiOutsourcingModel> getDaftar_pegawai_outsourcing() {
		return daftar_pegawai_outsourcing;
	}

	public void setDaftar_pegawai_outsourcing(List<PegawaiOutsourcingModel> daftar_pegawai_outsourcing) {
		this.daftar_pegawai_outsourcing = daftar_pegawai_outsourcing;
	}

	public List<KehadiranModel> getDaftar_kehadiran() {
		return daftar_kehadiran;
	}

	public void setDaftar_kehadiran(List<KehadiranModel> daftar_kehadiran) {
		this.daftar_kehadiran = daftar_kehadiran;
	}
	
	
	
	
}
