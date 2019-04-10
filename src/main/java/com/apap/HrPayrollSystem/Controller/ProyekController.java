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
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.ProyekModel;
import com.apap.HrPayrollSystem.Service.PegawaiOutsourcingService;
import com.apap.HrPayrollSystem.Service.ProyekService;
import com.apap.HrPayrollSystem.Utility.PegawaiProyekWrapper;

@Controller
public class ProyekController {
	@Autowired
	private ProyekService proyekService;
	@Autowired
	private PegawaiOutsourcingService pegawaiService;
	
	@RequestMapping("/proyek")
	private String proyek(Model model) {
		List<ProyekModel> list = proyekService.getAllProyek();
		model.addAttribute("listProyek", list);
		
		return "list_proyek";
	}
	
	//Detail Proyek
	
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
		return "list_proyek";
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

		pegawaiService.save_all_pegawai_proyek(listPegawai.getListPegawai());
		return "list_proyek";
	}

}


















