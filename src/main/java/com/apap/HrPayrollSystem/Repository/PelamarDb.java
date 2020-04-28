package com.apap.HrPayrollSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apap.HrPayrollSystem.Model.PelamarModel;

@Repository
public interface PelamarDb extends JpaRepository<PelamarModel,Long>{

	@Query(value="SELECT * FROM PelamarModel WHERE is_pegawai = false", nativeQuery=true) 
	List<PelamarModel> findAllPelamar();
	
	PelamarModel findById(long id);

}
