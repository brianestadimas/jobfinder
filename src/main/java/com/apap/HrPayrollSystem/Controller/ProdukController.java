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

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.ProdukModel;
import com.apap.HrPayrollSystem.Service.PegawaiOutsourcingService;
import com.apap.HrPayrollSystem.Service.ProdukService;


@Controller
public class ProdukController {
	@Autowired
	private ProdukService produk_service;
	@Autowired
	private PegawaiOutsourcingService pegawai_service;

	
	//list all produk
	@RequestMapping(value="/produk",method=RequestMethod.GET)
	private String daftarProduk(Model model) {
		List<ProdukModel> get_all_produk = produk_service.getAllProduk();
		model.addAttribute("produk_produk", get_all_produk);
		return "list_produk";
	}
	
	//add produk
	@RequestMapping(value="/produk/tambah",method=RequestMethod.GET)
	private String tambahProduk(Model model) {
		ProdukModel produk = new ProdukModel();
		model.addAttribute("produk",produk);
		return "tambah_produk";
	}
	@RequestMapping(value="/produk/tambah/submit",method=RequestMethod.POST)
	private String tambahProdukSubmit(Model model,
									  @ModelAttribute ProdukModel produk, 
									  RedirectAttributes redir) {
		produk_service.saveProduk(produk);
		redir.addFlashAttribute("notifikasi", "Produk Berhasil Ditambah");
		return "redirect:/produk";

	}
	
	//edit produk
	@RequestMapping(value="/produk/update/{id}",method=RequestMethod.GET)
	private String editProduk(@PathVariable(value="id") long id ,
							  Model model) {
		ProdukModel produk_target = produk_service.getProdukById(id);
		model.addAttribute("produk", produk_target);		
		return "update_produk";
	}
	
	@RequestMapping(value="/produk/update/submit",method=RequestMethod.POST)
	private String editProdukSubmit(@ModelAttribute ProdukModel produk,
									Model model, 
									RedirectAttributes redir) {
		produk_service.saveProduk(produk);
		redir.addFlashAttribute("notifikasi", "Produk Berhasil Diubah");
		return "redirect:/produk";
	}
	
	//hapus produk
	@RequestMapping(value="/produk/hapus/{id}",method=RequestMethod.GET)
	private String hapusProduk(@PathVariable(value="id") long id ,
								Model model, RedirectAttributes redir) {
		ProdukModel produk_target = produk_service.getProdukById(id);
		
		List<PegawaiOutsourcingModel> listPegawai = pegawai_service.getAllPegawai();
		
		
		for (PegawaiOutsourcingModel target : listPegawai) {
			if (target.getProduk().getId() == produk_target.getId()) {
				String msg = "Produk Masih Milik Seorang Pegawai";
			    redir.addFlashAttribute("fail_notif",msg);
				return "redirect:/produk";
			}
		}
		
		produk_service.deleteProdukById(id);
		redir.addFlashAttribute("notifikasi", "Produk Berhasil Dihapus");
		return "redirect:/produk";
	}

	

}
