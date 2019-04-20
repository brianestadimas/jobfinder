package com.apap.HrPayrollSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.HrPayrollSystem.Model.AccountModel;
import com.apap.HrPayrollSystem.Service.AccountService;

@Controller
public class AccountController {
	@Autowired
	private AccountService akun_service;
	
	//list akun untuk admin
	@RequestMapping(value="/account/list", method=RequestMethod.GET)
	private String daftarAkun(Model model) {
		List<AccountModel> get_all_account = akun_service.get_all_account();
		model.addAttribute("akun_akun", get_all_account);		
		return "list_akun";
	}
	
	
	//add akun untuk admin
	@RequestMapping(value="/account/add", method=RequestMethod.GET)
	private String tambahAkun(Model model) {
		AccountModel akun = new AccountModel();
		model.addAttribute("akun",akun);
		return "tambah_akun";
	}
	
	@RequestMapping(value="/account/add/submit", method=RequestMethod.POST)
	private String tambahAkunSubmit(Model model,
									@ModelAttribute AccountModel account) {
		
		
		akun_service.addAccount(account);

		
		List<AccountModel> get_all_account = akun_service.get_all_account();
		model.addAttribute("akun_akun", get_all_account);	
		return "list_akun";
	}
	
	//hapus akun untuk admin
	@RequestMapping(value="/account/delete/{id}", method=RequestMethod.GET)
	private String hapusAkun(Model model,
							 @ModelAttribute AccountModel account) {
		akun_service.delete_account(account);
		List<AccountModel> get_all_account = akun_service.get_all_account();
		model.addAttribute("akun_akun", get_all_account);	
		return "list_akun";
	}
	
	//update akun untuk admin
	@RequestMapping(value="/account/update/{id}", method=RequestMethod.GET)
	private String updateAkun(@PathVariable(value="id") long id, 
							  Model model) {
		AccountModel akun_diupdate = akun_service.get_account_by_id(id);
		model.addAttribute("akun", akun_diupdate);
		return "update_akun";
	}
	
	@RequestMapping(value="/account/update/submit", method=RequestMethod.POST)
	private String updateAkunSubmit(Model model,
									@ModelAttribute AccountModel akun) {
		akun_service.save_account(akun);
		
		List<AccountModel> get_all_account = akun_service.get_all_account();
		model.addAttribute("akun_akun", get_all_account);
		return "list_akun";
	}
	
//	//view detail akun 
//	@RequestMapping(value="/my-account", method=RequestMethod.GET)
//	private String detailAkunSaya(Model model) {
//		
//		
//		
//		
//		return "akun_saya";
//	}
//	
//	
//	//edit password&username akun untuk pegawai
//	@RequestMapping(value="/my-account/update", method=RequestMethod.GET)
//	private String updateAkunSaya(Model model) {
//		
//		
//		
//		
//		return "update_akun_saya";
//	}
//	
//	@RequestMapping(value="/my-account/update", method=RequestMethod.POST)
//	private String updateAkunSayaSubmit(Model model) {
//		
//		
//		
//		
//		return "akun_saya";
//	}
	
	
}
