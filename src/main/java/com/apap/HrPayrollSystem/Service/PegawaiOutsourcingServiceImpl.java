package com.apap.HrPayrollSystem.Service;

import java.util.List;             
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.PelamarModel;
import com.apap.HrPayrollSystem.Model.ProyekModel;
import com.apap.HrPayrollSystem.Repository.PegawaiOutsourcingDb;
import com.apap.HrPayrollSystem.Repository.PelamarDb;

@Service
@Transactional
public class PegawaiOutsourcingServiceImpl implements PegawaiOutsourcingService {

	@Autowired
	PegawaiOutsourcingDb pegawaiOutsourcingDb;
	
	@Autowired
	PelamarDb pelamarDb;
	
	@Override
	public List<PegawaiOutsourcingModel> getAllPegawai() {
		// TODO Auto-generated method stub
		return pegawaiOutsourcingDb.findAll();
	}

		
	@Override
	public PegawaiOutsourcingModel getPegawaiById(long id) {
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
	public void updatePegawai(long id, PegawaiOutsourcingModel pegawai) {
		
		PegawaiOutsourcingModel updatePegawai = pegawaiOutsourcingDb.findById(id);
	
	
	
//		pelamar_baru.setNama_lengkap(pegawai.getPelamar_id().getNama_lengkap());
//		pelamar_baru.setNama_panggilan(pegawai.getPelamar_id().getNama_panggilan());
//		pelamar_baru.setGender(pegawai.getPelamar_id().getGender());
//		pelamar_baru.setTanggal_lahir(pegawai.getPelamar_id().getTanggal_lahir()); 
//		pelamar_baru.setStatus_marital(pegawai.getPelamar_id().getStatus_marital());
//		
//		pelamar_baru.setAlamat(pegawai.getPelamar_id().getAlamat());
//		pelamar_baru.setRegion(pegawai.getPelamar_id().getRegion());
//		pelamar_baru.setTelepon(pegawai.getPelamar_id().getTelepon());
//		pelamar_baru.setNomor_whatsapp(pegawai.getPelamar_id().getNomor_whatsapp());
//		pelamar_baru.setTelepon_orang_terdekat(pegawai.getPelamar_id().getTelepon());
//		pelamar_baru.setNo_ktp(pegawai.getPelamar_id().getNo_ktp());
//		
//		pelamar_baru.setPendidikan_terakhir(pegawai.getPelamar_id().getPendidikan_terakhir());
//		pelamar_baru.setApply_date(pegawai.getPelamar_id().getApply_date());
//		pelamar_baru.setProduk_dilamar(pegawai.getPelamar_id().getProduk_dilamar());
//		pelamar_baru.setGender(pegawai.getPelamar_id().getGender());
//		pelamar_baru.setRegion(pegawai.getPelamar_id().getRegion());
//		
//		pelamar_baru.setTempat_sekolah(pegawai.getPelamar_id().getTempat_sekolah());
//		pelamar_baru.setNama_sekolah(pegawai.getPelamar_id().getNama_sekolah());
//		pelamar_baru.setJurusan(pegawai.getPelamar_id().getJurusan());
//		
//		pelamar_baru.setStatus_marital(pegawai.getPelamar_id().getStatus_marital());
//		
//		
//		pelamar_baru.setId(pegawai.getPelamar_id().getId());
//		
//		updatePegawai.setPelamar_id(pelamar_baru);
//		
//		pelamarDb.save(pelamar_baru);
//		
		updatePegawai.getPelamar_id().setNama_lengkap(pegawai.getPelamar_id().getNama_lengkap());
		updatePegawai.getPelamar_id().setNama_panggilan(pegawai.getPelamar_id().getNama_panggilan());
		updatePegawai.getPelamar_id().setGender(pegawai.getPelamar_id().getGender());
		updatePegawai.getPelamar_id().setTanggal_lahir(pegawai.getPelamar_id().getTanggal_lahir());
		updatePegawai.getPelamar_id().setStatus_marital(pegawai.getPelamar_id().getStatus_marital());
		
		updatePegawai.getPelamar_id().setAlamat(pegawai.getPelamar_id().getAlamat());
		updatePegawai.getPelamar_id().setRegion(pegawai.getPelamar_id().getRegion());
		updatePegawai.getPelamar_id().setNomor_whatsapp(pegawai.getPelamar_id().getNomor_whatsapp());
		
		updatePegawai.getPelamar_id().setNo_ktp(pegawai.getPelamar_id().getNo_ktp());
		
		updatePegawai.setJabatan(pegawai.getJabatan());
		updatePegawai.setProduk(pegawai.getProduk());
		updatePegawai.setNip(pegawai.getNip());
		updatePegawai.setNo_arsip(pegawai.getNo_arsip());
		
		updatePegawai.setEnd_date(pegawai.getEnd_date());
		updatePegawai.setJoin_date(pegawai.getJoin_date());
		
		updatePegawai.setNama_bank(pegawai.getNama_bank());
		updatePegawai.setNo_rekening(pegawai.getNo_rekening());
	}
	
	@Override
	public void save_all_pegawai_proyek(List<PegawaiOutsourcingModel> listPegawai) {
		// TODO Auto-generated method stub
		
		pegawaiOutsourcingDb.saveAll(listPegawai);
	}



	@Override
	//Ini sebenernya bisa dipake buat update data pegawai outsourcing
	public void updatePegawaiProyek(long id, PegawaiOutsourcingModel pegawaiBaru) {
		PegawaiOutsourcingModel pegawai = pegawaiOutsourcingDb.findById(id);
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
	public Boolean expiredDate(PegawaiOutsourcingModel pegawai) {
		Boolean expiredStatus = true;
		
		
		return null;
	}
	

}
