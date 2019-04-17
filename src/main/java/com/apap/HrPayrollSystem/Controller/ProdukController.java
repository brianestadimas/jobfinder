package com.apap.HrPayrollSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.HrPayrollSystem.Model.ProdukModel;
import com.apap.HrPayrollSystem.Service.ProdukService;

@Controller
public class ProdukController {
	@Autowired
	private ProdukService produk_service;
	
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
									  @ModelAttribute ProdukModel produk) {
		produk_service.saveProduk(produk);
		
		List<ProdukModel> get_all_produk = produk_service.getAllProduk();
		model.addAttribute("produk_produk", get_all_produk);
		return "list_produk";
	}
	
	//edit produk
	@RequestMapping(value="/produk/update/{id}",method=RequestMethod.GET)
	private String editProduk(@PathVariable(value="id") long id ,
							  Model model) {
		ProdukModel produk_target = produk_service.getProdukById(id);
		model.addAttribute("produk", produk_target);		
		return "update_produk";
	}
	
	@RequestMapping(value="/produk/update/{id}/submit",method=RequestMethod.POST)
	private String editProdukSubmit(@PathVariable(value="id") long id ,
									@ModelAttribute ProdukModel produk,
									Model model) {
		produk_service.saveProduk(produk);
		
		List<ProdukModel> get_all_produk = produk_service.getAllProduk();
		model.addAttribute("produk_produk", get_all_produk);
		return "list_produk";
	}
	
	//hapus produk
//	@RequestMapping(value="/produk/hapus/{id}",method=RequestMethod.POST)
//	private String hapusProduk(@PathVariable(value="id") long id ,
//								Model model) {
//		produk_service.saveProduk(produk);
//		
//		List<ProdukModel> get_all_produk = produk_service.getAllProduk();
//		model.addAttribute("produk_produk", get_all_produk);
//		return "list_produk";
//	}
	

}
