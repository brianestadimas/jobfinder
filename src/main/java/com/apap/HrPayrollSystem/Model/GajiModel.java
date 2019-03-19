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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="gaji")
public class GajiModel implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	//FK to NIP
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="nip_pegawai_outsourcing",referencedColumnName="nip",nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PegawaiOutsourcingModel pegawai_outsourcing;	
		
	@NotNull
	@Size(max=255)
	@Column(name="gaji_pokok",nullable=false)
	private long gaji_pokok;
	
	@Size(max=255)
	@Column(name="tunjangan",nullable=true)
	private long tunjangan;
	
	@Size(max=255)
	@Column(name="pinjaman",nullable=true)
	private long pinjaman;
	
	@NotNull
	@Size(max=255)
	@Column(name="no_rekening",nullable=false)
	private String no_rekening;
	
	@NotNull
	@Size(max=255)
	@Column(name="gaji_net",nullable=false)
	private long gaji_net;
	
	@Size(max=255)
	@Column(name="take_home_pay",nullable=true)
	private long take_home_pay;
	
	@Size(max=255)
	@Column(name="penambahan_lain_lain",nullable=true)
	private long penambahan_lain_lain;
	
	@Size(max=255)
	@Column(name="pengurangan_lain_lain",nullable=true)
	private long pengurangan_lain_lain;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGaji_pokok() {
		return gaji_pokok;
	}

	public void setGaji_pokok(long gaji_pokok) {
		this.gaji_pokok = gaji_pokok;
	}

	public long getTunjangan() {
		return tunjangan;
	}

	public void setTunjangan(long tunjangan) {
		this.tunjangan = tunjangan;
	}

	public long getPinjaman() {
		return pinjaman;
	}

	public void setPinjaman(long pinjaman) {
		this.pinjaman = pinjaman;
	}

	public String getNo_rekening() {
		return no_rekening;
	}

	public void setNo_rekening(String no_rekening) {
		this.no_rekening = no_rekening;
	}

	public long getGaji_net() {
		return gaji_net;
	}

	public void setGaji_net(long gaji_net) {
		this.gaji_net = gaji_net;
	}

	public long getTake_home_pay() {
		return take_home_pay;
	}

	public void setTake_home_pay(long take_home_pay) {
		this.take_home_pay = take_home_pay;
	}

	public long getPenambahan_lain_lain() {
		return penambahan_lain_lain;
	}

	public void setPenambahan_lain_lain(long penambahan_lain_lain) {
		this.penambahan_lain_lain = penambahan_lain_lain;
	}

	public long getPengurangan_lain_lain() {
		return pengurangan_lain_lain;
	}

	public void setPengurangan_lain_lain(long pengurangan_lain_lain) {
		this.pengurangan_lain_lain = pengurangan_lain_lain;
	}

}
