package com.apap.HrPayrollSystem.Service;

import java.util.Optional;

import com.apap.HrPayrollSystem.Model.VariableGajiModel;


public interface VariableGajiService {
	Optional<VariableGajiModel> get_by_id(long id);
	
}
