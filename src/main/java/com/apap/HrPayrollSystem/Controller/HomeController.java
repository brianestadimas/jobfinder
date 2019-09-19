package com.apap.HrPayrollSystem.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apap.HrPayrollSystem.Model.AccountModel;
import com.apap.HrPayrollSystem.Model.PelamarModel;
import com.apap.HrPayrollSystem.Model.ProyekModel;
import com.apap.HrPayrollSystem.Service.AccountService;
import com.apap.HrPayrollSystem.Service.PegawaiOutsourcingService;
import com.apap.HrPayrollSystem.Service.PelamarService;
import com.apap.HrPayrollSystem.Service.ProyekService;

@Controller
public class HomeController {
	@Autowired
	ProyekService proyek_service;
	@Autowired
	PelamarService pelamar_service;
	@Autowired
	AccountService akun_service;
	@Autowired
	PegawaiOutsourcingService pegawaiService;
	
	@RequestMapping("/")
	private String home(Model model,
			HttpServletRequest req) {
		AccountModel user = akun_service.findByUsername(req.getRemoteUser());
		if(user.getRole().equals("pelamar")) {
			return "redirect:/pelamar/daftar";
		}
		List<ProyekModel> list_of_proyek = new ArrayList<ProyekModel>();
		List<PelamarModel> pelamar_belum_assign = new ArrayList<PelamarModel>();
		for(int i = 0 ; i < pelamar_service.getAllPelamar().size() ; i++) {
			if(pelamar_service.getAllPelamar().get(i).isIs_pegawai()==false) {
				if(pelamar_belum_assign.size()<7) {
					pelamar_belum_assign.add(pelamar_service.getAllPelamar().get(i));
				}
				
			}
		}
		for(int i = 0 ; i < proyek_service.getAllProyek().size() ; i++) {
			if(list_of_proyek.size()<7) {
				list_of_proyek.add(proyek_service.getAllProyek().get(i));
			}
		}
		model.addAttribute("user", user);
		model.addAttribute("pelamar", pelamar_belum_assign);
		model.addAttribute("proyek",list_of_proyek);
		model.addAttribute("pegawai", pegawaiService.getAllPegawai());

		//Changelog 19/09/2019
		model.addAttribute("pelamarLength", pelamar_belum_assign.size());
		model.addAttribute("proyekLength",list_of_proyek.size());
		model.addAttribute("pegawaiLength", pegawaiService.getAllPegawai().size());
		return"home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
