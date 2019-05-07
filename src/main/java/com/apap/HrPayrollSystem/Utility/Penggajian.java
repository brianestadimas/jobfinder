package com.apap.HrPayrollSystem.Utility;

import com.apap.HrPayrollSystem.Model.GajiModel;
import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;

public class Penggajian {
	GajiModel gaji = new GajiModel();
	PegawaiOutsourcingModel pegawai = new PegawaiOutsourcingModel();
	long gaji_bruto = 0; 
	long bpjstk = 0;
	long bpjsk = 0;
	long total_bpjs = 0;
	long jumlah_gaji = 0;
	long ptkp = 0;
	long pkp = 0; 
	long pph21 = 0;
	long total_potongan = 0;
	long gaji_netto = 0;
	

	public Penggajian(GajiModel gaji, PegawaiOutsourcingModel pegawai, long gaji_bruto, long bpjstk, long bpjsk,
			long total_bpjs, long jumlah_gaji, long ptkp, long pkp, long pph21, long total_potongan, long gaji_netto) {
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

	public long getGaji_bruto() {
		return gaji_bruto;
	}

	public void setGaji_bruto(long gaji_bruto) {
		this.gaji_bruto = gaji_bruto;
	}

	public long getBpjstk() {
		return bpjstk;
	}

	public void setBpjstk(long bpjstk) {
		this.bpjstk = bpjstk;
	}

	public long getBpjsk() {
		return bpjsk;
	}

	public void setBpjsk(long bpjsk) {
		this.bpjsk = bpjsk;
	}

	public long getTotal_bpjs() {
		return total_bpjs;
	}

	public void setTotal_bpjs(long total_bpjs) {
		this.total_bpjs = total_bpjs;
	}

	public long getJumlah_gaji() {
		return jumlah_gaji;
	}

	public void setJumlah_gaji(long jumlah_gaji) {
		this.jumlah_gaji = jumlah_gaji;
	}

	public long getPtkp() {
		return ptkp;
	}

	public void setPtkp(long ptkp) {
		this.ptkp = ptkp;
	}

	public long getPkp() {
		return pkp;
	}

	public void setPkp(long pkp) {
		this.pkp = pkp;
	}

	public long getPph21() {
		return pph21;
	}

	public void setPph21(long pph21) {
		this.pph21 = pph21;
	}

	public long getTotal_potongan() {
		return total_potongan;
	}

	public void setTotal_potongan(long total_potongan) {
		this.total_potongan = total_potongan;
	}

	public long getGaji_netto() {
		return gaji_netto;
	}

	public void setGaji_netto(long gaji_netto) {
		this.gaji_netto = gaji_netto;
	}
	
	
	
	
	
	
}
