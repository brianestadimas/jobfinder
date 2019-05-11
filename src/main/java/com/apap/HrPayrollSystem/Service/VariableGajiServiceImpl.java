package com.apap.HrPayrollSystem.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.HrPayrollSystem.Model.VariableGajiModel;
import com.apap.HrPayrollSystem.Repository.VariableGajiDb;

@Transactional
@Service
public class VariableGajiServiceImpl implements VariableGajiService{
	@Autowired
	VariableGajiDb variable_gaji;

	@Override
	public Optional<VariableGajiModel> get_by_id(long id) {
		// TODO Auto-generated method stub
		return variable_gaji.findById(id);
	}
	
	
	

		
}
