package com.apap.HrPayrollSystem.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.HrPayrollSystem.Model.KehadiranModel;
import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.ProyekModel;
import com.apap.HrPayrollSystem.Repository.KehadiranDb;

@Service
@Transactional
public class KehadiranServiceImpl implements KehadiranService {

	@Autowired
	private KehadiranDb kehadiran_Db;

	@Override
	public List<KehadiranModel> get_all_kehadiran() {
		// TODO Auto-generated method stub
		return kehadiran_Db.findAll();
	}

	@Override
	public void delete_kehadiran(KehadiranModel kehadiran) {
		// TODO Auto-generated method stub
		kehadiran_Db.delete(kehadiran);
	}

	@Override
	public void save_all_kehadiran(List<KehadiranModel> kehadiran_kehadiran) {
		// TODO Auto-generated method stub

		kehadiran_Db.saveAll(kehadiran_kehadiran);
	}

	@Override
	public List<KehadiranModel> get_all_kehadiran_by_pegawai(PegawaiOutsourcingModel pegawai) {
		List<KehadiranModel> kehadiran_pelamar = new ArrayList<KehadiranModel>();
		for (int i = kehadiran_Db.findAll().size() - 1; i >= 0; i--) {
			KehadiranModel kehadiran = kehadiran_Db.findAll().get(i);
			if (kehadiran_pelamar.size() == 4) {
				break;
			} else if (kehadiran.getPegawai_outsourcing().equals(pegawai)) {
				kehadiran_pelamar.add(kehadiran);
			}
		}
		return kehadiran_pelamar;
	}

	@Override
	public List<KehadiranModel> get_all_kehadiran_by_proyek(ProyekModel proyek) {
		List<KehadiranModel> all_proyek = new ArrayList<KehadiranModel>();
		List<KehadiranModel> kehadiran_proyek = new ArrayList<KehadiranModel>();
		for (int i = kehadiran_Db.findAll().size() - 1; i >= 0; i--) {
			KehadiranModel kehadiran = kehadiran_Db.findAll().get(i);
			if (kehadiran.getProyek().equals(proyek)) {
				all_proyek.add(kehadiran);
			}
		}
		KehadiranModel kehadiran_sementara= new KehadiranModel();
		for (int j = 0; j > all_proyek.size() ; j++) {
			KehadiranModel k1 = all_proyek.get(j);
			for (int k = 1; k > all_proyek.size(); k++) {
				KehadiranModel k2 = all_proyek.get(k);
				if(k1.getJudul_kehadiran().equals(k2.getJudul_kehadiran())) {
					kehadiran_sementara.setJudul_kehadiran(k1.getJudul_kehadiran());
					kehadiran_sementara.setJumlah_absen(k1.getJumlah_absen()+k2.getJumlah_absen());
					kehadiran_sementara.setJumlah_cuti(k1.getJumlah_cuti()+k2.getJumlah_cuti());
					kehadiran_sementara.setJumlah_hari_kerja(k1.getJumlah_hari_kerja());
					kehadiran_sementara.setJumlah_izin(k1.getJumlah_izin()+k2.getJumlah_izin());
					kehadiran_sementara.setJumlah_kehadiran(k1.getJumlah_kehadiran()+k2.getJumlah_kehadiran());
					kehadiran_sementara.setJumlah_off(k1.getJumlah_off()+k2.getJumlah_off());
				}
			}
		}
		return all_proyek;
	}
}
