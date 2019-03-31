package com.apap.HrPayrollSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.HrPayrollSystem.Model.PelamarModel;
import com.apap.HrPayrollSystem.Service.PelamarService;

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
		// Tambah attribute ke dalam model
		model.addAttribute("pelamar", pelamar);
		model.addAttribute("command", command);
		return "pelamar-daftar";
	}

	/**
	 * Fitur pendaftaran pelamar : POST request
	 * 
	 * @param pelamar Data pelamar hasil isian formulir
	 * @param model   Model
	 * @return Halaman HTML data pelamar
	 */
	@RequestMapping(value = "pelamar/daftar", method = RequestMethod.POST)
	private String daftarPelamarPost(@ModelAttribute PelamarModel pelamar, FormCommand command, Model model) {
		pelamar.setGender(command.getGenderSelectedValue());
		pelamar.setStatus_marital(command.getStatusNikahSelectedValue());
		pelamar.setProduk_dilamar(command.getProdukSelectedValues());
		pelamarService.addPelamar(pelamar);
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

}