package com.apap.HrPayrollSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Service.PegawaiOutsourcingService;
import com.apap.HrPayrollSystem.Service.ProyekService;

@Controller
public class PegawaiOutsourcingController {
	@Autowired
	private ProyekService proyekService;
	@Autowired
	private PegawaiOutsourcingService pegawaiService;
	
	@RequestMapping("/pegawai")
	private String pegawai(Model model) {
		List<PegawaiOutsourcingModel> list = pegawaiService.getAllPegawai();
		model.addAttribute("listPegawai", list);
		
		return "DaftarPegawai";
	}
	
	@RequestMapping(value = "/pegawai-detail/{id}", method = RequestMethod.GET)
	private String detailPegawain(@PathVariable long id, Model model) {
		PegawaiOutsourcingModel pegawai = pegawaiService.getPegawaiById(id).get();
		model.addAttribute("pegawai", pegawai);
		return "DetailPegawai";
	}
	
	@RequestMapping(value = "/pegawai-hapus", method = RequestMethod.POST)
	private String deletePegawai(@RequestParam("id") long id, Model model) {
		
		try {
			pegawaiService.deletePegawaiById(id);
			return "DaftarPegawai";
		} catch(Exception e) {
			return null;
		}
		
	}

	@RequestMapping(value = "/pegawai-assign", method = RequestMethod.GET)
	private String assignPegawai(@PathVariable long id, Model model) {
		return "form_assignment";
	}
	
}
