package com.apap.HrPayrollSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;

@Repository
public interface PegawaiOutsourcingDb extends JpaRepository<PegawaiOutsourcingModel,Long>{
	List<PegawaiOutsourcingModel> findByStatus(boolean status);

}
