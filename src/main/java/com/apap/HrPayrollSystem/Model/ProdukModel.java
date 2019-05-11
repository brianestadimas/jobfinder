package com.apap.HrPayrollSystem.Model;

import java.io.Serializable;

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
@Table(name="produk")
public class ProdukModel implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max=10)
	@Column(name="nama_produk",nullable=false)
	private String nama_produk;
	
//	@OneToOne(mappedBy = "pegawai_outsourcing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private PegawaiOutsourcingModel pegawai_outsourcing;
//
//	@OneToOne(mappedBy = "history_bekerja", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private HistoryModel history_bekerja;

	
//	public HistoryModel getHistory() {
//		return history_bekerja;
//	}
//
//	public void setHistory(HistoryModel history) {
//		this.history_bekerja = history;
//	}

	public String getNama_produk() {
		return nama_produk;
	}

	public void setNama_produk(String nama_produk) {
		this.nama_produk = nama_produk;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	public PegawaiOutsourcingModel getPegawai_outsourcing() {
//		return pegawai_outsourcing;
//	}
//
//	public void setPegawai_outsourcing(PegawaiOutsourcingModel pegawai_outsourcing) {
//		this.pegawai_outsourcing = pegawai_outsourcing;
//	}
	
	
}
