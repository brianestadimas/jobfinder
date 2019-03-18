package com.apap.HrPayrollSystem.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="pengalaman_pelamar")
public class PengalamanPelamarModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long id;
	
	@NotNull
	@Size(max=255)
	@Column(name="nama_perusahaan",nullable = false)
	private String nama_perusahaan;
	
	@NotNull
	@Size(max=255)
	@Column(name="pekerjaan_terakhir",nullable = true)
	private String pekerjaan_terakhir;
	
	@NotNull
	@Size(max=255)
	@Column(name="lama_bekerja",nullable = true)
	private String lama_bekerja;
	
	@NotNull
	@Size(max=255)
	@Column(name="jabatan",nullable = true)
	private String jabatan;
	
	@NotNull
	@Size(max=500)
	@Column(name="alasan_berhenti",nullable = false)
	private String alasan_berhenti;
	
	//FK to pelamar

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama_perusahaan() {
		return nama_perusahaan;
	}

	public void setNama_perusahaan(String nama_perusahaan) {
		this.nama_perusahaan = nama_perusahaan;
	}

	public String getPekerjaan_terakhir() {
		return pekerjaan_terakhir;
	}

	public void setPekerjaan_terakhir(String pekerjaan_terakhir) {
		this.pekerjaan_terakhir = pekerjaan_terakhir;
	}

	public String getLama_bekerja() {
		return lama_bekerja;
	}

	public void setLama_bekerja(String lama_bekerja) {
		this.lama_bekerja = lama_bekerja;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public String getAlasan_berhenti() {
		return alasan_berhenti;
	}

	public void setAlasan_berhenti(String alasan_berhenti) {
		this.alasan_berhenti = alasan_berhenti;
	}
	

}
