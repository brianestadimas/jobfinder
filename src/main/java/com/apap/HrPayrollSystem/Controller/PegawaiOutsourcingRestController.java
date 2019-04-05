package com.apap.HrPayrollSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apap.HrPayrollSystem.Model.PegawaiOutsourcingModel;
import com.apap.HrPayrollSystem.Service.PegawaiOutsourcingService;
import com.apap.HrPayrollSystem.Service.ProyekService;

@RestController
public class PegawaiOutsourcingRestController {
	@Autowired
	private PegawaiOutsourcingService pegawaiService;
	
	 @RequestMapping(value = "/getPegawaiOutsourcing")
	 public List<PegawaiOutsourcingModel> getPegawaiOutsourcing() {

		return pegawaiService.getAllPegawai();
	}
}
