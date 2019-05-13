package com.apap.HrPayrollSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.HrPayrollSystem.Model.ProdukModel;

@Repository
public interface ProdukDb extends JpaRepository<ProdukModel,Long>{

}
