package com.apap.HrPayrollSystem.Controller;

import java.time.Year;
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
import org.springframework.web.bind.annotation.RequestParam;

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
	ProyekService proyekService;
	@Autowired
	PelamarService pelamarService;
	@Autowired
	PengalamanPelamarService pengalamanService;
	@Autowired
	ProdukService produkService;
    @Autowired
	PegawaiOutsourcingService pegawaiService;
	
	/**
	 * Fitur pendaftaran pelamar : GET formulir pendaftaran
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

	/**
	 * Fitur pendaftaran pelamar : Tambah baris pengalaman
	 * 
	 * @param model   Model
	 * @param command Model pembungkus list pengalaman
	 * @param pelamar Model pelamar yang sudah diisi sementara
	 * @return Halaman HTML formulir pendaftaran pelamar
	 */
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

	/**
	 * Fitur pendaftaran pelamar : Hapus baris pengalaman
	 * 
	 * @param model       Model
	 * @param command     Model pembungkus list pengalaman
	 * @param pelamar     Model pelamar yang sudah diisi sementara
	 * @param deleteIndex Index dari baris yang akan dihapus
	 * @return Halaman HTML formulir pendaftaran pelamar
	 */
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
	 * Fitur pendaftaran pelamar : POST formulir pendaftaran
	 * 
	 * @param pelamar Model Pelamar yang sudah diisi
	 * @param command Model pembungkus list pengalaman dan checkbox
	 * @param model   Model
	 * @return Halaman HTML data pelamar
	 */
	@RequestMapping(value = "pelamar/daftar", params = { "submitPelamar" }, method = RequestMethod.POST)
	private String daftarPelamarPost(@ModelAttribute PelamarModel pelamar, @ModelAttribute FormCommand command,
			Model model) {
		String produkResult = "";
		for (String produk : command.getSelectedCheckboxProduk()) {
			produkResult += produk + ",";
		}

		System.out.println(produkResult.substring(0, produkResult.length() - 1));
		pelamar.setProduk_dilamar(produkResult.substring(0, produkResult.length() - 1));

		pelamarService.addPelamar(pelamar);
		for (PengalamanPelamarModel pp : command.getPengalamanList()) {
			pp.setPelamar_id(pelamar);
			pengalamanService.addPengalaman(pp);
		}
		List<PelamarModel> arsip_pelamar = pelamarService.getAllPelamar();
		model.addAttribute("daftarSukses_msg", "Pelamar " + pelamar.getNama_lengkap() + " sukses didaftarkan!");
		model.addAttribute("listPelamar", arsip_pelamar);
		return "pelamar-view";
	}

	/**
	 * Fitur melihat list pelamar : GET Request
	 * 
	 * @param model Model
	 * @return Halaman HTML list pelamar
	 */
	@RequestMapping(value = "pelamar/", method = RequestMethod.GET)
	private String getPelamar(Model model) {
		List<PelamarModel> arsip_pelamar = pelamarService.getAllPelamar();
		model.addAttribute("listPelamar", arsip_pelamar);
		return "pelamar-view";
	}

	@RequestMapping(value = "pelamar/detail/{id}", method = RequestMethod.GET)
	private String getPelamarDetail(@PathVariable(value = "id") long id, Model model) {
		PelamarModel arsip_pelamar = pelamarService.getPelamarById(id);
		model.addAttribute("pelamar", arsip_pelamar);
		return "pelamar-detail";
	}

	/**
	 * Fitur mengubah pelamar : GET formulir ubah pelamar
	 * 
	 * @param id    id_pelamar
	 * @param model Model
	 * @return Halaman HTML formulir ubah pelamar
	 */
	@RequestMapping(value = "pelamar/ubah/{id}", method = RequestMethod.GET)
	private String ubahPelamar(@PathVariable(value = "id") long id, Model model) {
		FormCommand command = new FormCommand();
		PelamarModel arsip_pelamar = pelamarService.getPelamarById(id);
		command.setSelectedCheckboxProduk(arsip_pelamar.getProduk_dilamar().split(","));
		command.setPengalamanList(pengalamanService.getAllPengalamanByPelamar(arsip_pelamar));

		// Tambah attribute ke dalam model
		model.addAttribute("command", command);
		model.addAttribute("pelamar", arsip_pelamar);
		return "pelamar-ubah";
	}

	/**
	 * Fitur mengubah pelamar : Tambah baris pengalaman
	 * 
	 * @param model   Model
	 * @param id      ID Pelamar
	 * @param command Model pembungkus list pengalaman dan checkbox
	 * @param pelamar Model Pelamar yang akan diubah
	 * @return Halaman HTML formulir ubah pelamar
	 */
	@RequestMapping(value = "pelamar/ubah/{id}", params = { "addEntryUbah" }, method = RequestMethod.POST)
	private String addEntryPengalamanUpdate(Model model, @PathVariable(value = "id") long id,
			@ModelAttribute FormCommand command, @ModelAttribute PelamarModel pelamar) {
		// Add baris baru dalam pengalaman di form
		if (command.getPengalamanList().size() >= 3) {
			model.addAttribute("limit_msg", "Maksimal 3 pengalaman");
		} else {
			command.addPengalamanToList(new PengalamanPelamarModel());
		}
		model.addAttribute("command", command);
		model.addAttribute("pelamar", pelamar);
		return "pelamar-ubah";
	}

	/**
	 * Fitur mengubah pelamar : Hapus baris pengalaman
	 * 
	 * @param model       Model
	 * @param id          ID Pelamar
	 * @param command     Model pembungkus list pengalaman dan checkbox
	 * @param pelamar     Model Pelamar yang akan diubah
	 * @param deleteIndex Index baris yang akan dihapus
	 * @return Halaman HTML formulir ubah pelamar
	 */
	@RequestMapping(value = "pelamar/ubah/{id}", params = { "deleteEntryUbah" }, method = RequestMethod.POST)
	private String deleteEntryPengalamanUpdate(Model model, @PathVariable(value = "id") long id,
			@ModelAttribute FormCommand command, @ModelAttribute PelamarModel pelamar, HttpServletRequest deleteIndex) {

		if (command.getPengalamanList().size() == 1) {
			model.addAttribute("deleteLimit_msg", "Tidak bisa dihapus, minimum 1 entri pengalaman");
		} else {
			PengalamanPelamarModel pengalaman_to_delete = command.getPengalamanList()
					.get(Integer.parseInt(deleteIndex.getParameter("deleteEntryUbah")));
			PengalamanPelamarModel arsip_pengalaman = pengalamanService.getPengalamanById(pengalaman_to_delete.getId());
			pengalamanService.deletePengalaman(arsip_pengalaman);
			command.getPengalamanList().remove(pengalaman_to_delete);
		}
		model.addAttribute("command", command);
		model.addAttribute("pelamar", pelamar);
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
	@RequestMapping(value = "pelamar/ubah/{id}", params = { "submitPelamarUbah" }, method = RequestMethod.POST)
	private String ubahPelamarPost(@PathVariable(value = "id") long id, @ModelAttribute PelamarModel pelamar,
			@ModelAttribute FormCommand command, Model model) {
		System.out.println(pelamar.getNama_lengkap());
		System.out.println(command.getSelectedCheckboxProduk());
		String produkResult = "";
		for (String produk : command.getSelectedCheckboxProduk()) {
			produkResult += produk + ",";
		}
		pelamar.setProduk_dilamar(produkResult);
		pelamarService.updatePelamar(pelamar);
		String nama_pelamar = pelamar.getNama_lengkap();
		model.addAttribute("pelamar",pelamar);
		model.addAttribute("suksesUbah_msg", "Pelamar " + nama_pelamar + " berhasil diubah!");
		return "pelamar-detail";
	}

	@RequestMapping(value = "/pelamar/hapus", method = RequestMethod.POST)
	private String deletePelamar(@RequestParam("id") Long[] ids, Model model) {
		List<PengalamanPelamarModel> arsip_pengalaman = pengalamanService.getAllPengalaman();
		if (ids.length == 0) {
			model.addAttribute("deleteError_msg", "Centang Pelamar yang akan dihapus terlebih dahulu!");
		} else {

			for (Long id : ids) {
				PelamarModel arsip_pelamar = pelamarService.getPelamarById(id);
				for (PengalamanPelamarModel pengalaman : arsip_pengalaman) {
					if (pengalaman.getPelamar_id().equals(arsip_pelamar)) {
						pengalamanService.deletePengalaman(pengalaman);
					}
				}
				pelamarService.deletePelamar(arsip_pelamar);
			}
		}
		return "pelamar-view";
	}

	@ModelAttribute("radio_gender")
	public String[] getRadioGenderValues() {
		return new String[] { "Laki-Laki", "Perempuan" };
	}

	@ModelAttribute("checkbox_produk")
	public String[] getProdukValues() {
		return produkService.getAllProdukName();
	}

	@ModelAttribute("radio_statusNikah")
	public String[] getStatusNikahValues() {
		return new String[] { "Belum Menikah", "Sudah Menikah" };
	}

	@ModelAttribute("list_tahunBekerja")
	public String[] getTahunBekerjaValues() {
		String[] values = new String[21];
		for (int i = 0; i <= 20; i++) {
			values[i] = Integer.toString(i);
		}
		return values;
	}

	@ModelAttribute("list_bulanBekerja")
	public String[] getBulanBekerjaValues() {
		String[] values = new String[13];
		for (int i = 0; i <= 12; i++) {
			values[i] = Integer.toString(i);
		}
		return values;
	}

	@ModelAttribute("list_tahun")
	public List<String> getTahunValues() {
		int minYear = Year.now().getValue() - 60;
		int maxYear = Year.now().getValue() + 10;
		List<String> values = new ArrayList<String>();
		for (int i = minYear; i <= maxYear; i++) {
			values.add(Integer.toString(i));
		}
		return values;
	}

	
	//Assign Pelamar Get
	@RequestMapping(value = "/pelamar/assign", method = RequestMethod.GET)
	private String assignPelamar(long[] ids, Model model) {
		
		AssignmentWrapper wrapper = new AssignmentWrapper();
		List<ProdukModel> daftar_produk = produkService.getAllProduk();
		List<ProyekModel> daftar_proyek = proyekService.getAllProyek();
		
		wrapper.setDaftar_proyek(daftar_proyek);
		
		List<String> nama_pelamar = new ArrayList<String>();
		
		ids = new long[1];
		ids[0] = (long) 1;

		
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