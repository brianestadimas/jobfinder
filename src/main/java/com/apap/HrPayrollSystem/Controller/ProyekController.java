package com.apap.HrPayrollSystem.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.HrPayrollSystem.Model.KehadiranModel;
import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.ProyekModel;
import com.apap.HrPayrollSystem.Service.KehadiranService;
import com.apap.HrPayrollSystem.Service.PegawaiOutsourcingService;
import com.apap.HrPayrollSystem.Service.ProyekService;
import com.apap.HrPayrollSystem.Utility.PegawaiProyekWrapper;

@Controller
public class ProyekController {
	@Autowired
	private ProyekService proyekService;
	@Autowired
	private PegawaiOutsourcingService pegawaiService;
	@Autowired
	private KehadiranService kehadiranService;
	
	/**
	 * Fitur Menampilkan Daftar Proyek
	 * @param model Model
	 * @return Halaman HTML list_proyek
	 */
	@RequestMapping("/proyek")
	private String proyek(Model model) {
		List<ProyekModel> list = proyekService.getAllProyek();
		model.addAttribute("listProyek", list);
		
		return "list_proyek";
	}
	
	/**
	 * Fitur Melihat Detail Proyek
	 * @param id idProyek
	 * @param model Model
	 * @return Halaman HTML detail_proyek
	 */
	@RequestMapping(value = "/proyek-detail/{id}", method = RequestMethod.GET)
	private String detailProyek(@PathVariable long id, Model model) {
		ProyekModel proyek = proyekService.getProyekById(id).get();
		model.addAttribute("proyek", proyek);

		List<PegawaiOutsourcingModel> pegawaiOutsourcing = pegawaiService.getAllPegawai();
		List<PegawaiOutsourcingModel> pegawaiProyek = new ArrayList<PegawaiOutsourcingModel>();
		for (int i=0; i<pegawaiOutsourcing.size(); i++){
			if ((pegawaiOutsourcing.get(i)).getProyek() != null) {
				if ((pegawaiOutsourcing.get(i).getProyek().getId())==(proyek.getId())){
					pegawaiProyek.add(pegawaiOutsourcing.get(i));
				}
			}
		}
		//Performa dalam Proyek (4 Bulan Terakhir)
		List<KehadiranModel> kehadiranProyek = kehadiranService.get_all_kehadiran_by_proyek(proyek);
		int persentasePerforma = 0;
		int totalHadir = 0;
		int totalSakit = 0;
		int totalIzin = 0;
		int totalAlfa =0;
		if (kehadiranProyek.size() == 0) {
			model.addAttribute("performaErr_Msg",
					"Performa belum bisa dihitung, tambahkan data kehadiran terlebih dahulu !");
		} else if (kehadiranProyek.size() == 1) {
			totalHadir=kehadiranProyek.get(0).getJumlah_kehadiran();
			totalSakit=kehadiranProyek.get(0).getJumlah_sakit();
			totalIzin=kehadiranProyek.get(0).getJumlah_izin();
			totalAlfa=kehadiranProyek.get(0).getJumlah_absen();
			persentasePerforma = 100;
		} else {
			int kehadiranSebelum = kehadiranProyek.get(1).getJumlah_kehadiran();
			int kehadiranSesudah = kehadiranProyek.get(0).getJumlah_kehadiran();
			persentasePerforma = (kehadiranSesudah - kehadiranSebelum) / kehadiranSebelum * 100;
		}
		model.addAttribute("kehadiranPegawai", kehadiranProyek);
		model.addAttribute("persentasePerforma", persentasePerforma);
		
		
		
		model.addAttribute("listPegawai", pegawaiProyek);
		return "detail_proyek";
	}
	
	//Delete Proyek
	
	@RequestMapping(value = "/proyek-hapus/{id}", method = RequestMethod.GET)
	private String deleteProyek(@PathVariable(value = "id") long id, Model model) {

		List<PegawaiOutsourcingModel> pegawaiOutsourcing = pegawaiService.getAllPegawai();
		for (int i=0; i<pegawaiOutsourcing.size(); i++){
			if ((pegawaiOutsourcing.get(i)).getProyek() != null) {
				if ((pegawaiOutsourcing.get(i).getProyek().getId())==(id)){
					pegawaiOutsourcing.get(i).setProyek(null);
					pegawaiOutsourcing.get(i).setStatus(true);
				}
			}
		}
		
		proyekService.deleteById(id);
		return "list_proyek";
	}
	
	//Add Proyek
	
	@RequestMapping(value = "/proyek-tambah", method = RequestMethod.GET)
	public String addProyekGET(Model model) {
		ProyekModel proyek = new ProyekModel();
		model.addAttribute("proyek", proyek);
		
		return "tambah_proyek";
	}
	
	@RequestMapping(value = "/proyek-tambah", method = RequestMethod.POST)
	public String addProyekPost(Model model, @ModelAttribute ProyekModel proyek) {
		proyekService.addProyek(proyek);
		
		List<ProyekModel> list = proyekService.getAllProyek();
		model.addAttribute("listProyek", list);
		
		return "list_proyek";
	}

	//Ubah Proyek

	@RequestMapping(value = "/proyek-ubah/{id}", method = RequestMethod.GET)
	private String ubahProyekGET(@PathVariable(value = "id") long id, Model model) {
		ProyekModel proyekLama = proyekService.getProyekById(id).get();
		model.addAttribute("proyekLama", proyekLama);
		
		return "ubah_proyek";
	}
	
	@RequestMapping(value = "/proyek-ubah/{id}", method = RequestMethod.POST)
	private String ubahProyekPost(@PathVariable(value = "id") long id, ProyekModel proyek, Model model) {

		proyekService.updateProyek(id, proyek);
		
		
		model.addAttribute("proyek", proyek);
		
		List<PegawaiOutsourcingModel> pegawaiOutsourcing = pegawaiService.getAllPegawai();
		List<PegawaiOutsourcingModel> pegawaiProyek = new ArrayList<PegawaiOutsourcingModel>();
		for (int i=0; i<pegawaiOutsourcing.size(); i++){
			if ((pegawaiOutsourcing.get(i)).getProyek() != null) {
				if ((pegawaiOutsourcing.get(i).getProyek().getId())==(proyek.getId())){
					pegawaiProyek.add(pegawaiOutsourcing.get(i));
				}
			}
		}
		model.addAttribute("listPegawai", pegawaiProyek);
		
		return "detail_proyek";
	}

	//Ubah Pegawai Proyek
	
	@RequestMapping(value = "/proyek-pegawai/{id}", method = RequestMethod.GET)
	private String ubahPegawaiProyekGET(@PathVariable(value = "id") long id, Model model) {
		ProyekModel proyek = proyekService.getProyekById(id).get();
		model.addAttribute("proyek", proyek);

		List<PegawaiOutsourcingModel> pegawaiOutsourcing = pegawaiService.getAllPegawai();
		List<PegawaiOutsourcingModel> pegawaiProyek = new ArrayList<PegawaiOutsourcingModel>();
		for (int i=0; i<pegawaiOutsourcing.size(); i++){
			if ((pegawaiOutsourcing.get(i)).getProyek() != null) {
				if ((pegawaiOutsourcing.get(i).getProyek().getId())==(proyek.getId())){
					pegawaiProyek.add(pegawaiOutsourcing.get(i));
				}
			}
		}
		PegawaiProyekWrapper listPegawai= new PegawaiProyekWrapper();
		
		for (int i=0; i<pegawaiProyek.size(); i++) {
			listPegawai.addListPegawai(pegawaiProyek.get(i));
		}
		model.addAttribute("listPegawai", listPegawai);
		
		return "ubah_pegawai_proyek";
	}
	@RequestMapping(value = "/proyek-pegawai/{id}", method = RequestMethod.POST)
	private String ubahPegawaiProyekPost(@PathVariable(value = "id") long id, @ModelAttribute PegawaiProyekWrapper listPegawai, Model model) {
		ProyekModel proyek = proyekService.getProyekById(id).get();
		pegawaiService.save_all_pegawai_proyek(listPegawai.getListPegawai());
		
		List<PegawaiOutsourcingModel> pegawaiOutsourcing = pegawaiService.getAllPegawai();
		List<PegawaiOutsourcingModel> pegawaiProyek = new ArrayList<PegawaiOutsourcingModel>();
		for (int i=0; i<pegawaiOutsourcing.size(); i++){
			if ((pegawaiOutsourcing.get(i)).getProyek() != null) {
				if ((pegawaiOutsourcing.get(i).getProyek().getId())==(proyek.getId())){
					pegawaiProyek.add(pegawaiOutsourcing.get(i));
				}
			}
		}
		
		model.addAttribute("proyek", proyek);
		model.addAttribute("listPegawai", pegawaiProyek);
		return "detail_proyek";
	}

}


















