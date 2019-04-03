package com.apap.HrPayrollSystem.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.HrPayrollSystem.Model.KehadiranModel;
import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Service.KehadiranService;
import com.apap.HrPayrollSystem.Service.PegawaiOutsourcingService;

@Controller
public class KehadiranController {
	@Autowired
	private KehadiranService kehadiran_service;
	@Autowired
	private PegawaiOutsourcingService pegawai_outsourcing_service;

	
	@RequestMapping(value="/proyek/{proyek_id}/kehadiran",method=RequestMethod.GET)
	private String daftarKehadiranProyek(@PathVariable(value="proyek_id") long proyek_id, Model model) {
		
		List<KehadiranModel> get_all_kehadiran = kehadiran_service.get_all_kehadiran();
		List<String> kehadiran_proyek_ini = new ArrayList<String>();
		
		for(int i = 0 ; i < get_all_kehadiran.size() ; i++) {
			if(get_all_kehadiran.get(i).getProyek().getId() == proyek_id) {
				if(kehadiran_proyek_ini.isEmpty()) {
					kehadiran_proyek_ini.add(get_all_kehadiran.get(i).getJudul_kehadiran());
				}
				else {
					String judul_kehadiran = get_all_kehadiran.get(i).getJudul_kehadiran();
					if(!(kehadiran_proyek_ini.contains(judul_kehadiran))) {
						kehadiran_proyek_ini.add(get_all_kehadiran.get(i).getJudul_kehadiran());
					}
				}
			}
		}
		//TO DO render
		model.addAttribute("id_proyek", proyek_id);
		model.addAttribute("list_of_kehadiran",kehadiran_proyek_ini);
		return "list_kehadiran";
	}
	

	//Lihat Detail Kehadiran
	@RequestMapping(value="/proyek/{proyek_id}/kehadiran/{judul_kehadiran}", method=RequestMethod.GET)
	private String detailKehadiranProyek(@PathVariable(value="proyek_id") long proyek_id,
										@PathVariable(value="judul_kehadiran") String judul_kehadiran,
										Model model) {
		
		List<KehadiranModel> get_all_kehadiran = kehadiran_service.get_all_kehadiran();
		List<KehadiranModel> detail_kehadiran_proyek_ini = new ArrayList<KehadiranModel>();
		List<String> nip_pegawai_proyek = new ArrayList<String>();
		List<String> nama_pegawai_proyek = new ArrayList<String>();
		for(int i = 0 ; i < get_all_kehadiran.size() ; i++) {
			if(get_all_kehadiran.get(i).getProyek().getId() == proyek_id && get_all_kehadiran.get(i).getJudul_kehadiran().equals(judul_kehadiran)) {
					detail_kehadiran_proyek_ini.add(get_all_kehadiran.get(i));
			}
		}
		for(int i = 0 ; i < detail_kehadiran_proyek_ini.size() ; i++) {
			nip_pegawai_proyek.add(detail_kehadiran_proyek_ini.get(i).getPegawai_outsourcing().getNip());
			nama_pegawai_proyek.add(detail_kehadiran_proyek_ini.get(i).getPegawai_outsourcing().getPelamar_id().getNama_lengkap());
		}
		
		model.addAttribute("judul_kehadiran", judul_kehadiran);
		model.addAttribute("jumlah_hari_kerja", detail_kehadiran_proyek_ini.get(0).getJumlah_hari_kerja());
		model.addAttribute("nip_pegawai",nip_pegawai_proyek);
		model.addAttribute("nama_pegawai", nama_pegawai_proyek);
		model.addAttribute("detail_detail_kehadiran", detail_kehadiran_proyek_ini);
		return "detail_kehadiran";
	}
	//ADD KEHADIRAN
	//iterasi semua pegawai yg ada di proyek itu NIP dan Nama Pegawainya
	//sebelahnya form kosong hadir, sakit, izin, alfa, libur, cuti, lain-lain
	@RequestMapping(value="/proyek/{proyek_id}/kehadiran/tambah", method=RequestMethod.GET)
	private String addKehadiranProyek(@PathVariable(value="proyek_id") long proyek_id, Model model) {
		List<PegawaiOutsourcingModel> pegawai_outsourcing = pegawai_outsourcing_service.getAllPegawai();
		List<String> nip_pegawai_pada_proyek_ini = new ArrayList<String>();
		List<String> nama_pegawai_pada_proyek_ini = new ArrayList<String>();
		for(int i = 0 ; i < pegawai_outsourcing.size() ; i++) {
			if(pegawai_outsourcing.get(i).getProyek().getId() == proyek_id) {
				nip_pegawai_pada_proyek_ini.add(pegawai_outsourcing.get(i).getNip());
				nama_pegawai_pada_proyek_ini.add(pegawai_outsourcing.get(i).getPelamar_id().getNama_lengkap());
			}	
		}
		model.addAttribute("nama_pegawai", nama_pegawai_pada_proyek_ini);
		model.addAttribute("nip_pegawai", nip_pegawai_pada_proyek_ini);	
		return "form_kehadiran";
	}
	//Update Kehadiran
	
	//Hapus Kehadiran
	@RequestMapping(value="/proyek/{proyek_id}/kehadiran/hapus/{judul_kehadiran}", method=RequestMethod.GET)
	private String hapusKehadiranProyek(@PathVariable(value="proyek_id") long proyek_id,
										 @PathVariable(value="judul_kehadiran") String judul_kehadiran,
										 Model model) {
	List<KehadiranModel> get_all_kehadiran = kehadiran_service.get_all_kehadiran();
	List<KehadiranModel> kehadiran_yang_dihapus = new ArrayList<KehadiranModel>();	
	for(int i = 0 ; i < get_all_kehadiran.size() ; i++) {
		if(get_all_kehadiran.get(i).getProyek().getId() == proyek_id && get_all_kehadiran.get(i).getJudul_kehadiran().equals(judul_kehadiran)) {
			kehadiran_yang_dihapus.add(get_all_kehadiran.get(i));
		}
	}	
	for(int i = 0 ; i < kehadiran_yang_dihapus.size() ; i++) {
		kehadiran_service.delete_kehadiran(kehadiran_yang_dihapus.get(i));
	}
	
	return "list_kehadiran";
	}
	
	
}
