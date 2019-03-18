package com.apap.HrPayrollSystem.Model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="pegawai_outsourcing")
public class PegawaiOutsourcingModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pelamar_id")
	private PelamarModel pelamar;
	
	@NotNull
	@Size(max=255)
	@Column(name="nip",nullable = false)
	private String nip;
	
	@NotNull
	@Size(max=255)
	@Column(name="npwp",nullable = true)
	private String npwp;

	@NotNull
	@Size(max=255)
	@Column(name="pkwt",nullable = true)
	private String pkwt;
	
	@NotNull
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
	
	@NotNull
	@Size(max=255)
	@Column(name="nama_bank",nullable = true)
	private String nama_bank;
	
	@NotNull
	@Size(max=255)
	@Column(name="no_rekening",nullable = true)
	private String no_rekening;
	
	@NotNull
	@Size(max=255)
	@Column(name="BPJSTK",nullable = true)
	private String bpjstk;
	
	@NotNull
	@Size(max=255)
	@Column(name="BPJSK",nullable = true)
	private String bpjsk;
	
	@NotNull
	@Size(max=255)
	@Column(name="status",nullable = false)
	private String status;
	
	//FK to produk
	//FK to proyek
}
