package com.apap.HrPayrollSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.ProyekModel;
import com.apap.HrPayrollSystem.Repository.PegawaiOutsourcingDb;

@Service
@Transactional
public class PegawaiOutsourcingServiceImpl implements PegawaiOutsourcingService {

	@Autowired
	PegawaiOutsourcingDb pegawaiOutsourcingDb;
	
	@Override
	public List<PegawaiOutsourcingModel> getAllPegawai() {
		// TODO Auto-generated method stub
		
		return pegawaiOutsourcingDb.findAll();
	}

		
	@Override
	public Optional<PegawaiOutsourcingModel> getPegawaiById(long id) {
		
		return pegawaiOutsourcingDb.findById(id);
	}

	@Override
	public void deletePegawaiById(long id) {
		pegawaiOutsourcingDb.deleteById(id);
	}
	

	@Override
	public void updatePegawaiStatusById(long id) {
		// TODO Auto-generated method stub
		PegawaiOutsourcingModel obj = pegawaiOutsourcingDb.getOne(id);
		obj.setStatus(false);
		
		pegawaiOutsourcingDb.save(obj);
		pegawaiOutsourcingDb.flush();
		
	


		
	}
	
	@Override
	public void save_all_pegawai_proyek(List<PegawaiOutsourcingModel> listPegawai) {
		// TODO Auto-generated method stub
		
		pegawaiOutsourcingDb.saveAll(listPegawai);
	}



	@Override
	//Ini sebenernya bisa dipake buat update data pegawai outsourcing
	public void updatePegawaiProyek(long id, PegawaiOutsourcingModel pegawaiBaru) {
		PegawaiOutsourcingModel pegawai = pegawaiOutsourcingDb.findById(id).get();
		pegawai.setNip(pegawaiBaru.getNip());
		pegawai.setPelamar_id(pegawaiBaru.getPelamar_id());
		pegawai.setJoin_date(pegawaiBaru.getJoin_date());
		pegawai.setEnd_date(pegawaiBaru.getEnd_date());
		pegawai.setGaji_pokok(pegawaiBaru.getGaji_pokok());
		pegawai.setTunjangan_tetap(pegawaiBaru.getTunjangan_tetap());
		pegawai.setTunjangan_tidak_tetap(pegawaiBaru.getTunjangan_tidak_tetap());
		pegawai.setBpjsk(pegawaiBaru.getBpjsk());
		pegawai.setBpjstk(pegawaiBaru.getBpjstk());
		pegawai.setJaminan(pegawaiBaru.getJaminan());
		pegawai.setNpwp(pegawaiBaru.getNpwp());
		pegawai.setPkwt(pegawaiBaru.getPkwt());
		pegawai.setNo_arsip(pegawai.getNo_arsip());
		pegawai.setNo_rekening(pegawaiBaru.getNo_rekening());
		pegawai.setNama_bank(pegawaiBaru.getNama_bank());
		pegawai.setStatus(pegawaiBaru.getStatus());
		pegawai.setJabatan(pegawai.getJabatan());
		pegawai.setProduk(pegawaiBaru.getProduk());
		pegawai.setProyek(pegawaiBaru.getProyek());
		
		
	}


	@Override
	public void addPegawai(PegawaiOutsourcingModel pegawai) {
		// TODO Auto-generated method stub
		pegawaiOutsourcingDb.save(pegawai);
	}


	@Override
	public void updatePegawai(PegawaiOutsourcingModel pegawai) {
		// TODO Auto-generated method stub
		pegawaiOutsourcingDb.save(pegawai);
	}


	@Override
	public void assignAll(List<PegawaiOutsourcingModel> list_pegawai) {
		// TODO Auto-generated method stub
		pegawaiOutsourcingDb.saveAll(list_pegawai);
	}


	@Override
	public PegawaiOutsourcingModel getPegawaiByNip(String nip) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updatePegawai(long id, PegawaiOutsourcingModel pegawai) {
		// TODO Auto-generated method stub
		
	}

}
