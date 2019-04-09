package com.apap.HrPayrollSystem.Controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.ProdukModel;
import com.apap.HrPayrollSystem.Model.ProyekModel;
import com.apap.HrPayrollSystem.Model.RiwayatKerjaPegawaiModel;
import com.apap.HrPayrollSystem.Service.PegawaiOutsourcingService;
import com.apap.HrPayrollSystem.Service.ProdukService;
import com.apap.HrPayrollSystem.Service.ProyekService;
import com.apap.HrPayrollSystem.Service.RiwayatKerjaPegawaiService;

@Controller
public class PegawaiOutsourcingController {
	@Autowired
	private ProyekService proyekService;
	@Autowired
	private PegawaiOutsourcingService pegawaiService;
	@Autowired
	private ProdukService produkService;
	@Autowired
	private RiwayatKerjaPegawaiService riwayatService;
	
	@RequestMapping("/pegawai")
	private String pegawai(Model model) {
		List<PegawaiOutsourcingModel> list = pegawaiService.getAllPegawai();
		model.addAttribute("listPegawai", list);
		
		
		return "ListPegawai";
	}
	
	@RequestMapping(value = "/pegawai-detail/{id}", method = RequestMethod.GET)
	private String detailPegawain(@PathVariable long id, Model model) {
		PegawaiOutsourcingModel pegawai = pegawaiService.getPegawaiById(id);
		
		
//		List<RiwayatKerjaPegawaiModel> rKerja= riwayatService.getAllRiwayat();
//		List<RiwayatKerjaPegawaiModel>	rTemp = new ArrayList<RiwayatKerjaPegawaiModel>();
//	
//		for( RiwayatKerjaPegawaiModel riwayat : rKerja) {
//			if (riwayat.getPegawai_outsourcing_id().equals(pegawai)){
//				rTemp.add(riwayat);
//			}
//		}
	
	
		
		//riwayatService.getAllRiwayat(nip);
		model.addAttribute("pegawai", pegawai);
//		model.addAttribute("riwayatPegawai", rTemp);
		return "DetailPegawai";
	}
	
	/*
	 * Ubah Pegawai 
	 */
	@RequestMapping(value="/pegawai/ubah/{id}" , method = RequestMethod.GET)
	private String ubahPegawai(@PathVariable(value = "id") long id, Model model) {
		PegawaiOutsourcingModel pegawaiLama = pegawaiService.getPegawaiById(id);
		List<ProdukModel> produkList = produkService.getAllProduk();
		List<ProdukModel> produkList2 = produkList.subList(1, produkList.size());
		//List<ProdukModel> produkAvail = produkList.get(1);
		//List<PelamarModel> pelamarList = pelamarService
		
		model.addAttribute("pegawai", pegawaiLama);
		model.addAttribute("produk", produkList2);
	
		
		
		
		
		return "UbahPegawai";
	
	}
	
	@RequestMapping(value="/pegawai/ubah/{id}", method = RequestMethod.POST)
    public String submitUbahPegawai(@PathVariable(value="id") long id, @ModelAttribute PegawaiOutsourcingModel pegawaiBaru, Model model) {	
		System.out.println(pegawaiBaru.getPkwt());
		pegawaiService.updatePegawai(id,pegawaiBaru);
		System.out.println(pegawaiBaru.getPelamar_id().getNama_lengkap());
		System.out.println(pegawaiBaru.getPelamar_id().getNama_panggilan());
		
		model.addAttribute("pegawai", pegawaiBaru);
		return "DetailPegawai";
    }
	
	
	@RequestMapping(value = "/pegawai-hapus", method = RequestMethod.POST)
	private String deletePegawai(@RequestParam("id") Long[] ids, Model model) {
		
		try {
			for(Long id : ids) {
				pegawaiService.deletePegawaiById(id);
				
			}
			return "ListPegawai";
		} catch(Exception e) {
			return null;
		}
		
	}
	
	@RequestMapping(value = "/pegawai-berhenti-assign", method = RequestMethod.POST)
	private String berhentiPegawai(@RequestParam("id") Long[] ids, Model model) {
//		List<RiwayatKerjaPegawaiModel> riwayatKerjaPegawai = new List();
		try {
		System.out.println("MASUKKKKKK");
			for(Long id : ids) {
				System.out.println(id);
				pegawaiService.updatePegawaiStatusById(id);
				
				
				/*
				 * Untuk nambah Riwayat Kerja
				 */
				
				RiwayatKerjaPegawaiModel rBaru = new RiwayatKerjaPegawaiModel();
				
				PegawaiOutsourcingModel temp = pegawaiService.getPegawaiById(id);
				
				rBaru.setPegawai_outsourcing_id(temp);
				rBaru.setProyek(temp.getProyek());
				rBaru.setProduk(temp.getProduk());
				rBaru.setJoin_date(temp.getJoin_date());
				rBaru.setEnd_date(temp.getEnd_date());
				
				
				riwayatService.addRiwayat(rBaru);
				
				
				//riwayatService.addRiwayat(id);
			}
			return "ListPegawai";
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}	

	@ModelAttribute("radio_gender")
	public String[] getRadioGenderValues() {
		return new String[] { "Laki-Laki", "Perempuan" };
	}

	@ModelAttribute("checkbox_produk")
	public String[] getProdukValues() {
		return new String[] { "Security", "Housekeeping", "Driver/Kurir", "Pekerja blabla" };
	}

	@ModelAttribute("radio_statusNikah")
	public String[] getStatusNikahValues() {
		return new String[] { "Belum Menikah", "Sudah Menikah" };
	}
	
	
}
