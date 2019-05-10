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
import com.apap.HrPayrollSystem.Utility.PerformaWrapper;

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
		List<KehadiranModel> kehadiran_pegawai = new ArrayList<KehadiranModel>();
		for (int i = kehadiran_Db.findAll().size() - 1; i >= 0; i--) {
			KehadiranModel kehadiran = kehadiran_Db.findAll().get(i);
			if (kehadiran_pegawai.size() == 4) {
				break;
			} else if (kehadiran.getPegawai_outsourcing().equals(pegawai)) {
				kehadiran_pegawai.add(kehadiran);
			}
		}
		return kehadiran_pegawai;
	}

	@Override
	public List<PerformaWrapper> get_all_kehadiran_by_proyek(ProyekModel proyek) {
		List<KehadiranModel> all_proyek = new ArrayList<KehadiranModel>();
		List<PerformaWrapper> listPerforma = new ArrayList<PerformaWrapper>();
		for (int i = kehadiran_Db.findAll().size() - 1; i >= 0; i--) {
			KehadiranModel kehadiran = kehadiran_Db.findAll().get(i);
			if (kehadiran.getProyek().equals(proyek)) {
				all_proyek.add(kehadiran);
			}
		}
		System.out.println("Jumlah Semua Kehadiran Dalam Proyek:" + all_proyek.size());
//		KehadiranModel kehadiran_sementara= new KehadiranModel();
//		for (int j = 0; j > all_proyek.size() ; j++) {
//			KehadiranModel k1 = all_proyek.get(j);
//			for (int k = 1; k > all_proyek.size(); k++) {
//				KehadiranModel k2 = all_proyek.get(k);
//				if(k1.getJudul_kehadiran().equals(k2.getJudul_kehadiran())) {
//					
//				}
//			}
//		}
		PerformaWrapper performaSementara = new PerformaWrapper();
		for (KehadiranModel hadirProyek : all_proyek) {
			if (performaSementara.getKehadiranList().isEmpty()) {
				performaSementara.getKehadiranList().add(hadirProyek);
				performaSementara.setJudulKehadiran(hadirProyek.getJudul_kehadiran());
				listPerforma.add(performaSementara);
			} else if (performaSementara.getJudulKehadiran().equals(hadirProyek.getJudul_kehadiran())) {
				performaSementara.getKehadiranList().add(hadirProyek);

			} else {
				performaSementara = new PerformaWrapper();
				performaSementara.getKehadiranList().add(hadirProyek);
				performaSementara.setJudulKehadiran(hadirProyek.getJudul_kehadiran());
				listPerforma.add(performaSementara);
			}

		}
		for (PerformaWrapper performaFinal : listPerforma) {
			performaFinal.hitungTotal();
		}
		return listPerforma;
	}
}
