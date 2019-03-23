package com.apap.HrPayrollSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.HrPayrollSystem.Model.ProyekModel;
import com.apap.HrPayrollSystem.Repository.ProyekDb;

@Service
@Transactional
public class ProyekServiceImpl implements ProyekService {

	@Autowired
	ProyekDb proyekDb;
	
	@Override
	public List<ProyekModel> getAllProyek() {
		// TODO Auto-generated method stub
		return proyekDb.findAll();
	}

}
