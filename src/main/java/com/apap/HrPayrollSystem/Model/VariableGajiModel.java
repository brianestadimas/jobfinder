package com.apap.HrPayrollSystem.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="variable_gaji")
public class VariableGajiModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="PTKP",nullable = true)
	private int PTKP;
	
	@Column(name="BPJSTK",nullable = true)
	private int BPJSTK;
	
	@Column(name="BPJSK",nullable = true)
	private int BPJSK;
	
	@Column(name="default_potongan",nullable = true)
	private int default_potongan;
	
	@Column(name="persenan_pph",nullable = true)
	private int persenan_pph;
	
	public int getPTKP() {
		return PTKP;
	}

	public void setPTKP(int pTKP) {
		PTKP = pTKP;
	}

	public int getBPJSTK() {
		return BPJSTK;
	}

	public void setBPJSTK(int bPJSTK) {
		BPJSTK = bPJSTK;
	}

	public int getBPJSK() {
		return BPJSK;
	}

	public void setBPJSK(int bPJSK) {
		BPJSK = bPJSK;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDefault_potongan() {
		return default_potongan;
	}

	public void setDefault_potongan(int default_potongan) {
		this.default_potongan = default_potongan;
	}

	public int getPersenan_pph() {
		return persenan_pph;
	}

	public void setPersenan_pph(int persenan_pph) {
		this.persenan_pph = persenan_pph;
	}
	
	
	
	
}
