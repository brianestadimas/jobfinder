package com.apap.HrPayrollSystem.Service;




import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.HrPayrollSystem.Model.PelamarModel;
import com.apap.HrPayrollSystem.Repository.PelamarDb;

@Service
@Transactional
public class PelamarServiceImpl implements PelamarService {

	@Autowired
	PelamarDb pelamarDb;

	@Override
	public PelamarModel addPelamar(PelamarModel pelamar) {
		return pelamarDb.save(pelamar);
	}

	@Override
	public void deletePelamar(PelamarModel pelamar) {
		pelamarDb.delete(pelamar);
	}

	@Override
	public void deleteBanyakPelamar(List<PelamarModel> pelamar) {
		pelamarDb.deleteInBatch(pelamar);
	}

	@Override
	public List<PelamarModel> getAllPelamar() {
		return pelamarDb.findAll();
	}

	@Override
	public PelamarModel getPelamarById(long id) {
		return pelamarDb.findById(id);
	}

	@Override
	public PelamarModel updatePelamar(PelamarModel pelamar) {
		return pelamarDb.save(pelamar);
	}
	
	}

