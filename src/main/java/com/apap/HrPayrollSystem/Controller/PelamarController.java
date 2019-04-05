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

import com.apap.HrPayrollSystem.Model.PelamarModel;
import com.apap.HrPayrollSystem.Model.PengalamanPelamarModel;
import com.apap.HrPayrollSystem.Service.PelamarService;
import com.apap.HrPayrollSystem.Service.PengalamanPelamarService;

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
		List<PengalamanPelamarModel> pengalaman = new ArrayList<PengalamanPelamarModel>(3);
		// Tambah attribute ke dalam model
		model.addAttribute("pelamar", pelamar);
		model.addAttribute("command", command);
		model.addAttribute("pengalaman", pengalaman);
		return "pelamar-daftar";
	}

	@RequestMapping(value = "pelamar/daftar", params = { "addEntry" }, method = RequestMethod.GET)
	private String addEntryPengalaman(Model model, @ModelAttribute FormCommand command) {
		// Add baris baru dalam pengalaman di form
		if (command.getPengalamanList().size() >= 3) {
			model.addAttribute("limit_msg", "Maksimal 3 pengalaman");
		} else {
			command.addPengalamanToList(new PengalamanPelamarModel());
		}
		model.addAttribute("command", command);
		return "pelamar-daftar";
	}

	@RequestMapping(value = "pelamar/daftar", params = { "deleteEntry" }, method = RequestMethod.POST)
	private String deleteEntryPengalaman(Model model, @ModelAttribute FormCommand command,
			HttpServletRequest deleteIndex) {

		if (command.getPengalamanList().size() == 1) {
			model.addAttribute("deleteLimit_msg", "Tidak bisa dihapus, minimum 1 entri pengalaman");
		} else {
			command.getPengalamanList().remove(
					(command.getPengalamanList().get(Integer.parseInt(deleteIndex.getParameter("deleteEntry")))));
		}
		model.addAttribute("command", command);
		return "jadwalJaga-add";

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
		pelamar.setProduk_dilamar(command.getProdukSelectedValues());
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

}
