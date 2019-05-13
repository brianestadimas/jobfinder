package com.apap.HrPayrollSystem.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.HrPayrollSystem.Model.PelamarModel;
import com.apap.HrPayrollSystem.Model.PengalamanPelamarModel;
import com.apap.HrPayrollSystem.Repository.PengalamanPelamarDb;

/**
 * Implementasi PengalamanPelamarService
 * 
 * @author Nathanael Lemuella
 *
 */
@Service
@Transactional
public class PengalamanPelamarServiceImpl implements PengalamanPelamarService {

	@Autowired
	PengalamanPelamarDb pengalamanDb;

	@Override
	public PengalamanPelamarModel addPengalaman(PengalamanPelamarModel pengalaman) {
		return pengalamanDb.save(pengalaman);
	}

	@Override
	public void deletePengalaman(PengalamanPelamarModel pengalaman) {
		pengalamanDb.delete(pengalaman);
	}

	@Override
	public List<PengalamanPelamarModel> getAllPengalaman() {
		return pengalamanDb.findAll();
	}

	@Override
	public PengalamanPelamarModel getPengalamanById(long id) {
		return pengalamanDb.findById(id);
	}

	@Override
	public PengalamanPelamarModel updatePengalaman(PengalamanPelamarModel pengalaman) {
		return pengalamanDb.save(pengalaman);
	}

	@Override
	public List<PengalamanPelamarModel> getAllPengalamanByPelamar(PelamarModel pelamar) {
		List<PengalamanPelamarModel> pengalaman_pelamar = new ArrayList<PengalamanPelamarModel>();
		for (PengalamanPelamarModel pengalaman : pengalamanDb.findAll()) {
			if (pengalaman.getPelamar_id().equals(pelamar)) {
				pengalaman_pelamar.add(pengalaman);
			}
		}
		return pengalaman_pelamar;
	}

}
