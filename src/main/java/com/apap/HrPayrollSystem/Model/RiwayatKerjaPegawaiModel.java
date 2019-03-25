package com.apap.HrPayrollSystem.Model;

import java.io.Serializable;

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
import javax.persistence.ManyToOne;
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
@Table(name="riwayat_kerja_pegawai")
public class RiwayatKerjaPegawaiModel implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	/*
	 	//Relationship dengan pegawai 
	 	
	 	// Relationship dengan proyek & produk 
	 	
		//Intinya kalo data end date diubah, data pada class ini juga berubah (lihat mock yang detail pegawai : proyek)
		
		//relation dengan class feedback
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="nip_pegawai_outsourcing",referencedColumnName="nip",nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PegawaiOutsourcingModel pegawai_outsourcing_id;	
	
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
	
	@NotNull
	@Size(max=255)
	@Column(name="join_date",nullable = false)
	private Date join_date;	
	
	@NotNull
	@Size(max=255)
	@Column(name="end_date",nullable = false)
	private Date end_date;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="feedback",referencedColumnName="feedback",nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private FeedbackModel feedback;	

}
