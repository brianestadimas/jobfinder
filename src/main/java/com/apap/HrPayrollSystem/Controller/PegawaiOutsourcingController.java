package com.apap.HrPayrollSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Service.PegawaiOutsourcingService;
import com.apap.HrPayrollSystem.Service.ProyekService;
import com.apap.HrPayrollSystem.Service.RiwayatKerjaPegawaiService;

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
	
	//Assign Pegawai Get
	@RequestMapping(value = "/pegawai/assign", method = RequestMethod.GET)
	private String assignPegawai(@RequestParam("id") Long[] ids, Model model) {
		
		try {
			for(Long id : ids) {
				PegawaiOutsourcingModel pegawai = pegawaiService.getPegawaiById(id).get();
				if(pegawai.getStatus()) {
					pegawai.setProyek(null);
				}
				pegawaiService.updatePegawai(pegawai);
			}
			return "form_assignment_pegawai";
		} catch(Exception e) {
			return null;
		}
		
	}
}
