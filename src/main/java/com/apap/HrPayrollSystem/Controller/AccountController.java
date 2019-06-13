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

import com.apap.HrPayrollSystem.Model.AccountModel;
import com.apap.HrPayrollSystem.Service.AccountService;
import com.apap.HrPayrollSystem.security.UserDetailsServiceImpl;

@Controller
public class AccountController {
	@Autowired
	private AccountService akun_service;
	
	//list akun untuk admin
	@RequestMapping(value="/account/list", method=RequestMethod.GET)
	private String daftarAkun(Model model, HttpServletRequest req ) {
		List<AccountModel> get_all_account = akun_service.get_all_account();
		AccountModel akun = akun_service.findByUsername(req.getRemoteUser());
		String peran = akun.getRole();
		List<AccountModel> akun_ditampilkan = new ArrayList<AccountModel>();
		if(peran.equals("manager")) {
			for(int i = 0 ; i < get_all_account.size() ; i++) {
				if(get_all_account.get(i).getRole().equals("hr") || get_all_account.get(i).getRole().equals("busdev") || get_all_account.get(i).getRole().equals("pelamar") || get_all_account.get(i).getRole().equals("viewer") ) {
					akun_ditampilkan.add(get_all_account.get(i));
				}
			}
		}else if(peran.equals("hr")) {
			for(int i = 0 ; i < get_all_account.size() ; i++) {
				if(get_all_account.get(i).getRole().equals("pelamar")) {
					akun_ditampilkan.add(get_all_account.get(i));
				}
			}
		}else if(peran.equals("admin")) {
			for(int i = 0 ; i < get_all_account.size() ; i++) {
				if(!(get_all_account.get(i).getRole().equals("admin"))) {
					akun_ditampilkan.add(get_all_account.get(i));
				}
			}
		}
		model.addAttribute("akun_akun", akun_ditampilkan);		
		return "list_akun";
	}
	
	
	//add akun untuk admin
	@RequestMapping(value="/account/add", method=RequestMethod.GET)
	private String tambahAkun(Model model,
							HttpServletRequest req) {
		AccountModel user = akun_service.findByUsername(req.getRemoteUser());
		String peran = user.getRole();
		String return_page = "";
		if(peran.equals("admin")) {
			return_page = "tambah_akun";
		}else if(peran.equals("manager")) {
			return_page = "tambah_akun_manager";
		}else if(peran.equals("hr")) {
			return_page = "tambah_akun_hr";
		}
		return return_page;
	}
	
	@RequestMapping(value="/account/add/submit", method=RequestMethod.POST)
	private String tambahAkunSubmit(Model model,
									@ModelAttribute AccountModel account,
									HttpServletRequest req, RedirectAttributes redir) {
		List<AccountModel> get_all_account = akun_service.get_all_account();
		if(get_all_account.isEmpty()) {
			akun_service.addAccount(account);
		}else {
			for(int i = 0 ; i < get_all_account.size() ; i++) {
				if(account.getUsername().equalsIgnoreCase(get_all_account.get(i).getUsername())) {
					redir.addFlashAttribute("notif", "Sudah terdapat akun dengan username " +account.getUsername() );		
					return "redirect:/account/add";
				}
			}
			akun_service.addAccount(account);
		}
		String peran = "";
		if(account.getRole().equalsIgnoreCase("busdev")) {
			peran ="Business Development";
		}else if(account.getRole().equalsIgnoreCase("hr")) {
			peran ="Human Resources";
		}else if(account.getRole().equalsIgnoreCase("manager")) {
			peran = "Manager";
		}else if(account.getRole().equalsIgnoreCase("pelamar")) {
			peran = "Pelamar";
		}else if(account.getRole().equalsIgnoreCase("viewer")) {
			peran = "Pegawai";
		}
		redir.addFlashAttribute("notif", "Berhasil menambahkan akun dengan nama "+account.getName()+" dengan peran "+peran );		
		return "redirect:/account/list";
	}
	
	//hapus akun untuk admin
	@RequestMapping(value="/account/delete/{id}", method=RequestMethod.GET)
	private String hapusAkun(Model model,
							 @ModelAttribute AccountModel account,
							 HttpServletRequest req, RedirectAttributes redir) {
		akun_service.delete_account(account);
		redir.addFlashAttribute("notif", "Berhasil menghapus akun");
		return "redirect:/account/list";
	}
	
	//update akun untuk admin
	@RequestMapping(value="/account/update/{id}", method=RequestMethod.GET)
	private String updateAkun(@PathVariable(value="id") long id, 
							  Model model,
							  HttpServletRequest req) {
		AccountModel akun_diupdate = akun_service.get_account_by_id(id);
		AccountModel user = akun_service.findByUsername(req.getRemoteUser());
		String peran = user.getRole();
		String return_page = "";
		if(peran.equals("admin")) {
			return_page = "update_akun";
		}else if(peran.equals("manager")) {
			return_page = "update_akun_manager";
		}else if(peran.equals("hr")) {
			return_page = "update_akun_hr";
		}
		
		model.addAttribute("akun", akun_diupdate);
		return return_page;
	}
	
	@RequestMapping(value="/account/update/submit", method=RequestMethod.POST)
	private String updateAkunSubmit(Model model,
									@ModelAttribute AccountModel account,
									HttpServletRequest req, RedirectAttributes redir) {
//		akun_service.save_account(account);
		
		for(int i = 0 ; i < akun_service.get_all_account().size() ; i++) {
			if(account.getUsername().equals(akun_service.get_all_account().get(i).getUsername())) {
				redir.addFlashAttribute("notif", "Sudah terdapat akun dengan username " +account.getUsername() );
				return "redirect:/account/update/"+account.getId();
			}
		}
		akun_service.save_account(account);
		redir.addFlashAttribute("notif", "Berhasil mengubah akun dengan nama "+ account.getName());
		return "redirect:/account/list";
	}
	
	//view detail akun 
	@RequestMapping(value="/my-account", method=RequestMethod.GET)
	private String detailAkunSaya(Model model,HttpServletRequest req) {
		AccountModel akun = akun_service.findByUsername(req.getRemoteUser());
		
		model.addAttribute("akun", akun);
		return "akun_saya";
	}
	
	
	//edit password&username akun untuk pegawai
	@RequestMapping(value="/my-account/update", method=RequestMethod.GET)
	private String updateAkunSaya(Model model,HttpServletRequest req) {
		AccountModel akun = akun_service.findByUsername(req.getRemoteUser());
		
		
		model.addAttribute("akun", akun);
		return "update_akun_saya";
	}
	
	@RequestMapping(value="/my-account/update/submit", method=RequestMethod.POST)
	private String updateAkunSayaSubmit(HttpServletRequest req, RedirectAttributes redir) {
		String password_lama = req.getParameter("password_lama");
		String password_baru = req.getParameter("password_baru");
		AccountModel akun = akun_service.findByUsername(req.getRemoteUser());
		boolean cek_password = akun_service.decrypt(password_lama, akun.getPassword());
		System.out.println(cek_password);
		if(cek_password == true) {
//			account.setPassword(password_baru);
//			akun_service.save_account(account);
			String new_pass = akun_service.encrypt(password_baru);
			akun_service.changePassword(new_pass, req.getRemoteUser());
			redir.addFlashAttribute("notif", "Berhasil mengubah password");
			return "redirect:/my-account";
		}else {
			redir.addFlashAttribute("notif", "Password lama tidak sesuai dengan yang ada di database");
			return "redirect:/my-account/update";
		}


	}
	
	
}
