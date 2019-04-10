package com.apap.HrPayrollSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.HrPayrollSystem.Model.PengalamanPelamarModel;

@Repository
public interface PengalamanPelamarDb extends JpaRepository<PengalamanPelamarModel, Long> {
	
	PengalamanPelamarModel findById (long id);

}