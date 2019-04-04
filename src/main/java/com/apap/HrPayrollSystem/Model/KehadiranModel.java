package com.apap.HrPayrollSystem.Model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="kehadiran")
public class KehadiranModel implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY)
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
	
	//FK produk id
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_produk",referencedColumnName="id",nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private ProdukModel produk;
	
	@NotNull
	@Size(max=255)
	@Column(name="judul_kehadiran",nullable = false)
	private String judul_kehadiran;
	
	@NotNull
	@Size(max=255)
	@Column(name="jumlah_hari_kerja",nullable = false)
	private int jumlah_hari_kerja;
	
	@NotNull
	@Size(max=255)
	@Column(name="jumlah_kehadiran",nullable = false)
	private int jumlah_kehadiran;
	
	@NotNull
	@Size(max=255)
	@Column(name="jumlah_absen",nullable = false)
	private int jumlah_absen;
	
	@NotNull
	@Size(max=255)
	@Column(name="jumlah_sakit",nullable = false)
	private int jumlah_sakit;
	
	@NotNull
	@Size(max=255)
	@Column(name="jumlah_izin",nullable = false)
	private int jumlah_izin;
	
	@NotNull
	@Size(max=255)
	@Column(name="jumlah_cuti",nullable = false)
	private int jumlah_cuti;
	
	@NotNull
	@Size(max=255)
	@Column(name="jumlah_off",nullable = false)
	private int jumlah_off;
	
	@NotNull
	@Size(max=255)
	@Column(name="jumlah_lain_lain",nullable = false)
	private int jumlah_lain_lain;
	
	@NotNull
	@Size(max=255)
	@Column(name="tanggal_pengisian_kehadiran",nullable = false)
	private Date tanggal_pengisian_kehadiran;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJudul_kehadiran() {
		return judul_kehadiran;
	}

	public void setJudul_kehadiran(String judul_kehadiran) {
		this.judul_kehadiran = judul_kehadiran;
	}

	public int getJumlah_hari_kerja() {
		return jumlah_hari_kerja;
	}

	public void setJumlah_hari_kerja(int jumlah_hari_kerja) {
		this.jumlah_hari_kerja = jumlah_hari_kerja;
	}

	public int getJumlah_kehadiran() {
		return jumlah_kehadiran;
	}

	public void setJumlah_kehadiran(int jumlah_kehadiran) {
		this.jumlah_kehadiran = jumlah_kehadiran;
	}

	public int getJumlah_absen() {
		return jumlah_absen;
	}

	public void setJumlah_absen(int jumlah_absen) {
		this.jumlah_absen = jumlah_absen;
	}

	public int getJumlah_sakit() {
		return jumlah_sakit;
	}

	public void setJumlah_sakit(int jumlah_sakit) {
		this.jumlah_sakit = jumlah_sakit;
	}

	public int getJumlah_izin() {
		return jumlah_izin;
	}

	public void setJumlah_izin(int jumlah_izin) {
		this.jumlah_izin = jumlah_izin;
	}

	public int getJumlah_cuti() {
		return jumlah_cuti;
	}

	public void setJumlah_cuti(int jumlah_cuti) {
		this.jumlah_cuti = jumlah_cuti;
	}

	public int getJumlah_off() {
		return jumlah_off;
	}

	public void setJumlah_off(int jumlah_off) {
		this.jumlah_off = jumlah_off;
	}

	public Date getTanggal_pengisian_kehadiran() {
		return tanggal_pengisian_kehadiran;
	}

	public void setTanggal_pengisian_kehadiran(Date tanggal_pengisian_kehadiran) {
		this.tanggal_pengisian_kehadiran = tanggal_pengisian_kehadiran;
	}
	
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

	public int getJumlah_lain_lain() {
		return jumlah_lain_lain;
	}

	public void setJumlah_lain_lain(int jumlah_lain_lain) {
		this.jumlah_lain_lain = jumlah_lain_lain;
	}

	public ProdukModel getProduk() {
		return produk;
	}

	public void setProduk(ProdukModel produk) {
		this.produk = produk;
	}


}
