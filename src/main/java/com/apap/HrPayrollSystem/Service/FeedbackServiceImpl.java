package com.apap.HrPayrollSystem.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.HrPayrollSystem.Model.FeedbackModel;
import com.apap.HrPayrollSystem.Repository.FeedbackDb;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	FeedbackDb feedback_db;
	
	
	@Override
	public List<FeedbackModel> get_all_feedback() {
		// TODO Auto-generated method stub
		return feedback_db.findAll();
	}

	@Override
	public List<FeedbackModel> get_feedback_by_id_pegawai(long id_pegawai) {
		List<FeedbackModel> feedback_by_pegawai = new ArrayList<FeedbackModel>();
		for(int i = 0 ; i < feedback_db.findAll().size() ; i++) {
			if(feedback_db.findAll().get(i).getPegawai_outsourcing().getId() == id_pegawai) {
				feedback_by_pegawai.add(feedback_db.findAll().get(i));
			}
		}
		return feedback_by_pegawai;
	}

	
	
}
