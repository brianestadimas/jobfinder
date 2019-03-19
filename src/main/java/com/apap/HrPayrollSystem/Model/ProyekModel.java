package com.apap.HrPayrollSystem.Model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="proyek")
public class ProyekModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max=255)
	@Column(name="nama_proyek",nullable = false)
	private String nama_proyek;
	
	@NotNull
	@Size(max=255)
	@Column(name="nomor_kontrak",nullable = false)
	private String nomor_kontrak;
	
	@NotNull
	@Size(max=255)
	@Column(name="region",nullable = false)
	private String region;
	
	@Size(max=255)
	@Column(name="nama_cp",nullable = true)
	private String nama_cp;
	
	@Size(max=255)
	@Column(name="no_telp_cp",nullable = true)
	private String no_telp_cp;
	
	@Size(max=255)
	@Column(name="no_rekening",nullable = true)
	private String no_rekening;
	
	@NotNull
	@Size(max=255)
	@Column(name="start_date_kontrak",nullable = false)
	private Date start_date_kontrak;
	
	@NotNull
	@Size(max=255)
	@Column(name="end_date_kontrak",nullable = false)
	private Date end_date_kontrak;
	
	@NotNull
	@Size(max=255)
	@Column(name="jenis_proyek",nullable = false)
	private long jenis_proyek;
	
	@Size(max=255)
	@Column(name="biaya_keseluruhan",nullable = true)
	private long biaya_keseluruhan;
	
	@Size(max=255)
	@Column(name="NPWP_klien",nullable = true)
	private String NPWP_klien;
	
	@Size(max=255)
	@Column(name="nilai_kontrak",nullable = true)
	private long nilai_kontrak;

//	@OneToOne(mappedBy = "pegawai_outsourcing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private PegawaiOutsourcingModel pegawai_outsourcing;
//
//	@OneToOne(mappedBy = "history_bekerja", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private HistoryModel history_bekerja;
	

//	
//	public HistoryModel getHistory() {
//		return history_bekerja;
//	}
//
//	public void setHistory(HistoryModel history) {
//		this.history_bekerja = history;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama_proyek() {
		return nama_proyek;
	}

	public void setNama_proyek(String nama_proyek) {
		this.nama_proyek = nama_proyek;
	}

	public String getNomor_kontrak() {
		return nomor_kontrak;
	}

	public void setNomor_kontrak(String nomor_kontrak) {
		this.nomor_kontrak = nomor_kontrak;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getNama_cp() {
		return nama_cp;
	}

	public void setNama_cp(String nama_cp) {
		this.nama_cp = nama_cp;
	}

	public String getNo_telp_cp() {
		return no_telp_cp;
	}

	public void setNo_telp_cp(String no_telp_cp) {
		this.no_telp_cp = no_telp_cp;
	}

	public Date getStart_date_kontrak() {
		return start_date_kontrak;
	}

	public void setStart_date_kontrak(Date start_date_kontrak) {
		this.start_date_kontrak = start_date_kontrak;
	}

	public Date getEnd_date_kontrak() {
		return end_date_kontrak;
	}

	public void setEnd_date_kontrak(Date end_date_kontrak) {
		this.end_date_kontrak = end_date_kontrak;
	}

	public long getJenis_proyek() {
		return jenis_proyek;
	}

	public void setJenis_proyek(long jenis_proyek) {
		this.jenis_proyek = jenis_proyek;
	}

	public long getBiaya_keseluruhan() {
		return biaya_keseluruhan;
	}

	public void setBiaya_keseluruhan(long biaya_keseluruhan) {
		this.biaya_keseluruhan = biaya_keseluruhan;
	}

	public String getNPWP_klien() {
		return NPWP_klien;
	}

	public void setNPWP_klien(String nPWP_klien) {
		NPWP_klien = nPWP_klien;
	}

	public long getNilai_kontrak() {
		return nilai_kontrak;
	}

	public void setNilai_kontrak(long nilai_kontrak) {
		this.nilai_kontrak = nilai_kontrak;
	}

//	public PegawaiOutsourcingModel getPegawai_outsourcing() {
//		return pegawai_outsourcing;
//	}
//
//	public void setPegawai_outsourcing(PegawaiOutsourcingModel pegawai_outsourcing) {
//		this.pegawai_outsourcing = pegawai_outsourcing;
//	}
	
	
	
}
