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
import com.apap.HrPayrollSystem.Model.FeedbackModel;
import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.ProyekModel;
import com.apap.HrPayrollSystem.Service.AccountService;
import com.apap.HrPayrollSystem.Service.FeedbackService;
import com.apap.HrPayrollSystem.Service.KehadiranService;
import com.apap.HrPayrollSystem.Service.PegawaiOutsourcingService;
import com.apap.HrPayrollSystem.Service.ProyekService;
import com.apap.HrPayrollSystem.Utility.PegawaiProyekWrapper;
import com.apap.HrPayrollSystem.Utility.PerformaWrapper;

@Controller
public class ProyekController {
	@Autowired
	private ProyekService proyekService;
	@Autowired
	private PegawaiOutsourcingService pegawaiService;
	@Autowired
	private AccountService akun_service;
	@Autowired
	private KehadiranService kehadiranService;
	@Autowired
	private FeedbackService feedback_service;

	
	@RequestMapping("/proyek")
	private String proyek(Model model,HttpServletRequest req) {
		List<ProyekModel> list = proyekService.getAllProyek();
		model.addAttribute("listProyek", list);
		AccountModel user = akun_service.findByUsername(req.getRemoteUser());
		model.addAttribute("user", user);
		return "list_proyek";
	}
	
	//Detail Proyek
	
	@RequestMapping(value = "/proyek-detail/{id}", method = RequestMethod.GET)
	private String detailProyek(@PathVariable long id, Model model,HttpServletRequest req ) {
		ProyekModel proyek = proyekService.getProyekById(id).get();
		model.addAttribute("proyek", proyek);

		List<PegawaiOutsourcingModel> pegawaiOutsourcing = pegawaiService.getAllPegawai();
		List<PegawaiOutsourcingModel> pegawaiProyek = new ArrayList<PegawaiOutsourcingModel>();
		for (int i=0; i<pegawaiOutsourcing.size(); i++){
			if ((pegawaiOutsourcing.get(i)).getProyek() != null) {
				if ((pegawaiOutsourcing.get(i).getProyek().getId())==(proyek.getId()) && pegawaiOutsourcing.get(i).getStatus() == true ){
					pegawaiProyek.add(pegawaiOutsourcing.get(i));
				}
			}
		}
		AccountModel user = akun_service.findByUsername(req.getRemoteUser());
		model.addAttribute("user", user);

		model.addAttribute("listPegawai", pegawaiProyek);
		
		return "detail_proyek";
	}
	
	@RequestMapping(value = "/performa-proyek/{id}", method = RequestMethod.GET)
	private String performaProyek(@PathVariable long id, Model model) {
		// Performa dalam Proyek (4 Bulan Terakhir)
		ProyekModel proyek = proyekService.getProyekById(id).get();
		List<PerformaWrapper> kehadiranProyek = kehadiranService.get_all_kehadiran_by_proyek(proyek);
		double persentase = 0.0;
		if (kehadiranProyek.isEmpty()) {
			model.addAttribute("performaErr_Msg",
					"Performa belum bisa dihitung, tambahkan data kehadiran terlebih dahulu !");
		} else if (kehadiranProyek.size() == 1) {
			persentase = 100;
		} else {
			double kehadiranSebelum = kehadiranProyek.get(1).getTotalKehadiran();
			double kehadiranSesudah = kehadiranProyek.get(0).getTotalKehadiran();
			persentase = (kehadiranSesudah - kehadiranSebelum) / kehadiranSebelum * 100;
		}
		int persentasePerforma = (int) persentase;
		if (persentasePerforma == 0) {
			model.addAttribute("persentasePerforma", "Stabil");
			model.addAttribute("Meningkat", true);
		} else if (persentasePerforma > 0) {
			model.addAttribute("persentasePerforma", "Meningkat " + persentasePerforma + "%");
			model.addAttribute("Meningkat", true);
		} else {
			model.addAttribute("persentasePerforma", "Menurun " + persentasePerforma + "%");
			model.addAttribute("Meningkat", false);
		}
		int panjangKehadiran = kehadiranProyek.size();
		List<FeedbackModel> feedback_proyek =  new ArrayList<FeedbackModel>();
		for(int i = 0 ; i < feedback_service.get_all_feedback().size() ; i++) {
			for(int j = 0 ; j < kehadiranProyek.get(1).getKehadiranList().size() ; j++) {
				if(feedback_service.get_all_feedback().get(i).getPegawai_outsourcing().equals(kehadiranProyek.get(1).getKehadiranList().get(j).getPegawai_outsourcing())) {
					feedback_proyek.add(feedback_service.get_all_feedback().get(i));
				}
			}
		}
		model.addAttribute("feedback_proyek", feedback_proyek);
		model.addAttribute("panjangKehadiran", panjangKehadiran);
		model.addAttribute("proyek", proyek);
		model.addAttribute("kehadiranProyek", kehadiranProyek);
		return "performa_proyek";
	}

	// Delete Proyek

	/**
	 * Fitur Hapus Proyek
	 * 
	 * @param id    idProyek
	 * @param model Model
	 * @return Halaman HTML list_proyek
	 */	
	@RequestMapping(value = "/proyek-hapus/{id}", method = RequestMethod.GET)
	private String deleteProyek(@PathVariable(value = "id") long id, Model model) {

		List<PegawaiOutsourcingModel> pegawaiOutsourcing = pegawaiService.getAllPegawai();
		for (int i=0; i<pegawaiOutsourcing.size(); i++){
			if ((pegawaiOutsourcing.get(i)).getProyek() != null) {
				if ((pegawaiOutsourcing.get(i).getProyek().getId())==(id)){
					pegawaiOutsourcing.get(i).setProyek(null);
					pegawaiOutsourcing.get(i).setStatus(false);
				}
			}
		}
		
		proyekService.deleteById(id);
		return "redirect:/proyek";
	}
	
	//Add Proyek
	
	@RequestMapping(value = "/proyek-tambah", method = RequestMethod.GET)
	public String addProyekGET(Model model) {
		ProyekModel proyek = new ProyekModel();
		model.addAttribute("proyek", proyek);
		return "tambah_proyek";
	}
	
	@RequestMapping(value = "/proyek-tambah", method = RequestMethod.POST)
	public String addProyekPost(Model model, @ModelAttribute ProyekModel proyek,
			RedirectAttributes redir) {
		if(proyek.getEnd_date_kontrak().before(proyek.getStart_date_kontrak())) {
			redir.addFlashAttribute("fail_notif", "Tanggal mulai proyek harus lebih kecil dari tanggal berakhir proyek");
			return "redirect:/proyek-tambah";
		}
		proyekService.addProyek(proyek);
		model.addAttribute("sukses_menambahkan", "proyek dengan nama "+proyek.getNama_proyek()+"berhasil ditambahkan");
		return "redirect:/proyek";
	}

	
	
	//Ubah Proyek

	@RequestMapping(value = "/proyek-ubah/{id}", method = RequestMethod.GET)
	private String ubahProyekGET(@PathVariable(value = "id") long id, Model model) {
		ProyekModel proyekLama = proyekService.getProyekById(id).get();
		model.addAttribute("proyekLama", proyekLama);
		return "ubah_proyek";
	}
	
	@RequestMapping(value = "/proyek-ubah/{id}", method = RequestMethod.POST)
	private String ubahProyekPost(@PathVariable(value = "id") long id, ProyekModel proyek, Model model,
			RedirectAttributes redir) {

		proyekService.updateProyek(id, proyek);
		redir.addFlashAttribute("ubahSukses_msg", "Proyek " + proyek.getNama_proyek() + " berhasil diubah!");
		return "redirect:/proyek-detail/"+id;
	}

	//Ubah Pegawai Proyek
	
	@RequestMapping(value = "/proyek-pegawai/{id}", method = RequestMethod.GET)
	private String ubahPegawaiProyekGET(@PathVariable(value = "id") long id, Model model) {
		ProyekModel proyek = proyekService.getProyekById(id).get();
		model.addAttribute("proyek", proyek);

		List<PegawaiOutsourcingModel> pegawaiOutsourcing = pegawaiService.getAllPegawai();
		List<PegawaiOutsourcingModel> pegawaiProyek = new ArrayList<PegawaiOutsourcingModel>();
		for (int i=0; i<pegawaiOutsourcing.size(); i++){
			if ((pegawaiOutsourcing.get(i)).getProyek() != null) {
				if ((pegawaiOutsourcing.get(i).getProyek().getId())==(proyek.getId())){
					pegawaiProyek.add(pegawaiOutsourcing.get(i));
				}
			}
		}
		PegawaiProyekWrapper listPegawai= new PegawaiProyekWrapper();
		
		for (int i=0; i<pegawaiProyek.size(); i++) {
			listPegawai.addListPegawai(pegawaiProyek.get(i));
		}
		model.addAttribute("listPegawai", listPegawai);
		
		return "ubah_pegawai_proyek";
	}
	@RequestMapping(value = "/proyek-pegawai/{id}", method = RequestMethod.POST)
	private String ubahPegawaiProyekPost(@PathVariable(value = "id") long id, @ModelAttribute PegawaiProyekWrapper listPegawai, Model model) {
		ProyekModel proyek = proyekService.getProyekById(id).get();
		pegawaiService.save_all_pegawai_proyek(listPegawai.getListPegawai());
		
		List<PegawaiOutsourcingModel> pegawaiOutsourcing = pegawaiService.getAllPegawai();
		List<PegawaiOutsourcingModel> pegawaiProyek = new ArrayList<PegawaiOutsourcingModel>();
		for (int i=0; i<pegawaiOutsourcing.size(); i++){
			if ((pegawaiOutsourcing.get(i)).getProyek() != null) {
				if ((pegawaiOutsourcing.get(i).getProyek().getId())==(proyek.getId())){
					pegawaiProyek.add(pegawaiOutsourcing.get(i));
				}
			}
		}
		
		model.addAttribute("proyek", proyek);
		model.addAttribute("listPegawai", pegawaiProyek);
		return "redirect:/proyek-detail/"+id;
	}

}


















