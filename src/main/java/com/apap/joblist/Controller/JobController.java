package com.apap.joblist.Controller;

import java.time.LocalDate;
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
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.apap.joblist.Model.AccountModel;
import com.apap.joblist.Service.AccountService;

@Controller
public class JobController {
	@Autowired
	private AccountService akun_service;
	
	@RequestMapping("/job-list")
	private String proyek(Model model,HttpServletRequest req) {
		// List<ProyekModel> list = proyekService.getAllProyek();
		// model.addAttribute("listProyek", list);

		AccountModel user = akun_service.findByUsername(req.getRemoteUser());
		model.addAttribute("user", user);
		return "joblist";
	}
	
	//Detail Proyek
	
	@RequestMapping(value = "/job-list/{id}", method = RequestMethod.GET)
	private String detailProyek(@PathVariable long id, Model model, HttpServletRequest req) {
		// ProyekModel proyek = proyekService.getProyekById(id).get();
		// model.addAttribute("proyek", proyek);

		// List<PegawaiOutsourcingModel> pegawaiOutsourcing = pegawaiService.getAllPegawai();
		// List<PegawaiOutsourcingModel> pegawaiProyek = new ArrayList<PegawaiOutsourcingModel>();
		// for (int i=0; i<pegawaiOutsourcing.size(); i++){
		// 	if ((pegawaiOutsourcing.get(i)).getProyek() != null) {
		// 		if ((pegawaiOutsourcing.get(i).getProyek().getId())==(proyek.getId()) && pegawaiOutsourcing.get(i).getStatus() == true ){
		// 			pegawaiProyek.add(pegawaiOutsourcing.get(i));
		// 		}
		// 	}
		// }
		
		AccountModel user = akun_service.findByUsername(req.getRemoteUser());
		model.addAttribute("user", user);

		// model.addAttribute("listPegawai", pegawaiProyek);
		return "jobdetail";
	}
}


















