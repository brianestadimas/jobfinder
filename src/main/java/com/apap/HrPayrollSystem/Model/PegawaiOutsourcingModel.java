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
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.apap.HrPayrollSystem.Repository.PegawaiOutsourcingDb;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="pegawai_outsourcing")
public class PegawaiOutsourcingModel implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pelamar", referencedColumnName="id",nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PelamarModel pelamar;
	
	@NotNull
	@Size(max=255)
	@Column(name="nip",nullable = false)
	private String nip;
	
	@Size(max=255)
	@Column(name="npwp",nullable = true)
	private String npwp;

	@Size(max=255)
	@Column(name="pkwt",nullable = true)
	private String pkwt;
	
	@Size(max=255)
	@Column(name="no_arsip",nullable = true)
	private String no_arsip;

	@NotNull
	@Size(max=255)
	@Column(name="join_date",nullable = false)
	private Date join_date;	
	
	@NotNull
	@Size(max=255)
	@Column(name="end_date",nullable = false)
	private Date end_date;
	
	@Size(max=255)
	@Column(name="nama_bank",nullable = true)
	private String nama_bank;
	
	@Size(max=255)
	@Column(name="no_rekening",nullable = true)
	private String no_rekening;
	
	@Size(max=255)
	@Column(name="BPJSTK",nullable = true)
	private String bpjstk;
	
	@Size(max=255)
	@Column(name="BPJSK",nullable = true)
	private String bpjsk;
	
	@NotNull
	@Size(max=255)
	@Column(name="status",nullable = false)
	private String status;
	
	//FK to produk
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="produk",referencedColumnName="id",nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private ProdukModel produk;
	
	@NotNull
	@Size(max=255)
	@Column(name="jabatan",nullable = false)
	private String jabatan;
	
	//FK to proyek
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="proyek",referencedColumnName="id",nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private ProyekModel proyek;

//	@OneToMany(mappedBy = "history_bekerja", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private HistoryModel history_bekerja;


	
//	public HistoryModel getHistory_bekerja() {
//		return history_bekerja;
//	}
//
//	public void setHistory_bekerja(HistoryModel history_bekerja) {
//		this.history_bekerja = history_bekerja;
//	}
	public long getId() {
		return this.id;
	}
	public PelamarModel getPelamar() {
		return pelamar;
	}

	public void setPelamar(PelamarModel pelamar) {
		this.pelamar = pelamar;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getNpwp() {
		return npwp;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public String getPkwt() {
		return pkwt;
	}

	public void setPkwt(String pkwt) {
		this.pkwt = pkwt;
	}

	public String getNo_arsip() {
		return no_arsip;
	}

	public void setNo_arsip(String no_arsip) {
		this.no_arsip = no_arsip;
	}

	public Date getJoin_date() {
		return join_date;
	}

	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getNama_bank() {
		return nama_bank;
	}

	public void setNama_bank(String nama_bank) {
		this.nama_bank = nama_bank;
	}

	public String getNo_rekening() {
		return no_rekening;
	}

	public void setNo_rekening(String no_rekening) {
		this.no_rekening = no_rekening;
	}

	public String getBpjstk() {
		return bpjstk;
	}

	public void setBpjstk(String bpjstk) {
		this.bpjstk = bpjstk;
	}

	public String getBpjsk() {
		return bpjsk;
	}

	public void setBpjsk(String bpjsk) {
		this.bpjsk = bpjsk;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ProdukModel getProduk() {
		return produk;
	}

	public void setProduk(ProdukModel produk) {
		this.produk = produk;
	}

	public ProyekModel getProyek() {
		return proyek;
	}

	public void setProyek(ProyekModel proyek) {
		this.proyek = proyek;
	}
	
}

