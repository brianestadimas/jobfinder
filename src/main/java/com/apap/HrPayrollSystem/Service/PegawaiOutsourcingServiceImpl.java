package com.apap.HrPayrollSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Repository.PegawaiOutsourcingDb;

@Service
@Transactional
public class PegawaiOutsourcingServiceImpl implements PegawaiOutsourcingService {

	@Autowired
	PegawaiOutsourcingDb pegawaiOutsourcingDb;
	
	@Override
	public List<PegawaiOutsourcingModel> getAllPegawai() {
		// TODO Auto-generated method stub
		
		return pegawaiOutsourcingDb.findAll();
	}

	@Override
	public Optional<PegawaiOutsourcingModel> getPegawaiById(long id) {
		
		return pegawaiOutsourcingDb.findById(id);
	}

	@Override
	public void deletePegawaiById(long id) {
		pegawaiOutsourcingDb.deleteById(id);
	}

}
