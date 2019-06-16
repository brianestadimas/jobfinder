package com.apap.HrPayrollSystem.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apap.HrPayrollSystem.Model.AccountModel;
import com.apap.HrPayrollSystem.Model.GajiModel;
import com.apap.HrPayrollSystem.Model.KehadiranModel;
import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.ProyekModel;
import com.apap.HrPayrollSystem.Service.AccountService;
import com.apap.HrPayrollSystem.Model.VariableGajiModel;
import com.apap.HrPayrollSystem.Service.GajiService;
import com.apap.HrPayrollSystem.Service.KehadiranService;
import com.apap.HrPayrollSystem.Service.PegawaiOutsourcingService;
import com.apap.HrPayrollSystem.Service.ProyekService;
import com.apap.HrPayrollSystem.Service.VariableGajiService;
import com.apap.HrPayrollSystem.Utility.KehadiranWrapper;
import com.apap.HrPayrollSystem.Utility.Penggajian;
import com.apap.HrPayrollSystem.Utility.PenggajianWrapper;

@Controller
public class KehadiranController {
	@Autowired
	private KehadiranService kehadiran_service;
	@Autowired
	private PegawaiOutsourcingService pegawai_outsourcing_service;
	@Autowired
	private ProyekService proyek_service;
	@Autowired
	private GajiService gaji_service;
	@Autowired
	private VariableGajiService variable_service;
	@Autowired
	private AccountService akun_service;

	
	//List All Kehadiran
	@RequestMapping(value="/proyek/{proyek_id}/kehadiran",method=RequestMethod.GET)
	private String daftarKehadiranProyek(@PathVariable(value="proyek_id") long proyek_id, Model model ,HttpServletRequest req ) {	
		gaji_service.delete_all(gaji_service.get_all());
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
		String jenis_proyek = proyek_service.getProyekById(proyek_id).get().getJenis_proyek();
		AccountModel user = akun_service.findByUsername(req.getRemoteUser());
		model.addAttribute("user", user);

		//TO DO render
		model.addAttribute("jenis_proyek", jenis_proyek);
		model.addAttribute("nama_proyek_ini", nama_proyek);
		model.addAttribute("id_proyek", proyek_id);
		Collections.reverse(kehadiran_proyek_ini);
		model.addAttribute("list_of_kehadiran",kehadiran_proyek_ini);
		return "list_kehadiran";
	}
	

	//Lihat Detail Kehadiran
	@RequestMapping(value="/proyek/{proyek_id}/kehadiran/{judul_kehadiran}", method=RequestMethod.GET)
	private String detailKehadiranProyek(@PathVariable(value="proyek_id") long proyek_id,
										 @PathVariable(value="judul_kehadiran") String judul_kehadiran,
										 Model model,HttpServletRequest req ) {
		
		List<KehadiranModel> get_all_kehadiran = kehadiran_service.get_all_kehadiran();
		List<KehadiranModel> detail_kehadiran_proyek_ini = new ArrayList<KehadiranModel>();
		List<String> nip_pegawai_proyek = new ArrayList<String>();
		List<String> nama_pegawai_proyek = new ArrayList<String>();
		for(int i = 0 ; i < get_all_kehadiran.size() ; i++) {
			if(get_all_kehadiran.get(i).getProyek().getId() == proyek_id && get_all_kehadiran.get(i).getJudul_kehadiran().equals(judul_kehadiran)) {
				System.out.println(get_all_kehadiran.get(i).getProyek().getId());
				System.out.println(proyek_id);
				System.out.println(get_all_kehadiran.get(i).getPegawai_outsourcing().getPelamar_id().getNama_lengkap());
				detail_kehadiran_proyek_ini.add(get_all_kehadiran.get(i));
			}
		}
		for(int i = 0 ; i < detail_kehadiran_proyek_ini.size() ; i++) {
			nip_pegawai_proyek.add(detail_kehadiran_proyek_ini.get(i).getPegawai_outsourcing().getNip());
			nama_pegawai_proyek.add(detail_kehadiran_proyek_ini.get(i).getPegawai_outsourcing().getPelamar_id().getNama_lengkap());
		}
		AccountModel user = akun_service.findByUsername(req.getRemoteUser());
		model.addAttribute("user", user);
		model.addAttribute("proyek_id", proyek_id);
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
		List<PegawaiOutsourcingModel> pegawai_pegawai_proyek_ini = new ArrayList<PegawaiOutsourcingModel>();
		for(int i = 0 ; i < pegawai_outsourcing.size() ; i++) {			
			if(pegawai_outsourcing.get(i).getProyek()!=null && pegawai_outsourcing.get(i).getProyek().getId() == proyek_id && pegawai_outsourcing.get(i).getStatus() == true) {
				nip_pegawai_pada_proyek_ini.add(pegawai_outsourcing.get(i).getNip());
				nama_pegawai_pada_proyek_ini.add(pegawai_outsourcing.get(i).getPelamar_id().getNama_lengkap());
				pegawai_pegawai_proyek_ini.add(pegawai_outsourcing.get(i));
			}	
		}
		for(int i = 0 ; i < nama_pegawai_pada_proyek_ini.size() ; i ++) {
			KehadiranModel kehadiran = new KehadiranModel();
			if(pegawai_pegawai_proyek_ini.get(i).getProyek().getId() == proyek_id && pegawai_pegawai_proyek_ini.get(i).getStatus() == true ) {

				kehadiran.setPegawai_outsourcing(pegawai_pegawai_proyek_ini.get(i));
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
											Model model, RedirectAttributes redir) {
		
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
				
				String msg = "Terdapat kehadiran dengan jumlah komponen kehadiran tidak sama dengan jumlah hari kerja";
				redir.addFlashAttribute("fail_notif",msg);
				return "redirect:/proyek/{proyek_id}/kehadiran/tambah";

			}
		}
		kehadiran_service.save_all_kehadiran(daftar_daftar_kehadiran.getDaftar_kehadiran());


		//TO DO render
		redir.addFlashAttribute("notifikasi_sukses","Berhasil Menambahkan Kehadiran dengan judul "+judul_kehadiran);	
		return "redirect:/proyek/"+proyek_id+"/kehadiran";
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
											Model model, RedirectAttributes redir) {
								
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
					redir.addFlashAttribute("fail_notif", "Terdapat kehadiran dengan jumlah komponen kehadiran tidak sama dengan jumlah hari kerja");
					return "redirect:/proyek/{proyek_id}/kehadiran/update/"+judul_kehadiran;
				}
			}
			kehadiran_service.save_all_kehadiran(daftar_daftar_kehadiran.getDaftar_kehadiran());				
			
				redir.addFlashAttribute("notifikasi_sukses","Berhasil Mengubah Kehadiran dengan judul "+judul_kehadiran);
				return "redirect:/proyek/"+proyek_id+"/kehadiran";
			}
	
	//Hapus Kehadiran
	@RequestMapping(value="/proyek/{proyek_id}/kehadiran/hapus/{judul_kehadiran}", method=RequestMethod.GET)
	private String hapusKehadiranProyek(@PathVariable(value="proyek_id") long proyek_id,
										 @PathVariable(value="judul_kehadiran") String judul_kehadiran,
										 Model model, RedirectAttributes redir) {
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
	
	//TO DO render
	redir.addFlashAttribute("notifikasi_sukses","Berhasil Menghapus Kehadiran dengan judul "+judul_kehadiran);
	return "redirect:/proyek/"+proyek_id+"/kehadiran";
	}
	
	
	//Do Payment
	@RequestMapping(value="/proyek/{proyek_id}/kehadiran/{judul_kehadiran}/penggajian",method=RequestMethod.GET)
	private String penggajian(@PathVariable(value="proyek_id") long proyek_id,
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
		//APAKAH jika tidak ada gaji dia bisa di gaji????
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
		return "form_cek_kehadiran";
	}
	
	@RequestMapping(value="/proyek/{proyek_id}/kehadiran/{judul_kehadiran}/penggajian2",method=RequestMethod.POST)
	private String penggajianTahap2(@PathVariable(value="proyek_id") long proyek_id,
								    @PathVariable(value="judul_kehadiran") String judul_kehadiran,
								    @ModelAttribute KehadiranWrapper daftar_daftar_kehadiran, 
									HttpServletRequest req,
								    Model model, RedirectAttributes redir) {
		int jumlah_hari_kerja = Integer.parseInt(req.getParameter("jumlah_hari_kerja"));
		String judul_kehadiran_2 = String.valueOf(req.getParameter("judul_absensi"));
		ProyekModel proyek = new ProyekModel();
		PenggajianWrapper daftar_penggajian = new PenggajianWrapper();
		List<PegawaiOutsourcingModel> daftar_pegawai = new ArrayList<PegawaiOutsourcingModel>();
		List<KehadiranModel> daftar_kehadiran = new ArrayList<KehadiranModel>();
		for(int i = 0 ; i < proyek_service.getAllProyek().size() ; i++) {
			if(proyek_service.getAllProyek().get(i).getId() == proyek_id) {
				proyek = proyek_service.getAllProyek().get(i);
				}
			}
		for(int i = 0 ; i < daftar_daftar_kehadiran.getDaftar_kehadiran().size() ; i++) {
			daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).setProyek(proyek);
			daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).setJudul_kehadiran(judul_kehadiran_2);
			daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).setJumlah_hari_kerja(jumlah_hari_kerja);
			if(jumlah_hari_kerja != daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_absen()+
									daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_cuti()+
									daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_izin()+
									daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_kehadiran()+
									daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_off()+
									daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_sakit()) {
				redir.addFlashAttribute("fail_notif", "Terdapat kehadiran dengan jumlah komponen kehadiran tidak sama dengan jumlah hari kerja");
				return "redirect:/proyek/{proyek_id}/kehadiran/"+judul_kehadiran_2+"/penggajian";
			}
			GajiModel penggajian = new GajiModel();
			penggajian.setPegawai_outsourcing(daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getPegawai_outsourcing());			
			penggajian.calculate_potongan(penggajian.getPegawai_outsourcing().getGaji_pokok(), jumlah_hari_kerja, daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_kehadiran());
			//calculate total tunjangan (tunjangan bulanan+(tunjangan harian * hadir))
			penggajian.calculate_total_tunjangan(daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getPegawai_outsourcing().getTunjangan_tetap(),
												 daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getPegawai_outsourcing().getTunjangan_tidak_tetap(),
												 daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getJumlah_kehadiran());
			
			
			daftar_kehadiran.add(daftar_daftar_kehadiran.getDaftar_kehadiran().get(i));
			daftar_penggajian.add_penggajian(penggajian);
			daftar_pegawai.add(daftar_daftar_kehadiran.getDaftar_kehadiran().get(i).getPegawai_outsourcing());
		}
		kehadiran_service.save_all_kehadiran(daftar_daftar_kehadiran.getDaftar_kehadiran());				
		
		//detail2 kehadiran
		
		//TO DO render
		model.addAttribute("listPegawai", daftar_pegawai);
		model.addAttribute("proyek_id", proyek_id);
		model.addAttribute("judul_kehadiran", judul_kehadiran);
		model.addAttribute("daftar_penggajian", daftar_penggajian);
		model.addAttribute("daftar_kehadiran", daftar_kehadiran);
		return "form_penggajian";		
	}
	
	@RequestMapping(value="/proyek/{proyek_id}/kehadiran/{judul_kehadiran}/penggajian3",method=RequestMethod.POST)
	private String penggajianTahap3(@PathVariable(value="proyek_id") long proyek_id,
								    @PathVariable(value="judul_kehadiran") String judul_kehadiran,
								    @ModelAttribute PenggajianWrapper daftar_penggajian, 
									HttpServletRequest req,
								    Model model) {
		VariableGajiModel variable = variable_service.getVariableGaji();
		List<Penggajian> daftar_rekap_gaji = new ArrayList<Penggajian>();
		List<GajiModel> daftar_gaji = new ArrayList<GajiModel>();
		List<PegawaiOutsourcingModel> daftar_pegawai = new ArrayList<PegawaiOutsourcingModel>();
		List<KehadiranModel> daftar_kehadiran = new ArrayList<KehadiranModel>();
 		for(int i = 0 ; i < daftar_penggajian.getDaftar_penggajian().size() ; i++) {
			
			long gaji_bruto = daftar_penggajian.getDaftar_penggajian().get(i).getPegawai_outsourcing().getGaji_pokok()+
							  daftar_penggajian.getDaftar_penggajian().get(i).getTotal_tunjangan()+
							  daftar_penggajian.getDaftar_penggajian().get(i).getPenambahan_lain_lain()+
							  daftar_penggajian.getDaftar_penggajian().get(i).getInsentif() - (
							  daftar_penggajian.getDaftar_penggajian().get(i).getPengurangan_lain_lain()+
							  daftar_penggajian.getDaftar_penggajian().get(i).getPinjaman()+
							  daftar_penggajian.getDaftar_penggajian().get(i).getPotongan());
			float bpjstk = 0;
			float gaji_pokok = daftar_penggajian.getDaftar_penggajian().get(i).getPegawai_outsourcing().getGaji_pokok();
			float  var_bpjstk = variable.getBPJSTK();
			float var_bpjsk = variable.getBPJSK();
			if(!daftar_penggajian.getDaftar_penggajian().get(i).getPegawai_outsourcing().getBpjstk().isEmpty()) {
				bpjstk =  gaji_pokok * (var_bpjstk/100);
			}
			float bpjsk = 0;
			if(!daftar_penggajian.getDaftar_penggajian().get(i).getPegawai_outsourcing().getBpjsk().isEmpty()) {
				bpjsk =   gaji_pokok * (var_bpjsk/100);
			}
			float total_bpjs = bpjstk+bpjsk;
			float jumlah_gaji = gaji_bruto - total_bpjs;
			long ptkp = variable.getPTKP();
			float pkp = jumlah_gaji - ptkp;
			float pph21 = 0;
			float var_pph21 = variable.getPersenan_pph();
			if(pkp > 0 ) {
				pph21 = (var_pph21/100) * pkp;
			}
			float total_potongan = total_bpjs + pph21;
			float gaji_netto = jumlah_gaji - pph21;
			Penggajian rekap_gaji = new Penggajian(daftar_penggajian.getDaftar_penggajian().get(i), 
												   daftar_penggajian.getDaftar_penggajian().get(i).getPegawai_outsourcing(), 
												(int)gaji_bruto, 
												(int)bpjstk, 
												(int)bpjsk, 
												(int)total_bpjs, 
												(int)jumlah_gaji, 
												(int)ptkp, 
												(int)pkp, 
												(int)pph21, 
												(int)total_potongan, 
												(int)gaji_netto);
			daftar_rekap_gaji.add(rekap_gaji);
			daftar_gaji.add(daftar_penggajian.getDaftar_penggajian().get(i));
			daftar_pegawai.add(daftar_penggajian.getDaftar_penggajian().get(i).getPegawai_outsourcing());
			gaji_service.save_gaji(daftar_penggajian.getDaftar_penggajian().get(i));
		}
 		for(int i = 0 ; i < kehadiran_service.get_all_kehadiran().size() ; i++) {
 			for(int j = 0 ; j < pegawai_outsourcing_service.getAllPegawai().size() ; j++) {
 				if(kehadiran_service.get_all_kehadiran().get(i).getProyek().getId()==proyek_id) {
	 				if(kehadiran_service.get_all_kehadiran().get(i).getJudul_kehadiran().equals(judul_kehadiran)) {
 						if(kehadiran_service.get_all_kehadiran().get(i).getPegawai_outsourcing().getId()==pegawai_outsourcing_service.getAllPegawai().get(j).getId()) {
 							daftar_kehadiran.add(kehadiran_service.get_all_kehadiran().get(i));
	 					}
	 				}
 				}
 			}
 		}
 		
		model.addAttribute("rekap_gaji", daftar_rekap_gaji);
		model.addAttribute("daftar_gaji", daftar_gaji);
		model.addAttribute("listPegawai", daftar_pegawai);
		model.addAttribute("daftar_kehadiran", daftar_kehadiran);
		model.addAttribute("variable", variable);
		
		
		return "detail_penggajian";
	}
	

	
	
	
}
