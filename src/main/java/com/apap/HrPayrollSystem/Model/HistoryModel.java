package com.apap.HrPayrollSystem.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="history_bekerja")
public class HistoryModel implements Serializable{
	
	//Fk to pegawaioutsourcing NIP
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="nip_pegawai_outsourcing",referencedColumnName="nip",nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PegawaiOutsourcingModel pegawai_outsourcing;	
	
	//Fk to proyek id
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_proyek",referencedColumnName="id",nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private ProyekModel proyek;
	
	//FK to produk id
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_produk",referencedColumnName="id",nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private ProdukModel produk;
	
	
	@Size(max=255)
	@Column(name="lama_bekerja",nullable = true)
	private long lama_bekerja;
	
	@Size(max=255)
	@Column(name="jabatan",nullable = true)
	private String jabatan;
	
	@Size(max=500)
	@Column(name="alasan_berhenti",nullable = true)
	private String alasan_berhenti;

	public PegawaiOutsourcingModel getPegawai_outsourcing() {
		return pegawai_outsourcing;
	}

	public void setPegawai_outsourcing(PegawaiOutsourcingModel pegawai_outsourcing) {
		this.pegawai_outsourcing = pegawai_outsourcing;
	}

	public ProyekModel getProyek() {
		return proyek;
	}

	public void setProyek(ProyekModel proyek) {
		this.proyek = proyek;
	}

	public ProdukModel getProduk() {
		return produk;
	}

	public void setProduk(ProdukModel produk) {
		this.produk = produk;
	}

	public long getLama_bekerja() {
		return lama_bekerja;
	}

	public void setLama_bekerja(long lama_bekerja) {
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
