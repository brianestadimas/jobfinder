package com.apap.HrPayrollSystem.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.HrPayrollSystem.Model.KehadiranModel;
import com.apap.HrPayrollSystem.Service.KehadiranService;
import com.apap.HrPayrollSystem.Service.PegawaiOutsourcingService;

@Controller
public class KehadiranController {
	@Autowired
	private KehadiranService kehadiran_service;
	@Autowired
	private PegawaiOutsourcingService pegawai_outsourcing_service;
	
	@RequestMapping(value="/proyek/{proyekId}/kehadiran",method=RequestMethod.GET)
	private String daftarKehadiranProyek(@PathVariable(value="proyekId") long proyekId, Model model) {
		
		List<KehadiranModel> get_all_kehadiran = kehadiran_service.get_all_kehadiran();
		List<String> kehadiran_proyek_ini = new ArrayList<String>();
		
		for(int i = 0 ; i < get_all_kehadiran.size() ; i++) {
			if(get_all_kehadiran.get(i).getProyek().getId() == proyekId) {
				if(kehadiran_proyek_ini.isEmpty()) {
					kehadiran_proyek_ini.add(get_all_kehadiran.get(i).getJudul_kehadiran());
				}
				else {
					String judul_kehadiran = get_all_kehadiran.get(i).getJudul_kehadiran();
					if(!(kehadiran_proyek_ini.contains(judul_kehadiran))) {
						kehadiran_proyek_ini.add(get_all_kehadiran.get(i).getJudul_kehadiran());
					}
				}
			}
		}
		//TO DO render
		
		model.addAttribute("list_of_kehadiran",kehadiran_proyek_ini);
		return "list_kehadiran";
	}
	
	//ADD KEHADIRAN
	//iterasi semua pegawai yg ada di proyek itu NIP dan Nama Pegawainya
	//sebelahnya form kosong hadir, sakit, izin, alfa, libur, cuti, lain-lain
	
	
}
