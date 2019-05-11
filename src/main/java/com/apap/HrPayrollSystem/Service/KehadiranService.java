package com.apap.HrPayrollSystem.Service;

import java.util.List;

import com.apap.HrPayrollSystem.Model.KehadiranModel;

public interface KehadiranService {

	List<KehadiranModel> get_all_kehadiran();
	void delete_kehadiran(KehadiranModel kehadiran);
	void save_all_kehadiran(List<KehadiranModel> kehadiran_kehadiran);
}
