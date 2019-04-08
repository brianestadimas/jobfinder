package com.apap.HrPayrollSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
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
	public void updatePegawai(long id, PegawaiOutsourcingModel pegawai) {
		
		PegawaiOutsourcingModel updatePegawai = pegawaiOutsourcingDb.findById(id).get();
		updatePegawai.getPelamar_id().setNama_lengkap(pegawai.getPelamar_id().getNama_lengkap());
		updatePegawai.getPelamar_id().setNama_panggilan(pegawai.getPelamar_id().getNama_panggilan());
		updatePegawai.getPelamar_id().setGender(pegawai.getPelamar_id().getGender());
		updatePegawai.getPelamar_id().setTanggal_lahir(pegawai.getPelamar_id().getTanggal_lahir());
		updatePegawai.getPelamar_id().setStatus_marital(pegawai.getPelamar_id().getStatus_marital());
		
		updatePegawai.getPelamar_id().setAlamat(pegawai.getPelamar_id().getAlamat());
		updatePegawai.getPelamar_id().setRegion(pegawai.getPelamar_id().getRegion());
		updatePegawai.getPelamar_id().setNomor_handphone(pegawai.getPelamar_id().getNomor_handphone());
		updatePegawai.getPelamar_id().setNomor_whatsapp(pegawai.getPelamar_id().getNomor_whatsapp());
		
		updatePegawai.getPelamar_id().setNo_ktp(pegawai.getPelamar_id().getNo_ktp());
		
		
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
		

	@Override
	public PegawaiOutsourcingModel getPegawaiByNip(String nip) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < pegawaiOutsourcingDb.findAll().size() ; i++) {
			if(pegawaiOutsourcingDb.findAll().get(i).getNip().equals(nip)) {
				return pegawaiOutsourcingDb.findAll().get(i);
			}
		}
		return null;

	}

}
