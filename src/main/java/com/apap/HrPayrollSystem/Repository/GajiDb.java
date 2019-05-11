package com.apap.HrPayrollSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.HrPayrollSystem.Model.GajiModel;

@Repository
public interface GajiDb extends JpaRepository<GajiModel,Long>{

}
