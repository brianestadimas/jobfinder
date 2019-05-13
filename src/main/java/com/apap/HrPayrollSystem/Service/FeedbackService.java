package com.apap.HrPayrollSystem.Service;

import java.util.List;

import com.apap.HrPayrollSystem.Model.FeedbackModel;

public interface FeedbackService {
	List<FeedbackModel> get_all_feedback();
	List<FeedbackModel> get_feedback_by_id_pegawai(long id_pegawai);
	void save_feedback(FeedbackModel feedback);
	FeedbackModel get_feedback_by_id(long id_feedback);
	void delete_feedback(long id_feedback);
}
