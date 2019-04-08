package com.apap.HrPayrollSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apap.HrPayrollSystem.Model.PelamarModel;
import com.apap.HrPayrollSystem.Model.ProyekModel;
import com.apap.HrPayrollSystem.Service.PelamarService;
import com.apap.HrPayrollSystem.Service.ProyekService;

@Controller
public class HomeController {
	@Autowired
	ProyekService proyek_service;
	@Autowired
	PelamarService pelamar_service;
	
	@RequestMapping("/home")
	private String home(Model model) {
		List<ProyekModel> list_of_proyek = proyek_service.getAllProyek();
		List<PelamarModel> list_of_pelamar = pelamar_service.getAllPelamar();
		
		model.addAttribute("pelamar", list_of_pelamar);
		model.addAttribute("proyek",list_of_proyek);
		return"home";
	}
}
