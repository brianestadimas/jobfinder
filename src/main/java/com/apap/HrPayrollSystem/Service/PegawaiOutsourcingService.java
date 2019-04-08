package com.apap.HrPayrollSystem.Service;
import java.util.List;
import java.util.Optional;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;

public interface PegawaiOutsourcingService {
	List<PegawaiOutsourcingModel> getAllPegawai();
	Optional<PegawaiOutsourcingModel> getPegawaiById(long id);
	void deletePegawaiById(long id);
	void updatePegawaiStatusById(long id);
	void updatePegawaiProyek(long id, PegawaiOutsourcingModel pegawaiLama);
	void save_all_pegawai_proyek(List<PegawaiOutsourcingModel> listPegawai);
}
