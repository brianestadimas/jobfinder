package com.apap.HrPayrollSystem.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="variable_gaji")
public class VariableGajiModel {

	@Id
	@Column(name="PTKP")
	private int PTKP;
	
	@Column(name="BPJSTK")
	private int BPJSTK;
	
	@Column(name="BPJSK")
	private int BPJSK;

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
	
	
	
}
