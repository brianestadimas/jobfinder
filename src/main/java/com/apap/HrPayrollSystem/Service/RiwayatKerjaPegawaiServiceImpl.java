package com.apap.HrPayrollSystem.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.RiwayatKerjaPegawaiModel;
import com.apap.HrPayrollSystem.Repository.PegawaiOutsourcingDb;
import com.apap.HrPayrollSystem.Repository.RiwayatKerjaPegawai;

@Service
@Transactional
public class RiwayatKerjaPegawaiServiceImpl implements RiwayatKerjaPegawaiService {

	@Autowired
	RiwayatKerjaPegawai riwayatKerjaPegawaiDb;
	@Autowired
	PegawaiOutsourcingDb pegawaiOutsourcingDb;
	
	@Override
	public List<RiwayatKerjaPegawaiModel> getAllRiwayat(String nip) {
		// TODO Auto-generated method stub
//		return riwayatKerjaPegawaiDb.findNipPegawaiOutsourcing(nip);
		return new ArrayList<RiwayatKerjaPegawaiModel>(); // yang diubah
	}

	@Override
	public void addRiwayat(long id) {
		// TODO Auto-generated method stub
		PegawaiOutsourcingModel obj = pegawaiOutsourcingDb.getOne(id);
		RiwayatKerjaPegawaiModel data = new RiwayatKerjaPegawaiModel();
		//data.setEnd_date();
		
	}
	
}
