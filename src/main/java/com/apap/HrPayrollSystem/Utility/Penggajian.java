package com.apap.HrPayrollSystem.Utility;

import com.apap.HrPayrollSystem.Model.GajiModel;
import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;

public class Penggajian {
	GajiModel gaji = new GajiModel();
	PegawaiOutsourcingModel pegawai = new PegawaiOutsourcingModel();
	int gaji_bruto = 0; 
	int bpjstk = 0;
	int bpjsk = 0;
	int total_bpjs = 0;
	int jumlah_gaji = 0;
	int ptkp = 0;
	int pkp = 0; 
	int pph21 = 0;
	int total_potongan = 0;
	int gaji_netto = 0;
	

	public Penggajian(GajiModel gaji, PegawaiOutsourcingModel pegawai, int gaji_bruto, int bpjstk, int bpjsk,
			int total_bpjs, int jumlah_gaji, int ptkp, int pkp, int pph21, int total_potongan, int gaji_netto) {
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

	public int getGaji_bruto() {
		return gaji_bruto;
	}

	public void setGaji_bruto(int gaji_bruto) {
		this.gaji_bruto = gaji_bruto;
	}

	public int getBpjstk() {
		return bpjstk;
	}

	public void setBpjstk(int bpjstk) {
		this.bpjstk = bpjstk;
	}

	public int getBpjsk() {
		return bpjsk;
	}

	public void setBpjsk(int bpjsk) {
		this.bpjsk = bpjsk;
	}

	public int getTotal_bpjs() {
		return total_bpjs;
	}

	public void setTotal_bpjs(int total_bpjs) {
		this.total_bpjs = total_bpjs;
	}

	public int getJumlah_gaji() {
		return jumlah_gaji;
	}

	public void setJumlah_gaji(int jumlah_gaji) {
		this.jumlah_gaji = jumlah_gaji;
	}

	public int getPtkp() {
		return ptkp;
	}

	public void setPtkp(int ptkp) {
		this.ptkp = ptkp;
	}

	public int getPkp() {
		return pkp;
	}

	public void setPkp(int pkp) {
		this.pkp = pkp;
	}

	public int getPph21() {
		return pph21;
	}

	public void setPph21(int pph21) {
		this.pph21 = pph21;
	}

	public int getTotal_potongan() {
		return total_potongan;
	}

	public void setTotal_potongan(int total_potongan) {
		this.total_potongan = total_potongan;
	}

	public int getGaji_netto() {
		return gaji_netto;
	}

	public void setGaji_netto(int gaji_netto) {
		this.gaji_netto = gaji_netto;
	}
	
	
	
	
	
	
}
