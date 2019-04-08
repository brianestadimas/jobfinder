package com.apap.HrPayrollSystem.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
import com.apap.HrPayrollSystem.Service.PegawaiOutsourcingService;
import com.apap.HrPayrollSystem.Service.ProdukService;
import com.apap.HrPayrollSystem.Service.ProyekService;
import com.apap.HrPayrollSystem.Service.RiwayatKerjaPegawaiService;
import com.apap.HrPayrollSystem.Utility.AssignmentWrapper;

@Controller
public class PegawaiOutsourcingController {
	@Autowired
	private ProyekService proyekService;
	@Autowired
	private PegawaiOutsourcingService pegawaiService;
//	@Autowired
//	private RiwayatKerjaPegawaiService riwayatService;
	
	@RequestMapping("/pegawai")
	private String pegawai(Model model) {
		List<PegawaiOutsourcingModel> list = pegawaiService.getAllPegawai();
		model.addAttribute("listPegawai", list);
		
		return "ListPegawai";
	}
	
	@RequestMapping(value = "/pegawai-detail/{id}", method = RequestMethod.GET)
	private String detailPegawain(@PathVariable long id, Model model) {
		PegawaiOutsourcingModel pegawai = pegawaiService.getPegawaiById(id).get();
		//riwayatService.getAllRiwayat(nip)
		model.addAttribute("pegawai", pegawai);
		return "DetailPegawai";
	}
	
	/*
	 * Ubah Pegawai 
	 */
	@RequestMapping(value="/pegawai/ubah" , method = RequestMethod.GET)
	private String ubahPegawai(@PathVariable Long id, Model model) {
		PegawaiOutsourcingModel pegawai = pegawaiService.getPegawaiById(id).get();
		
		return "";
	
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

//	@RequestMapping(value = "/pegawai-berhenti-assign", method = RequestMethod.POST)
//	private String berhentiPegawai(@RequestParam("id") Long[] ids, Model model) {
//		
//		try {
//			System.out.println("MASUKKKKKK");
//			for(Long id : ids) {
//				System.out.println(id);
//				pegawaiService.updatePegawaiStatusById(id);
//				riwayatService.addRiwayat(id);
//			}
//			return "ListPegawai";
//		} catch(Exception e) {
//			System.out.println(e.getMessage());
//			return null;
//		}
//		
//	}

	@Autowired
	ProdukService produkService;
	
	//Assign Pegawai Get
	@RequestMapping(value = "/pegawai/assign", method = RequestMethod.GET)
	private String assignPegawai(long[] ids, Model model) {
		
		AssignmentWrapper daftar_pegawai = new AssignmentWrapper();
		List<ProdukModel> daftar_produk = produkService.getAllProduk();
		System.out.println(daftar_produk.get(0).getNama_produk());
		List<ProyekModel> daftar_proyek = proyekService.getAllProyek();
		System.out.println(daftar_proyek.get(0).getNama_proyek());
		
		List<String> nama_pegawai = new ArrayList<String>();
		ids = new long[2];
		ids[0] = (long) 1;
		ids[1] = (long) 2;
		
		daftar_pegawai.setListOfProyek(daftar_proyek);
		List<ProyekModel> listOfProyek = daftar_pegawai.getListOfProyek();
		
		System.out.println(daftar_pegawai.getListOfProyek().get(0).getNama_proyek());
		
		for(int i=0; i<ids.length; i++) {
			Optional<PegawaiOutsourcingModel> pegawai = pegawaiService.getPegawaiById(ids[i]);
			
			daftar_pegawai.add_pegawai(pegawai.get());
			nama_pegawai.add(pegawai.get().getPelamar_id().getNama_lengkap());
		}
		
		model.addAttribute("daftar_pegawai", daftar_pegawai);
		model.addAttribute("daftar_produk", daftar_produk);
		model.addAttribute("daftar_proyek", daftar_proyek);
		model.addAttribute("nama_pegawai", nama_pegawai);		
		return "form_assignment_pegawai";
	}
	
	//Assign Pegawai Post
	@RequestMapping(value="/pegawai/assign/submit", method=RequestMethod.POST)
	private String assignPegawaiSubmit(@ModelAttribute AssignmentWrapper daftar_pegawai, HttpServletRequest req, Model model) throws ParseException {
		String stringProyek = String.valueOf(req.getParameter("proyek"));
		ProyekModel proyek = proyekService.getProyekByName(stringProyek);
//		
//		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/mm/yyyy");
//		
//		String stringJoin_date = String.valueOf(req.getParameter("join_date"));
//		java.util.Date tempDate1 = sdf1.parse(stringJoin_date);
//		java.sql.Date join_date = new java.sql.Date(tempDate1.getTime());
//		
//		String stringEnd_date = String.valueOf(req.getParameter("end_date"));
//		java.util.Date tempDate2 = sdf1.parse(stringEnd_date);
//		java.sql.Date end_date = new java.sql.Date(tempDate2.getTime());
//		
//		for(int i=0; i<daftar_pegawai.getListOfPegawai().size(); i++) {
//			daftar_pegawai.getListOfPegawai().get(i).setProyek(proyek);
//			daftar_pegawai.getListOfPegawai().get(i).setJoin_date(join_date);;
//			daftar_pegawai.getListOfPegawai().get(i).setEnd_date(end_date);
//		}
//		
		pegawaiService.assignAll(daftar_pegawai.getListOfPegawai());
		
		return "DaftarPegawai";
	}
}
