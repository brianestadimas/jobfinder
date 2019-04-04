package com.apap.HrPayrollSystem.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="pengalaman_pelamar")
public class PengalamanPelamarModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pengalaman_pelamar",referencedColumnName="id",nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PelamarModel pelamar_id;
	
	@NotNull
	@Size(max=255)
	@Column(name="nama_perusahaan",nullable = false)
	private String nama_perusahaan;
	
	//ini mestinya tahun_terakhir 
	@Size(max=255)
	@Column(name="pekerjaan_terakhir",nullable = true)
	private String pekerjaan_terakhir;
	
	@Size(max=255)
	@Column(name="lama_bekerja",nullable = true)
	private String lama_bekerja;
	
	@Size(max=255)
	@Column(name="jabatan",nullable = true)
	private String jabatan;
	
	@NotNull
	@Size(max=500)
	@Column(name="alasan_berhenti",nullable = false)
	private String alasan_berhenti;
	
	

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
