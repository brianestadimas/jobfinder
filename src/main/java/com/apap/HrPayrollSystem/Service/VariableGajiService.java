package com.apap.HrPayrollSystem.Service;

import com.apap.HrPayrollSystem.Model.VariableGajiModel;

public interface VariableGajiService {

	//get variable gaji
	VariableGajiModel getVariableGaji();
	
	//Update
	void updateVariableGaji(VariableGajiModel newVarGaji);

	//Save
	void saveVariableGaji(VariableGajiModel varGaji);
	
	//null checker
	boolean checkNull();
}
