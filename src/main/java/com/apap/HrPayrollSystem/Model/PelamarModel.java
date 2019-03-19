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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="pelamar")
public class PelamarModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max=255)
	@Column(name="nama_lengkap",nullable = false)
	private String nama_lengkap;
	
	@Size(max=255)
	@Column(name="nama_panggilan",nullable = true)
	private String nama_panggilan;
	
	@NotNull
	@Size(max=255)
	@Column(name="gender",nullable = true)
	private String gender;
	
	@NotNull
	@Size(max=255)
	@Column(name="tanggal_lahir",nullable = false)
	private Date tanggal_lahir;
	
	@NotNull
	@Size(max=255)
	@Column(name="status_marital",nullable = false)
	private String status_marital;
	
	@NotNull
	@Size(max=255)
	@Column(name="no_ktp",nullable = false)
	private String no_ktp;
	
	@NotNull
	@Size(max=255)
	@Column(name="telepon",nullable = false)
	private String telepon;
	
	@Size(max=255)
	@Column(name="telepon_orang_terdekat",nullable = true)
	private String telepon_orang_terdekat;
	
	@Size(max=255)
	@Column(name="nomor_whatsapp",nullable = true)
	private String nomor_whatsapp;
	
	@Size(max=255)
	@Column(name="email",nullable = true)
	private String email;
	
	@NotNull
	@Size(max=255)
	@Column(name="produk_dilamar",nullable = false)
	private String produk_dilamar;
	
	@NotNull
	@Size(max=255)
	@Column(name="jabatan_dilamar",nullable = false)
	private String jabatan_dilamar;
	
	@NotNull
	@Size(max=255)
	@Column(name="alamat",nullable = false)
	private String alamat;
	
	@NotNull
	@Size(max=255)
	@Column(name="region",nullable = false)
	private String region;
	
	@NotNull
	@Size(max=255)
	@Column(name="pendidikan_terakhir",nullable = false)
	private String pendidikan_terakhir;
	
	@NotNull
	@Size(max=255)
	@Column(name="apply_date",nullable = false)
	private Date apply_date;

//    @OneToOne(mappedBy = "pelamar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private PegawaiOutsourcingModel pegawai_Outsourcing;
//	
//    @OneToMany(mappedBy="pengalaman_pelamar", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//    private PengalamanPelamarModel pengalaman_Pelamar;
	
    public long getId() {
		return id;
	}

	public String getTelepon_orang_terdekat() {
		return telepon_orang_terdekat;
	}

	public void setTelepon_orang_terdekat(String telepon_orang_terdekat) {
		this.telepon_orang_terdekat = telepon_orang_terdekat;
	}

	public String getNomor_whatsapp() {
		return nomor_whatsapp;
	}

	public void setNomor_whatsapp(String nomor_whatsapp) {
		this.nomor_whatsapp = nomor_whatsapp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama_lengkap() {
		return nama_lengkap;
	}

	public void setNama_lengkap(String nama_lengkap) {
		this.nama_lengkap = nama_lengkap;
	}

	public String getNama_panggilan() {
		return nama_panggilan;
	}

	public void setNama_panggilan(String nama_panggilan) {
		this.nama_panggilan = nama_panggilan;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getTanggal_lahir() {
		return tanggal_lahir;
	}

	public void setTanggal_lahir(Date tanggal_lahir) {
		this.tanggal_lahir = tanggal_lahir;
	}

	public String getStatus_marital() {
		return status_marital;
	}

	public void setStatus_marital(String status_marital) {
		this.status_marital = status_marital;
	}

	public String getNo_ktp() {
		return no_ktp;
	}

	public void setNo_ktp(String no_ktp) {
		this.no_ktp = no_ktp;
	}

	public String getTelepon() {
		return telepon;
	}

	public void setTelepon(String telepon) {
		this.telepon = telepon;
	}

	public String getTelepon_penjamin() {
		return telepon_orang_terdekat;
	}

	public void setTelepon_penjamin(String telepon_penjamin) {
		this.telepon_orang_terdekat = telepon_penjamin;
	}

	public String getProduk_dilamar() {
		return produk_dilamar;
	}

	public void setProduk_dilamar(String produk_dilamar) {
		this.produk_dilamar = produk_dilamar;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPendidikan_terakhir() {
		return pendidikan_terakhir;
	}

	public void setPendidikan_terakhir(String pendidikan_terakhir) {
		this.pendidikan_terakhir = pendidikan_terakhir;
	}

	public Date getApply_date() {
		return apply_date;
	}

	public void setApply_date(Date apply_date) {
		this.apply_date = apply_date;
	}
	
	
}
