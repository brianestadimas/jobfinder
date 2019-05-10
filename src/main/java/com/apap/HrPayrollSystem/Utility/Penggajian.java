package com.apap.HrPayrollSystem.Utility;

import com.apap.HrPayrollSystem.Model.GajiModel;
import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;

public class Penggajian {
	GajiModel gaji = new GajiModel();
	PegawaiOutsourcingModel pegawai = new PegawaiOutsourcingModel();
	float gaji_bruto = 0; 
	float bpjstk = 0;
	float bpjsk = 0;
	float total_bpjs = 0;
	float jumlah_gaji = 0;
	float ptkp = 0;
	float pkp = 0; 
	float pph21 = 0;
	float total_potongan = 0;
	float gaji_netto = 0;
	

	public Penggajian(GajiModel gaji, PegawaiOutsourcingModel pegawai, float gaji_bruto, float bpjstk, float bpjsk,
			float total_bpjs, float jumlah_gaji, float ptkp, float pkp, float pph21, float total_potongan, float gaji_netto) {
		super();
		this.gaji = gaji;
		this.pegawai = pegawai;
		this.gaji_bruto = gaji_bruto;
		this.bpjstk = bpjstk;
		this.bpjsk = bpjsk;
		this.total_bpjs = total_bpjs;
		this.jumlah_gaji = jumlah_gaji;
		this.ptkp = ptkp;
		this.pkp = pkp;
		this.pph21 = pph21;
		this.total_potongan = total_potongan;
		this.gaji_netto = gaji_netto;
	}

	public GajiModel getGaji() {
		return gaji;
	}

	public void setGaji(GajiModel gaji) {
		this.gaji = gaji;
	}

	public PegawaiOutsourcingModel getPegawai() {
		return pegawai;
	}

	public void setPegawai(PegawaiOutsourcingModel pegawai) {
		this.pegawai = pegawai;
	}

	public float getGaji_bruto() {
		return gaji_bruto;
	}

	public void setGaji_bruto(float gaji_bruto) {
		this.gaji_bruto = gaji_bruto;
	}

	public float getBpjstk() {
		return bpjstk;
	}

	public void setBpjstk(float bpjstk) {
		this.bpjstk = bpjstk;
	}

	public float getBpjsk() {
		return bpjsk;
	}

	public void setBpjsk(float bpjsk) {
		this.bpjsk = bpjsk;
	}

	public float getTotal_bpjs() {
		return total_bpjs;
	}

	public void setTotal_bpjs(float total_bpjs) {
		this.total_bpjs = total_bpjs;
	}

	public float getJumlah_gaji() {
		return jumlah_gaji;
	}

	public void setJumlah_gaji(float jumlah_gaji) {
		this.jumlah_gaji = jumlah_gaji;
	}

	public float getPtkp() {
		return ptkp;
	}

	public void setPtkp(float ptkp) {
		this.ptkp = ptkp;
	}

	public float getPkp() {
		return pkp;
	}

	public void setPkp(float pkp) {
		this.pkp = pkp;
	}

	public float getPph21() {
		return pph21;
	}

	public void setPph21(float pph21) {
		this.pph21 = pph21;
	}

	public float getTotal_potongan() {
		return total_potongan;
	}

	public void setTotal_potongan(float total_potongan) {
		this.total_potongan = total_potongan;
	}

	public float getGaji_netto() {
		return gaji_netto;
	}

	public void setGaji_netto(float gaji_netto) {
		this.gaji_netto = gaji_netto;
	}
	
	
	
	
	
	
}
