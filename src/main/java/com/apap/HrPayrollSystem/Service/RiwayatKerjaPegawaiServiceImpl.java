package com.apap.HrPayrollSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.apap.HrPayrollSystem.Repository.PegawaiOutsourcingDb;
import com.apap.HrPayrollSystem.Repository.RiwayatKerjaPegawai;
import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.RiwayatKerjaPegawaiModel;

public class RiwayatKerjaPegawaiServiceImpl implements RiwayatKerjaPegawaiService {

	@Autowired
	RiwayatKerjaPegawai riwayatKerjaPegawaiDb;
	@Autowired
	PegawaiOutsourcingDb pegawaiOutsourcingDb;
	
	@Override
	public List<RiwayatKerjaPegawaiModel> getAllRiwayat(String nip) {
		// TODO Auto-generated method stub
		return riwayatKerjaPegawaiDb.findNipPegawaiOutsourcing(nip);
	}

	@Override
	public void addRiwayat(long id) {
		// TODO Auto-generated method stub
		PegawaiOutsourcingModel obj = pegawaiOutsourcingDb.getOne(id);
		RiwayatKerjaPegawaiModel data = new RiwayatKerjaPegawaiModel();
		//data.setEnd_date();
		
	}
	
}
