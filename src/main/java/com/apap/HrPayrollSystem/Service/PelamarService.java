package com.apap.HrPayrollSystem.Service;

import java.util.List;

import com.apap.HrPayrollSystem.Model.PelamarModel;

/**
 * Service kelas Pelamar
 * 
 * @author Nathanael Lemuella
 *
 */
public interface PelamarService {

	PelamarModel addPelamar(PelamarModel pelamar);
	
	void deletePelamar(PelamarModel pelamar);
	
	void deleteBanyakPelamar (List<PelamarModel> pelamar);

	List<PelamarModel> getAllPelamar();

	PelamarModel getPelamarById(long id);

	PelamarModel updatePelamar(PelamarModel pelamar);

}
