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
	@JoinColumn(name="id_pegawai",referencedColumnName="id",nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PegawaiOutsourcingModel pegawai_outsourcing;	

	@Column(name="pinjaman",nullable=true)
	private long pinjaman;
	
	@Column(name="potongan",nullable=true)
	private long potongan;

	@NotNull
	@Column(name="gaji_net",nullable=false)
	private long gaji_net;
	
	@Column(name="take_home_pay",nullable=true)
	private long take_home_pay;
	
	@Column(name="penambahan_lain_lain",nullable=true)
	private long penambahan_lain_lain;
	
	@Column(name="pengurangan_lain_lain",nullable=true)
	private long pengurangan_lain_lain;
	
	@Column(name="insentif",nullable=true)
	private long insentif;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPotongan() {
		return potongan;
	}

	public void setPotongan(long potongan) {
		this.potongan = potongan;
	}


	public PegawaiOutsourcingModel getPegawai_outsourcing() {
		return pegawai_outsourcing;
	}

	public void setPegawai_outsourcing(PegawaiOutsourcingModel pegawai_outsourcing) {
		this.pegawai_outsourcing = pegawai_outsourcing;
	}

	public long getInsentif() {
		return insentif;
	}

	public void setInsentif(long insentif) {
		this.insentif = insentif;
	}

	public long getPinjaman() {
		return pinjaman;
	}

	public void setPinjaman(long pinjaman) {
		this.pinjaman = pinjaman;
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
	
	public void calculate_potongan(long gaji_pokok, long hari_kerja, long hari_masuk) {
		this.potongan = (gaji_pokok/hari_kerja) * hari_masuk;
	}
	
	

}
