package com.apap.HrPayrollSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.PelamarModel;
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
	public void updatePegawai(long id, PegawaiOutsourcingModel pegawai) {
		
		PegawaiOutsourcingModel updatePegawai = pegawaiOutsourcingDb.findById(id).get();
		PelamarModel pelamar_baru = new PelamarModel();
		
		pelamar_baru.setNama_lengkap(pegawai.getPelamar_id().getNama_lengkap());
		pelamar_baru.setNama_panggilan(pegawai.getPelamar_id().getNama_panggilan());
		pelamar_baru.setGender(pegawai.getPelamar_id().getGender());
		pelamar_baru.setTanggal_lahir(pegawai.getPelamar_id().getTanggal_lahir());
		pelamar_baru.setStatus_marital(pegawai.getPelamar_id().getStatus_marital());
		
		pelamar_baru.setAlamat(pegawai.getPelamar_id().getAlamat());
		pelamar_baru.setRegion(pegawai.getPelamar_id().getRegion());
		pelamar_baru.setTelepon(pegawai.getPelamar_id().getTelepon());
		pelamar_baru.setNomor_whatsapp(pegawai.getPelamar_id().getNomor_whatsapp());
		pelamar_baru.setTelepon_orang_terdekat(pegawai.getPelamar_id().getTelepon());
		pelamar_baru.setNo_ktp(pegawai.getPelamar_id().getNo_ktp());
		
		pelamar_baru.setPendidikan_terakhir(pegawai.getPelamar_id().getPendidikan_terakhir());
		pelamar_baru.setApply_date(pegawai.getPelamar_id().getApply_date());
		pelamar_baru.setProduk_dilamar(pegawai.getPelamar_id().getProduk_dilamar());
		pelamar_baru.setGender(pegawai.getPelamar_id().getGender());
		pelamar_baru.setRegion(pegawai.getPelamar_id().getRegion());
		
		pelamar_baru.setTempat_sekolah(pegawai.getPelamar_id().getTempat_sekolah());
		pelamar_baru.setNama_sekolah(pegawai.getPelamar_id().getNama_sekolah());
		pelamar_baru.setJurusan(pegawai.getPelamar_id().getJurusan());
		
		pelamar_baru.setStatus_marital(pegawai.getPelamar_id().getStatus_marital());
		
		
		pelamar_baru.setId(pegawai.getPelamar_id().getId());
		
		updatePegawai.setPelamar_id(pelamar_baru);
		
		pelamarDb.save(pelamar_baru);
		
//		updatePegawai.getPelamar_id().setNama_lengkap(pegawai.getPelamar_id().getNama_lengkap());
//		updatePegawai.getPelamar_id().setNama_panggilan(pegawai.getPelamar_id().getNama_panggilan());
//		updatePegawai.getPelamar_id().setGender(pegawai.getPelamar_id().getGender());
//		updatePegawai.getPelamar_id().setTanggal_lahir(pegawai.getPelamar_id().getTanggal_lahir());
//		updatePegawai.getPelamar_id().setStatus_marital(pegawai.getPelamar_id().getStatus_marital());
		
//		updatePegawai.getPelamar_id().setAlamat(pegawai.getPelamar_id().getAlamat());
//		updatePegawai.getPelamar_id().setRegion(pegawai.getPelamar_id().getRegion());
//		updatePegawai.getPelamar_id().setTelepon(pegawai.getPelamar_id().getTelepon());
//		updatePegawai.getPelamar_id().setNomor_whatsapp(pegawai.getPelamar_id().getNomor_whatsapp());
//		
//		updatePegawai.getPelamar_id().setNo_ktp(pegawai.getPelamar_id().getNo_ktp());
		
		
		updatePegawai.setNip(pegawai.getNip());
		updatePegawai.setNo_arsip(pegawai.getNo_arsip());
		
		updatePegawai.setEnd_date(pegawai.getEnd_date());
		updatePegawai.setJoin_date(pegawai.getJoin_date());
		
		updatePegawai.setNama_bank(pegawai.getNama_bank());
		updatePegawai.setNo_rekening(pegawai.getNo_rekening());
		
		updatePegawai.setBpjstk(pegawai.getBpjstk());
		updatePegawai.setBpjsk(pegawai.getBpjsk());
		updatePegawai.setNpwp(pegawai.getNpwp());
		updatePegawai.setPkwt(pegawai.getPkwt());
		
		updatePegawai.setJabatan(pegawai.getJabatan());
		
		
		
	}

}
