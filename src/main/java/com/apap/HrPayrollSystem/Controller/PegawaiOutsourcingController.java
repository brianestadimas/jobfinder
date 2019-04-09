package com.apap.HrPayrollSystem.Controller;

import java.sql.Date;
import java.text.ParseException;
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
	@Autowired
	private ProdukService produkService;
	@Autowired
	RiwayatKerjaPegawaiService riwayatService;
	
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
	@RequestMapping(value="/pegawai/ubah/{id}" , method = RequestMethod.GET)
	private String ubahPegawai(@PathVariable(value = "id") long id, Model model) {
		PegawaiOutsourcingModel pegawaiLama = pegawaiService.getPegawaiById(id).get();
		List<ProdukModel> produkList = produkService.getAllProduk();
		
		
		model.addAttribute("pegawai", pegawaiLama);
		model.addAttribute("produk", produkList);
		
		return "UbahPegawai";
	
	}
	
	@RequestMapping(value="/pegawai/ubah/{id}", method = RequestMethod.POST)
    public String submitUbahPegawai(@PathVariable(value="id") long id, PegawaiOutsourcingModel pegawaiBaru, Model model) {	
		pegawaiService.updatePegawai(id,pegawaiBaru);
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
	
	//Assign Pegawai Get
	@RequestMapping(value = "/pegawai/assign", method = RequestMethod.GET)
	private String assignPegawai(long[] ids, Model model) {
		
		AssignmentWrapper wrapper = new AssignmentWrapper();
		List<ProdukModel> daftar_produk = produkService.getAllProduk();
		List<ProyekModel> daftar_proyek = proyekService.getAllProyek();
		
		wrapper.setDaftar_proyek(daftar_proyek);
		
		List<String> nama_pegawai = new ArrayList<String>();
		ids = new long[2];
		ids[0] = (long) 1;
		ids[1] = (long) 2;
		
		for(int i=0; i<ids.length; i++) {
			Optional<PegawaiOutsourcingModel> pegawai = pegawaiService.getPegawaiById(ids[i]);
			
			wrapper.add_pegawai(pegawai.get());
			nama_pegawai.add(pegawai.get().getPelamar_id().getNama_lengkap());
			System.out.println(wrapper.getDaftar_pegawai().get(i).getPelamar_id().getNama_lengkap());
		}
		
		model.addAttribute("wrapper", wrapper);
		model.addAttribute("daftar_produk", daftar_produk);
		model.addAttribute("daftar_proyek", daftar_proyek);
		model.addAttribute("nama_pegawai", nama_pegawai);		
		return "form_assignment_pegawai";
	}
	
	//Assign Pegawai Post
	@RequestMapping(value="/pegawai/assign/submit", method=RequestMethod.POST)
	private String assignPegawaiSubmit(@ModelAttribute AssignmentWrapper daftar_pegawai, HttpServletRequest req, Model model) throws ParseException {
		String stringProyek = req.getParameter("proyek");
		Optional<ProyekModel> proyek = proyekService.getProyekById(Long.parseLong(stringProyek));
		System.out.println(stringProyek);
		Date join_date = Date.valueOf(req.getParameter("join_date"));
		Date end_date = Date.valueOf(req.getParameter("end_date"));
		
		for(int i=0; i<daftar_pegawai.getDaftar_pegawai().size(); i++) {
			daftar_pegawai.getDaftar_pegawai().get(i).setProyek(proyek.get());
			daftar_pegawai.getDaftar_pegawai().get(i).setJoin_date(join_date);;
			daftar_pegawai.getDaftar_pegawai().get(i).setEnd_date(end_date);
		}
		
		pegawaiService.assignAll(daftar_pegawai.getDaftar_pegawai());
		
		List<PegawaiOutsourcingModel> list = pegawaiService.getAllPegawai();
		model.addAttribute("listPegawai", list);
		
		return "ListPegawai";
	}
}
