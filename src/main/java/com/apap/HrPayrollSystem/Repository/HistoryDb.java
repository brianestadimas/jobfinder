package com.apap.HrPayrollSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.HrPayrollSystem.Model.HistoryModel;

@Repository
public interface HistoryDb extends JpaRepository<HistoryModel,Long>{

}
