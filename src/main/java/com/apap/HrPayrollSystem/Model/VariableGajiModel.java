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
	
	@Column(name="BPJSTK",nullable = true, precision=3, scale=2)
	private float BPJSTK;
	
	@Column(name="BPJSK",nullable = true, precision=3, scale=2)
	private float BPJSK;
		
	@Column(name="persenan_pph",nullable = true, precision=3, scale=2)
	private float persenan_pph;
	
	public int getPTKP() {
		return PTKP;
	}

	public void setPTKP(int pTKP) {
		PTKP = pTKP;
	}

	public float getBPJSTK() {
		return BPJSTK;
	}

	public void setBPJSTK(float bPJSTK) {
		BPJSTK = bPJSTK;
	}

	public float getBPJSK() {
		return BPJSK;
	}

	public void setBPJSK(float bPJSK) {
		BPJSK = bPJSK;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getPersenan_pph() {
		return persenan_pph;
	}

	public void setPersenan_pph(float persenan_pph) {
		this.persenan_pph = persenan_pph;
	}
	
	
	
	
}
