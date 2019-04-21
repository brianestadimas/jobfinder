package com.apap.HrPayrollSystem.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apap.HrPayrollSystem.Model.KehadiranModel;
import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.ProyekModel;
import com.apap.HrPayrollSystem.Service.KehadiranService;
import com.apap.HrPayrollSystem.Service.PegawaiOutsourcingService;
import com.apap.HrPayrollSystem.Service.ProyekService;
import com.apap.HrPayrollSystem.Utility.KehadiranWrapper;

@Controller
public class KehadiranController {
	@Autowired
	private KehadiranService kehadiran_service;
	@Autowired
	private PegawaiOutsourcingService pegawai_outsourcing_service;
	@Autowired
	private ProyekService proyek_service;
	
	
	//List All Kehadiran
	@RequestMapping(value="/proyek/{proyek_id}/kehadiran",method=RequestMethod.GET)
	private String daftarKehadiranProyek(@PathVariable(value="proyek_id") long proyek_id, Model model) {
		
		List<KehadiranModel> get_all_kehadiran = kehadiran_service.get_all_kehadiran();
		List<String> kehadiran_proyek_ini = new ArrayList<String>();
		String nama_proyek = proyek_service.getProyekById(proyek_id).get().getNama_proyek();
		
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
		model.addAttribute("nama_proyek_ini", nama_proyek);
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
		KehadiranWrapper daftar_kehadiran = new KehadiranWrapper();
		List<PegawaiOutsourcingModel> pegawai_outsourcing = pegawai_outsourcing_service.getAllPegawai();
		List<String> nip_pegawai_pada_proyek_ini = new ArrayList<String>();
		List<String> nama_pegawai_pada_proyek_ini = new ArrayList<String>();
		for(int i = 0 ; i < pegawai_outsourcing.size() ; i++) {
			if(pegawai_outsourcing.get(i).getProyek().getId() == proyek_id && pegawai_outsourcing.get(i).getStatus() == true) {
				nip_pegawai_pada_proyek_ini.add(pegawai_outsourcing.get(i).getNip());
				nama_pegawai_pada_proyek_ini.add(pegawai_outsourcing.get(i).getPelamar_id().getNama_lengkap());
			}	
		}
		for(int i = 0 ; i < nama_pegawai_pada_proyek_ini.size() ; i ++) {
			KehadiranModel kehadiran = new KehadiranModel();
			if(pegawai_outsourcing.get(i).getProyek().getId() == proyek_id && pegawai_outsourcing.get(i).getStatus() == true ) {
			kehadiran.setPegawai_outsourcing(pegawai_outsourcing.get(i));
			daftar_kehadiran.add_kehadiran(kehadiran);
			}
		}

		model.addAttribute("daftar_kehadiran", daftar_kehadiran);
		model.addAttribute("nama_pegawai", nama_pegawai_pada_proyek_ini);
		model.addAttribute("nip_pegawai", nip_pegawai_pada_proyek_ini);	
		return "form_kehadiran";
	}
	
	
	@RequestMapping(value="/proyek/{proyek_id}/kehadiran/tambah/submit", method=RequestMethod.POST)
	private String addKehadiranProyekSubmit(@PathVariable(value="proyek_id") long proyek_id,
											@ModelAttribute KehadiranWrapper daftar_daftar_kehadiran, 
											HttpServletRequest req,
											Model model) {
		
		int jumlah_hari_kerja = Integer.parseInt(req.getParameter("jumlah_hari_kerja"));
		String judul_kehadiran = String.valueOf(req.getParameter("judul_absensi"));
		ProyekModel proyek = new ProyekModel();
		for(int i = 0 ; i < proyek_service.getAllProyek().size() ; i++) {
			if(proyek_service.getAllProyek().get(i).getId() == proyek_id) {
				proyek = proyek_service.getAllProyek().get(i);
			}
		}
		for(int i = 0 ; i < daftar_daftar_kehadiran.getDaftar_kehadiran().size() ; i++) {
			daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).setProyek(proyek);
			daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).setJudul_kehadiran(judul_kehadiran);
			daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).setJumlah_hari_kerja(jumlah_hari_kerja);
			if(jumlah_hari_kerja != daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_absen()+
									daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_cuti()+
									daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_izin()+
									daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_kehadiran()+
									daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_off()+
									daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_sakit()) {
				System.out.println("hai tayo :))");
				String msg = "Terdapat kehadiran dengan jumlah komponen kehadiran tidak sama dengan jumlah hari kerja";
				model.addAttribute("fail_notif", msg);
				return "redirect:/proyek/{proyek_id}/kehadiran/tambah";

			}
		}
		kehadiran_service.save_all_kehadiran(daftar_daftar_kehadiran.getDaftar_kehadiran());

		List<KehadiranModel> get_all_kehadiran = kehadiran_service.get_all_kehadiran();
		List<String> kehadiran_proyek_ini = new ArrayList<String>();
		String nama_proyek = proyek_service.getProyekById(proyek_id).get().getNama_proyek();
		
		for(int i = 0 ; i < get_all_kehadiran.size() ; i++) {
			if(get_all_kehadiran.get(i).getProyek().getId() == proyek_id) {
				if(kehadiran_proyek_ini.isEmpty()) {
					kehadiran_proyek_ini.add(get_all_kehadiran.get(i).getJudul_kehadiran());
				}
				else {
					String judul_kehadiran_kehadiran = get_all_kehadiran.get(i).getJudul_kehadiran();
					if(!(kehadiran_proyek_ini.contains(judul_kehadiran_kehadiran))) {
						kehadiran_proyek_ini.add(get_all_kehadiran.get(i).getJudul_kehadiran());
					}
				}
			}
		}
		//TO DO render
		model.addAttribute("nama_proyek_ini", nama_proyek);
		model.addAttribute("id_proyek", proyek_id);
		model.addAttribute("list_of_kehadiran",kehadiran_proyek_ini);		
		model.addAttribute("notifikasi_sukses","Berhasil Menambahkan Kehadiran dengan judul "+judul_kehadiran);	
		return "list_kehadiran";
	}
	
	//Update Kehadiran
	@RequestMapping(value="/proyek/{proyek_id}/kehadiran/update/{judul_kehadiran}", method=RequestMethod.GET)
	private String updateKehadiranProyek(@PathVariable(value="proyek_id") long proyek_id,
										 @PathVariable(value="judul_kehadiran") String judul_kehadiran,
										 Model model) {
		KehadiranWrapper daftar_kehadiran = new KehadiranWrapper();
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
			daftar_kehadiran.add_kehadiran(detail_kehadiran_proyek_ini.get(i));
		}
		for(int i = 0 ; i < detail_kehadiran_proyek_ini.size() ; i++) {
			nip_pegawai_proyek.add(detail_kehadiran_proyek_ini.get(i).getPegawai_outsourcing().getNip());
			nama_pegawai_proyek.add(detail_kehadiran_proyek_ini.get(i).getPegawai_outsourcing().getPelamar_id().getNama_lengkap());
		}
		int hari_kerja = detail_kehadiran_proyek_ini.get(0).getJumlah_hari_kerja();
		model.addAttribute("proyek_id", proyek_id);
		model.addAttribute("judul_kehadiran", judul_kehadiran);
		model.addAttribute("jumlah_hari_kerja", hari_kerja);
		model.addAttribute("daftar_kehadiran", daftar_kehadiran);
		model.addAttribute("nama_pegawai", nama_pegawai_proyek);
		model.addAttribute("nip_pegawai", nip_pegawai_proyek);			
		return "form_update_kehadiran";
	}
	
	@RequestMapping(value="/proyek/{proyek_id}/kehadiran/update/submit", method=RequestMethod.POST)
	private String updateKehadiranProyekSubmit(@PathVariable(value="proyek_id") long proyek_id,
											@ModelAttribute KehadiranWrapper daftar_daftar_kehadiran, 
											HttpServletRequest req,
											Model model) {
								
			int jumlah_hari_kerja = Integer.parseInt(req.getParameter("jumlah_hari_kerja"));
			String judul_kehadiran = String.valueOf(req.getParameter("judul_absensi"));
			ProyekModel proyek = new ProyekModel();
			for(int i = 0 ; i < proyek_service.getAllProyek().size() ; i++) {
				if(proyek_service.getAllProyek().get(i).getId() == proyek_id) {
					proyek = proyek_service.getAllProyek().get(i);
					}
				}
			for(int i = 0 ; i < daftar_daftar_kehadiran.getDaftar_kehadiran().size() ; i++) {
				daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).setProyek(proyek);
				daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).setJudul_kehadiran(judul_kehadiran);
				daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).setJumlah_hari_kerja(jumlah_hari_kerja);
				if(jumlah_hari_kerja != daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_absen()+
										daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_cuti()+
										daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_izin()+
										daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_kehadiran()+
										daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_off()+
										daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_sakit()) {
					System.out.println("hai tayo :))");
					model.addAttribute("fail_notif", "Terdapat kehadiran dengan jumlah komponen kehadiran tidak sama dengan jumlah hari kerja");
					return "redirect:/proyek/{proyek_id}/kehadiran/update/"+judul_kehadiran;
				}
			}
			kehadiran_service.save_all_kehadiran(daftar_daftar_kehadiran.getDaftar_kehadiran());				
			List<KehadiranModel> get_all_kehadiran = kehadiran_service.get_all_kehadiran();
			List<String> kehadiran_proyek_ini = new ArrayList<String>();
			String nama_proyek = proyek_service.getProyekById(proyek_id).get().getNama_proyek();
								
			for(int i = 0 ; i < get_all_kehadiran.size() ; i++) {
				if(get_all_kehadiran.get(i).getProyek().getId() == proyek_id) {
					if(kehadiran_proyek_ini.isEmpty()) {
						kehadiran_proyek_ini.add(get_all_kehadiran.get(i).getJudul_kehadiran());
					}
				else {
					String judul_kehadiran_kehadiran = get_all_kehadiran.get(i).getJudul_kehadiran();
					if(!(kehadiran_proyek_ini.contains(judul_kehadiran_kehadiran))) {
						kehadiran_proyek_ini.add(get_all_kehadiran.get(i).getJudul_kehadiran());
							}
						}
					}
				}
								//TO DO render
				model.addAttribute("nama_proyek_ini", nama_proyek);
				model.addAttribute("id_proyek", proyek_id);
				model.addAttribute("list_of_kehadiran",kehadiran_proyek_ini);
				model.addAttribute("notifikasi_sukses","Berhasil Mengubah Kehadiran dengan judul "+judul_kehadiran);
				return "list_kehadiran";
			}
	
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
	get_all_kehadiran = kehadiran_service.get_all_kehadiran();
	List<String> kehadiran_proyek_ini = new ArrayList<String>();
	String nama_proyek = proyek_service.getProyekById(proyek_id).get().getNama_proyek();
	
	for(int i = 0 ; i < get_all_kehadiran.size() ; i++) {
		if(get_all_kehadiran.get(i).getProyek().getId() == proyek_id) {
			if(kehadiran_proyek_ini.isEmpty()) {
				kehadiran_proyek_ini.add(get_all_kehadiran.get(i).getJudul_kehadiran());
			}
			else {
				String judul_kehadiran_kehadiran = get_all_kehadiran.get(i).getJudul_kehadiran();
				if(!(kehadiran_proyek_ini.contains(judul_kehadiran_kehadiran))) {
					kehadiran_proyek_ini.add(get_all_kehadiran.get(i).getJudul_kehadiran());
				}
			}
		}
	}
	//TO DO render
	model.addAttribute("nama_proyek_ini", nama_proyek);
	model.addAttribute("id_proyek", proyek_id);
	model.addAttribute("list_of_kehadiran",kehadiran_proyek_ini);
	model.addAttribute("notifikasi_sukses","Berhasil Menghapus Kehadiran dengan judul "+judul_kehadiran);
	return "list_kehadiran";
	}
	
	
}
