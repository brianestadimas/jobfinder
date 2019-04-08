package com.apap.HrPayrollSystem.Service;
import java.util.List;
import java.util.Optional;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;

public interface PegawaiOutsourcingService {
	List<PegawaiOutsourcingModel> getAllPegawai();
	Optional<PegawaiOutsourcingModel> getPegawaiById(long id);
	void deletePegawaiById(long id);
	void updatePegawaiStatusById(long id);
	void updatePegawai(long id, PegawaiOutsourcingModel pegawai);
}
