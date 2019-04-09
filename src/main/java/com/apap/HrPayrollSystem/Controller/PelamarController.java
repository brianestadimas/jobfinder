package com.apap.HrPayrollSystem.Controller;

import java.sql.Date;
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

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Model.PelamarModel;
import com.apap.HrPayrollSystem.Model.PengalamanPelamarModel;
import com.apap.HrPayrollSystem.Model.ProdukModel;
import com.apap.HrPayrollSystem.Model.ProyekModel;
import com.apap.HrPayrollSystem.Service.PegawaiOutsourcingService;
import com.apap.HrPayrollSystem.Service.PelamarService;
import com.apap.HrPayrollSystem.Service.PengalamanPelamarService;
import com.apap.HrPayrollSystem.Service.ProdukService;
import com.apap.HrPayrollSystem.Service.ProyekService;
import com.apap.HrPayrollSystem.Utility.AssignmentWrapper;

/**
 * Controller kelas Pelamar
 * 
 * @author Nathanael Lemuella
 * 
 */
@Controller
public class PelamarController {

	@Autowired
	PelamarService pelamarService;

	@Autowired
	PengalamanPelamarService pengalamanService;

	@Autowired
	private PegawaiOutsourcingService pegawaiService;
	/**
	 * Fitur pendaftaran pelamar : GET request
	 * 
	 * @param model Model
	 * @return Halaman HTML formulir pendaftaran pelamar
	 */
	@RequestMapping(value = "pelamar/daftar", method = RequestMethod.GET)
	private String daftarPelamar(Model model) {

		PelamarModel pelamar = new PelamarModel();
		FormCommand command = new FormCommand();
		command.addPengalamanToList(new PengalamanPelamarModel());
		// Tambah attribute ke dalam model
		model.addAttribute("pelamar", pelamar);
		model.addAttribute("command", command);
		return "pelamar-daftar";
	}

	@RequestMapping(value = "pelamar/daftar", params = { "addEntry" }, method = RequestMethod.POST)
	private String addEntryPengalaman(Model model, @ModelAttribute FormCommand command,
			@ModelAttribute PelamarModel pelamar) {
		// Add baris baru dalam pengalaman di form
		if (command.getPengalamanList().size() >= 3) {
			model.addAttribute("limit_msg", "Maksimal 3 pengalaman");
		} else {
			command.addPengalamanToList(new PengalamanPelamarModel());
		}
		model.addAttribute("command", command);
		model.addAttribute("pelamar", pelamar);
		return "pelamar-daftar";
	}

	@RequestMapping(value = "pelamar/daftar", params = { "deleteEntry" }, method = RequestMethod.POST)
	private String deleteEntryPengalaman(Model model, @ModelAttribute FormCommand command,
			@ModelAttribute PelamarModel pelamar, HttpServletRequest deleteIndex) {

		if (command.getPengalamanList().size() == 1) {
			model.addAttribute("deleteLimit_msg", "Tidak bisa dihapus, minimum 1 entri pengalaman");
		} else {
			command.getPengalamanList().remove(
					(command.getPengalamanList().get(Integer.parseInt(deleteIndex.getParameter("deleteEntry")))));
		}
		model.addAttribute("command", command);
		model.addAttribute("pelamar", pelamar);
		return "pelamar-daftar";

	}

	/**
	 * Fitur pendaftaran pelamar : POST request
	 * 
	 * @param pelamar Model Pelamar yang sudah diisi
	 * @param command Hasil input RadioButton dan Checkbox
	 * @param model   Model
	 * @return Halaman HTML data pelamar
	 */
	@RequestMapping(value = "pelamar/daftar", params = { "submitPelamar" }, method = RequestMethod.POST)
	private String daftarPelamarPost(@ModelAttribute PelamarModel pelamar, FormCommand command, Model model) {
		pelamar.setGender(command.getGenderSelectedValue());
		pelamar.setStatus_marital(command.getStatusNikahSelectedValue());
		String[] produk_dilamar = command.getProdukSelectedValues();
		String produk_produk_dilamar = "";
		for(int i = 0 ; i < produk_dilamar.length ; i++) {
			produk_produk_dilamar += produk_dilamar[i];
		}
		pelamar.setProduk_dilamar(produk_produk_dilamar);
		pelamarService.addPelamar(pelamar);
		for (PengalamanPelamarModel pp : command.getPengalamanList()) {
			pengalamanService.addPengalaman(pp);
		}
		return "pelamar-view";
	}

	/**
	 * Fitur mengubah pelamar : GET request
	 * 
	 * @param id    id_pelamar
	 * @param model Model
	 * @return Halaman HTML formulir ubah pelamar
	 */
	@RequestMapping(value = "pelamar/ubah/{id}", method = RequestMethod.GET)
	private String ubahPelamar(@PathVariable(value = "id") long id, Model model) {

		PelamarModel arsip_pelamar = pelamarService.getPelamarById(id);
		// Tambah attribute ke dalam model
		model.addAttribute("pelamar", arsip_pelamar);
		return "pelamar-ubah";
	}

	/**
	 * Fitur mengubah pelamar : POST request
	 * 
	 * @param id      id_pelamar
	 * @param pelamar Pelamar yang sudah diubah
	 * @param model   Model
	 * @return Halaman HTML detail pelamar
	 */
	@RequestMapping(value = "pelamar/ubah/{id}", method = RequestMethod.POST)
	private String ubahPelamarPost(@PathVariable(value = "id") long id, PelamarModel pelamar, Model model) {
		pelamarService.updatePelamar(pelamar);
		String nama_pelamar = pelamar.getNama_lengkap();
		model.addAttribute("nama_pelamar", nama_pelamar);
		return "pelamar-detail";

	}

	/**
	 * Fitur menghapus pelamar
	 * 
	 * @param id
	 * @param model
	 * @return Halaman HTML pelamar
	 */
	@RequestMapping("/pelamar/delete/{id}")
	public String hapusPelamar(@PathVariable(value = "id") long id, Model model) {
		PelamarModel arsip_pelamar = pelamarService.getPelamarById(id);
		String nama_pelamar = arsip_pelamar.getNama_lengkap();
		pelamarService.deletePelamar(arsip_pelamar);

		model.addAttribute("nama_pelamar", nama_pelamar);
		return "pelamar-view";
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
	
	
	@Autowired
	ProdukService produkService;
	
	@Autowired
	ProyekService proyekService;
	
	//Assign Pelamar Get
	@RequestMapping(value = "/pelamar/assign", method = RequestMethod.GET)
	private String assignPelamar(long[] ids, Model model) {
		
		AssignmentWrapper wrapper = new AssignmentWrapper();
		List<ProdukModel> daftar_produk = produkService.getAllProduk();
		List<ProyekModel> daftar_proyek = proyekService.getAllProyek();
		
		wrapper.setDaftar_proyek(daftar_proyek);
		
		List<String> nama_pelamar = new ArrayList<String>();
		
		ids = new long[2];
		ids[0] = (long) 3;
		ids[1] = (long) 4;
		
		for(int i=0; i<ids.length; i++) {
			PelamarModel pelamar = pelamarService.getPelamarById(ids[i]);
			PegawaiOutsourcingModel pegawai = new PegawaiOutsourcingModel();
			pegawai.setPelamar_id(pelamar);
			
			wrapper.add_pegawai(pegawai);
			nama_pelamar.add(pelamar.getNama_lengkap());
			System.out.println(wrapper.getDaftar_pegawai().get(i).getPelamar_id().getNama_lengkap());
		}
		
		model.addAttribute("wrapper", wrapper);
		model.addAttribute("daftar_produk", daftar_produk);
		model.addAttribute("daftar_proyek", daftar_proyek);
		model.addAttribute("nama_pelamar", nama_pelamar);
		return "form_assignment_pelamar";
	}
	
	//Assign Pegawai Post
	@RequestMapping(value="/pelamar/assign/submit", method=RequestMethod.POST)
	private String assignPelamarSubmit(@ModelAttribute AssignmentWrapper daftar_pegawai, HttpServletRequest req, Model model) throws ParseException {
		String stringProyek = req.getParameter("proyek");
		Optional<ProyekModel> proyek = proyekService.getProyekById(Long.parseLong(stringProyek));
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
