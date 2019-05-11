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
		List<ProyekModel> list_of_proyek = proyek_service.getAllProyek();
		List<PelamarModel> pelamar_belum_assign = new ArrayList<PelamarModel>();
		List<PelamarModel> pelamar_sudah_assign = new ArrayList<PelamarModel>();
		for(int i = 0 ; i < pegawaiService.getAllPegawai().size() ; i++) {
			pelamar_sudah_assign.add(pegawaiService.getAllPegawai().get(i).getPelamar_id());
		}
		for(int i = 0 ; i < pelamar_service.getAllPelamar().size();i++) {
			if(!pelamar_sudah_assign.contains(pelamar_service.getAllPelamar().get(i))) {
				pelamar_belum_assign.add(pelamar_service.getAllPelamar().get(i));
			}
		}
		AccountModel user = akun_service.findByUsername(req.getRemoteUser());

		model.addAttribute("user", user);
		model.addAttribute("pelamar", pelamar_belum_assign);
		model.addAttribute("proyek",list_of_proyek);
		return"home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
