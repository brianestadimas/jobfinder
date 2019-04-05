package com.apap.HrPayrollSystem.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.HrPayrollSystem.Model.RiwayatKerjaPegawaiModel;

public interface RiwayatKerjaPegawaiService {
	List<RiwayatKerjaPegawaiModel> getAllRiwayat(String nip);
	void addRiwayat(long id);
}
