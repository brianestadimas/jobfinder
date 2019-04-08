package com.apap.HrPayrollSystem.Service;
import java.util.List;
import java.util.Optional;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;

public interface PegawaiOutsourcingService {
	List<PegawaiOutsourcingModel> getAllPegawai();
	Optional<PegawaiOutsourcingModel> getPegawaiById(long id);
	void deletePegawaiById(long id);
	void updatePegawaiStatusById(long id);
	void addPegawai(PegawaiOutsourcingModel pegawai);
	void updatePegawai(PegawaiOutsourcingModel pegawai);
	void assignAll(List<PegawaiOutsourcingModel> list_pegawai);
}
