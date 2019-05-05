package com.apap.HrPayrollSystem.Service;

import java.util.List;

import com.apap.HrPayrollSystem.Model.FeedbackModel;

public interface FeedbackService {
	List<FeedbackModel> get_all_feedback();
	List<FeedbackModel> get_feedback_by_id_pegawai(long id_pegawai);
}
