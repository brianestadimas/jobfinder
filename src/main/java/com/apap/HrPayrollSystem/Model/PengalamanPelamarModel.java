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
@Table(name = "pengalaman_pelamar")
public class PengalamanPelamarModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pengalaman_pelamar", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PelamarModel pelamar_id;

	@NotNull
	@Size(max = 255)
	@Column(name = "nama_perusahaan", nullable = false)
	private String nama_perusahaan;

	@Size(max = 255)
	@Column(name = "tahun_terakhir", nullable = true)
	private String tahun_terakhir;

	@Size(max = 255)
	@Column(name = "lamaTahun_bekerja", nullable = true)
	private String lamaTahun_bekerja;

	@Size(max = 255)
	@Column(name = "lamaBulan_bekerja", nullable = true)
	private String lamaBulan_bekerja;

	@Size(max = 255)
	@Column(name = "jabatan", nullable = true)
	private String jabatan;

	@NotNull
	@Size(max = 500)
	@Column(name = "alasan_berhenti", nullable = false)
	private String alasan_berhenti;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PelamarModel getPelamar_id() {
		return pelamar_id;
	}

	public void setPelamar_id(PelamarModel pelamar_id) {
		this.pelamar_id = pelamar_id;
	}

	public String getNama_perusahaan() {
		return nama_perusahaan;
	}

	public void setNama_perusahaan(String nama_perusahaan) {
		this.nama_perusahaan = nama_perusahaan;
	}

	public String getTahun_terakhir() {
		return tahun_terakhir;
	}

	public void setTahun_terakhir(String tahun_terakhir) {
		this.tahun_terakhir = tahun_terakhir;
	}

	public String getLamaTahun_bekerja() {
		return lamaTahun_bekerja;
	}

	public void setLamaTahun_bekerja(String lamaTahun_bekerja) {
		this.lamaTahun_bekerja = lamaTahun_bekerja;
	}

	public String getLamaBulan_bekerja() {
		return lamaBulan_bekerja;
	}

	public void setLamaBulan_bekerja(String lamaBulan_bekerja) {
		this.lamaBulan_bekerja = lamaBulan_bekerja;
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
