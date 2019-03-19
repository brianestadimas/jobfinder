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
}
