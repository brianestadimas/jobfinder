package com.apap.HrPayrollSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.HrPayrollSystem.Model.VariableGajiModel;

@Repository
public interface VariableGajiDb extends JpaRepository<VariableGajiModel,Long>{

}
