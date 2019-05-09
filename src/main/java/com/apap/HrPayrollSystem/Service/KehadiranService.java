package com.apap.HrPayrollSystem.Service;

import java.util.List;

import com.apap.HrPayrollSystem.Model.KehadiranModel;
import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.ProyekModel;
import com.apap.HrPayrollSystem.Utility.PerformaWrapper;

public interface KehadiranService {

	List<KehadiranModel> get_all_kehadiran();

	void delete_kehadiran(KehadiranModel kehadiran);

	void save_all_kehadiran(List<KehadiranModel> kehadiran_kehadiran);

	List<KehadiranModel> get_all_kehadiran_by_pegawai(PegawaiOutsourcingModel pegawai);

	List<PerformaWrapper> get_all_kehadiran_by_proyek(ProyekModel proyek);
}
