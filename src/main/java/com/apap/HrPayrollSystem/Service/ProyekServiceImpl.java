package com.apap.HrPayrollSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.HrPayrollSystem.Model.ProyekModel;
import com.apap.HrPayrollSystem.Repository.ProyekDb;

@Service
@Transactional
public class ProyekServiceImpl implements ProyekService {

	@Autowired
	ProyekDb proyekDb;

	@Override
	public Optional<ProyekModel> getProyekById(Long id) {
		// TODO Auto-generated method stub
		return proyekDb.findById(id);
	}

	@Override
	public List<ProyekModel> getAllProyek() {
		// TODO Auto-generated method stub
		return proyekDb.findAll();
	}

	@Override
	public void addProyek(ProyekModel proyekBaru) {
		// TODO Auto-generated method stub
		proyekDb.save(proyekBaru);
	}

	@Override
	public void updateProyek(Long id, ProyekModel proyekBaru) {
		// TODO Auto-generated method stub
		ProyekModel proyek = proyekDb.findById(id).get();
		proyek.setNama_proyek(proyekBaru.getNama_proyek());
		proyek.setNomor_kontrak(proyekBaru.getNomor_kontrak());
		proyek.setRegion(proyekBaru.getRegion());
		proyek.setNama_cp(proyekBaru.getNama_cp());
		proyek.setNo_telp_cp(proyekBaru.getNo_telp_cp());
		proyek.setNo_rekening(proyekBaru.getNo_rekening());
		proyek.setStart_date_kontrak(proyekBaru.getStart_date_kontrak());
		proyek.setEnd_date_kontrak(proyekBaru.getEnd_date_kontrak());
		proyek.setJenis_proyek(proyekBaru.getJenis_proyek());
		proyek.setNPWP_klien(proyekBaru.getNPWP_klien());
		proyek.setNilai_kontrak(proyekBaru.getNilai_kontrak());
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		proyekDb.deleteById(id);	
	}
}
