package com.apap.joblist.Controller;

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

import com.apap.joblist.Model.AccountModel;
import com.apap.joblist.Service.AccountService;
import com.apap.joblist.security.UserDetailsServiceImpl;

@Controller
public class AccountController {
	@Autowired
	private AccountService akun_service;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	//list akun untuk admin
	@RequestMapping(value="/account-list", method=RequestMethod.GET)
	private String daftarAkun(Model model, HttpServletRequest req ) {
		List<AccountModel> get_all_account = akun_service.get_all_account();
		AccountModel akun = akun_service.findByUsername(req.getRemoteUser());

		model.addAttribute("akun_akun", get_all_account);		
		return "list_akun";
	}
	
}
