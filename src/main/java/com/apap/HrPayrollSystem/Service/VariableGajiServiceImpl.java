package com.apap.HrPayrollSystem.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.HrPayrollSystem.Model.VariableGajiModel;
import com.apap.HrPayrollSystem.Repository.ProyekDb;
import com.apap.HrPayrollSystem.Repository.VariableGajiDb;

@Service
@Transactional
public class VariableGajiServiceImpl implements VariableGajiService{

	@Autowired
	VariableGajiDb variableGajiDb;
	
	@Override
	public void updateVariableGaji(VariableGajiModel newVarGaji) {
		VariableGajiModel varGaji = variableGajiDb.findById((long) 1).get();
		varGaji.setPTKP(newVarGaji.getPTKP());
		varGaji.setBPJSK(newVarGaji.getBPJSK());
		varGaji.setBPJSTK(newVarGaji.getBPJSTK());
		varGaji.setPersenan_pph(newVarGaji.getPersenan_pph());
	}

	@Override
	public VariableGajiModel getVariableGaji() {
		// TODO Auto-generated method stub
		return variableGajiDb.getOne((long) 1);
	}

	@Override
	public void saveVariableGaji(VariableGajiModel varGaji) {
		// TODO Auto-generated method stub
		variableGajiDb.save(varGaji);
	}

	@Override
	public boolean checkNull() {
		// TODO Auto-generated method stub
		if(variableGajiDb.existsById((long)1)) {
			return false;
		}
		return true;
	}

}
