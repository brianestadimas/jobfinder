package com.apap.HrPayrollSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.HrPayrollSystem.Model.ProyekModel;

@Repository
public interface ProyekDb extends JpaRepository<ProyekModel,Long>{

}
