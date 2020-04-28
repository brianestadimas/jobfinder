package com.apap.HrPayrollSystem.Repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apap.HrPayrollSystem.Model.PelamarModel;

@Repository
public interface PelamarDb extends JpaRepository<PelamarModel,Long>{

	@Query("select t from PelamarModel t where t.is_pegawai = :statusNow") 
	List<PelamarModel> findAllPelamar(@Param("statusNow") boolean statusNow);
	
	PelamarModel findById(long id);

}
