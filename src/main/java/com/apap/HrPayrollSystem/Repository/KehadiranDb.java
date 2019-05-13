package com.apap.HrPayrollSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.HrPayrollSystem.Model.KehadiranModel;

@Repository
public interface KehadiranDb extends JpaRepository<KehadiranModel,Long>{

}
