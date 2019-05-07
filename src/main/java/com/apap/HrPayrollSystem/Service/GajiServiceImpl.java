package com.apap.HrPayrollSystem.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.HrPayrollSystem.Model.GajiModel;
import com.apap.HrPayrollSystem.Repository.GajiDb;

@Service
@Transactional
public class GajiServiceImpl implements GajiService {
	
	@Autowired
	GajiDb gaji_db;
	
	@Override
	public void save_all_gaji(List<GajiModel> list_penggajian) {
		// TODO Auto-generated method stub
		gaji_db.saveAll(list_penggajian);
	}

	@Override
	public void save_gaji(GajiModel gaji) {
		// TODO Auto-generated method stub
		gaji_db.save(gaji);
	}

	
	
}
