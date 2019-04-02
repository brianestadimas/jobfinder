package com.apap.HrPayrollSystem.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.HrPayrollSystem.Model.KehadiranModel;
import com.apap.HrPayrollSystem.Repository.KehadiranDb;

@Service
@Transactional
public class KehadiranServiceImpl implements KehadiranService{

	@Autowired
	private KehadiranDb kehadiran_Db;
	@Autowired
	private PegawaiOutsourcingService pegawaiOutsourcing;
	
	@Override
	public List<KehadiranModel> get_all_kehadiran() {
		// TODO Auto-generated method stub
		return kehadiran_Db.findAll();
	}

	@Override
	public void delete_kehadiran(KehadiranModel kehadiran) {
		// TODO Auto-generated method stub
		kehadiran_Db.delete(kehadiran);
	}

}
