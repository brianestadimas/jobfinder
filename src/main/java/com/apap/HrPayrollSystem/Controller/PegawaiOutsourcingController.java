package com.apap.HrPayrollSystem.Controller;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.view.RedirectView;

import com.apap.HrPayrollSystem.Model.FeedbackModel;
import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.ProdukModel;
import com.apap.HrPayrollSystem.Model.ProyekModel;
import com.apap.HrPayrollSystem.Model.RiwayatKerjaPegawaiModel;
import com.apap.HrPayrollSystem.Service.FeedbackService;
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
	private RiwayatKerjaPegawaiService riwayatService;
	@Autowired
	private FeedbackService feedback_service;

	
	@RequestMapping("/pegawai")
	private String pegawai(Model model) {
		List<PegawaiOutsourcingModel> list = pegawaiService.getAllPegawai();
		model.addAttribute("listPegawai", list);
		
		
		return "ListPegawai";
	}
	
	@RequestMapping(value = "/pegawai-detail/{id}", method = RequestMethod.GET)
	private String detailPegawain(@PathVariable long id, Model model) {
		PegawaiOutsourcingModel pegawai = pegawaiService.getPegawaiById(id);
		Boolean expiredStatus= true;
		
		
		System.out.println("MASOOOOEEKKKKKK SINIII");
		System.out.println(pegawai.getJoin_date());
		
		
		List<RiwayatKerjaPegawaiModel> rKerja= riwayatService.getAllRiwayat();
		List<RiwayatKerjaPegawaiModel>	rTemp = new ArrayList<RiwayatKerjaPegawaiModel>();
	
		for( RiwayatKerjaPegawaiModel riwayat : rKerja) {
			if (riwayat.getPegawai_outsourcing_id().equals(pegawai)){
				rTemp.add(riwayat);
			}
		}
		Date date = new Date();
		long satuHari = 86400000;
		long hariKe14 = pegawai.getEnd_date().getTime() -  14*satuHari;
		System.out.println(hariKe14);
		System.out.println(pegawai.getEnd_date());
		System.out.println(date.getTime());
		System.out.println(date.getTime() - hariKe14);
		if(date.getTime() >  hariKe14) {
			expiredStatus=true; //kalau mendekati end date
		}else {
			expiredStatus=false; //kalau belum dekat end date
		}
		//get feedback
		List<FeedbackModel> list_feedback_pegawai = feedback_service.get_feedback_by_id_pegawai(id);
					
		
		//riwayatService.getAllRiwayat(nip);
		model.addAttribute("id", id);
		model.addAttribute("list_of_feedback", list_feedback_pegawai);
		model.addAttribute("expiredStatus", expiredStatus);
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("riwayatPegawai", rTemp);
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
    public RedirectView submitUbahPegawai(@PathVariable(value="id") long id, @ModelAttribute PegawaiOutsourcingModel pegawaiBaru, Model model) {	
		System.out.println(pegawaiBaru.getPkwt());
		pegawaiService.updatePegawai(id,pegawaiBaru);
		
		
		model.addAttribute("pegawai", pegawaiBaru);
		return new RedirectView("/pegawai-detail/"+id);

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
//				System.out.println("==SAVING RIWAYAT==");
//				System.out.println(rBaru.getEnd_date());
//				System.out.println(rBaru.getProduk().getNama_produk());
				riwayatService.addRiwayat(rBaru);
				
				
				//riwayatService.addRiwayat(id);
			}
			return "ListPegawai";
		} catch(Exception e) {
			System.out.println(e.getMessage());
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
	private String assignPegawai(@RequestParam("id") long[] ids, Model model) {
		AssignmentWrapper wrapper = new AssignmentWrapper();
		List<ProdukModel> daftar_produk = produkService.getAllProduk();
		List<ProyekModel> daftar_proyek = proyekService.getAllProyek();
		
		wrapper.setDaftar_proyek(daftar_proyek);
		List<String> nama_pegawai = new ArrayList<String>();	
		for(int i=0; i<ids.length; i++) {
			PegawaiOutsourcingModel pegawai = pegawaiService.getPegawaiById(ids[i]);
			wrapper.add_pegawai(pegawai);
			nama_pegawai.add(pegawai.getPelamar_id().getNama_lengkap());
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
//		System.out.println(stringProyek);
		java.sql.Date join_date = java.sql.Date.valueOf(req.getParameter("join_date"));
		java.sql.Date end_date = java.sql.Date.valueOf(req.getParameter("end_date"));
		boolean is_assigned = true;
		String name = "";
		for(int i=0; i<daftar_pegawai.getDaftar_pegawai().size(); i++) {
			daftar_pegawai.getDaftar_pegawai().get(i).setProyek(proyek.get());
			daftar_pegawai.getDaftar_pegawai().get(i).setJoin_date(join_date);
			daftar_pegawai.getDaftar_pegawai().get(i).setEnd_date(end_date);
			daftar_pegawai.getDaftar_pegawai().get(i).setStatus(is_assigned);
			name+= daftar_pegawai.getDaftar_pegawai().get(i).getPelamar_id().getNama_lengkap()+",";
		}
		
		pegawaiService.assignAll(daftar_pegawai.getDaftar_pegawai());
		
		List<PegawaiOutsourcingModel> list = pegawaiService.getAllPegawai();
		model.addAttribute("listPegawai", list);
		model.addAttribute("notifikasi_sukses","Berhasil Melakukan assignment terhadap pegawai dengan nama : " + name);
		return "ListPegawai";
	}
	
	//methods for feedback stuffs. find out how to add feedback(dumbshit)
	//this shitty cunt entah kenapa forbidden REEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
	@RequestMapping(value="/pegawai-detail/{id}/feedback/submit", method=RequestMethod.GET)
	private String feedbackSubmit(@PathVariable(value="id") long id, @ModelAttribute FeedbackModel feedback, Model model ) {
		feedback_service.save_feedback(feedback);
		
		return "redirect:/pegawai-detail/"+id;
	}
	
}
