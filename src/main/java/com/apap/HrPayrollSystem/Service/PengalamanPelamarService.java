package com.apap.HrPayrollSystem.Service;

import java.util.List;

import com.apap.HrPayrollSystem.Model.PengalamanPelamarModel;

/**
 * Interface Service PengalamanPelamar
 * 
 * @author Nathanael Lemuella
 *
 */
public interface PengalamanPelamarService {
	
	PengalamanPelamarModel addPengalaman(PengalamanPelamarModel pengalaman);

	void deletePengalaman(PengalamanPelamarModel pengalaman);

	List<PengalamanPelamarModel> getAllPengalaman();

	PengalamanPelamarModel getPengalamanById(long id);

	PengalamanPelamarModel updatePengalaman(PengalamanPelamarModel pengalaman);

}
