package com.apap.HrPayrollSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.HrPayrollSystem.Model.FeedbackModel;

@Repository
public interface FeedbackDb extends JpaRepository<FeedbackModel, Long>{

}
