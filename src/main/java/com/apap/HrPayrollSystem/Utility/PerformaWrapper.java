package com.apap.HrPayrollSystem.Utility;

import java.util.ArrayList;
import java.util.List;

import com.apap.HrPayrollSystem.Model.KehadiranModel;

public class PerformaWrapper {

	private List<KehadiranModel> kehadiranList;
	private String judulKehadiran;
	private int totalKehadiran;
	private int totalSakit;
	private int totalIzin;
	private int totalAlfa;

	public PerformaWrapper() {
		super();
		this.judulKehadiran = "";
		this.kehadiranList = new ArrayList<KehadiranModel>();
		this.totalKehadiran = 0;
		this.totalSakit = 0;
		this.totalIzin = 0;
		this.totalAlfa = 0;
	}

	public List<KehadiranModel> getKehadiranList() {
		return kehadiranList;
	}

	public void setKehadiranList(List<KehadiranModel> kehadiran) {
		this.kehadiranList = kehadiran;
	}

	public String getJudulKehadiran() {
		return judulKehadiran;
	}

	public void setJudulKehadiran(String judulKehadiran) {
		this.judulKehadiran = judulKehadiran;
	}

	public int getTotalKehadiran() {
		return totalKehadiran;
	}

	public void setTotalKehadiran(int totalKehadiran) {
		this.totalKehadiran = totalKehadiran;
	}

	public int getTotalSakit() {
		return totalSakit;
	}

	public void setTotalSakit(int totalSakit) {
		this.totalSakit = totalSakit;
	}

	public int getTotalIzin() {
		return totalIzin;
	}

	public void setTotalIzin(int totalIzin) {
		this.totalIzin = totalIzin;
	}

	public int getTotalAlfa() {
		return totalAlfa;
	}

	public void setTotalAlfa(int totalAlfa) {
		this.totalAlfa = totalAlfa;
	}

	public void hitungTotal() {
		for (KehadiranModel hadir : this.kehadiranList) {
			this.totalKehadiran += hadir.getJumlah_kehadiran();
			this.totalAlfa += hadir.getJumlah_absen();
			this.totalIzin += hadir.getJumlah_izin();
			this.totalSakit += hadir.getJumlah_sakit();
		}
	}
}
