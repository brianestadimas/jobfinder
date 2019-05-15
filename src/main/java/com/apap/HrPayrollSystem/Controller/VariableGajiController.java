package com.apap.HrPayrollSystem.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.apap.HrPayrollSystem.Model.VariableGajiModel;
import com.apap.HrPayrollSystem.Service.VariableGajiService;

@Controller
public class VariableGajiController {

	@Autowired
	private VariableGajiService variableGajiService;
	
	//detail variable gaji
	@RequestMapping(value="/variable-gaji",method=RequestMethod.GET)
	private String detailVariableGaji(Model model) {
		
		//cek jika null maka
		if(variableGajiService.checkNull()) {
			return "form_inisiasi_variable_gaji";
		}
		VariableGajiModel variable_gaji = variableGajiService.getVariableGaji();
		model.addAttribute("variableGaji", variable_gaji);
		return "variable_gaji";
	}
	
	@RequestMapping(value="/variable-gaji/submit",method=RequestMethod.POST)
	private String submitVariableGaji( Model model, 
							HttpServletRequest req) {
		VariableGajiModel newVar = new VariableGajiModel();
		newVar.setBPJSK(Float.parseFloat(req.getParameter("BPJSK")));
		newVar.setBPJSTK(Float.parseFloat(req.getParameter("BPJSTK")));
		newVar.setPersenan_pph(Float.parseFloat(req.getParameter("persenan_pph")));
		newVar.setPTKP(Integer.parseInt(req.getParameter("PTKP")));
		variableGajiService.saveVariableGaji(newVar);
		return "redirect:/variable-gaji";
	}
	
	//update variable gaji
	@RequestMapping(value="/variable-gaji/update",method=RequestMethod.GET)
	private String updateVariableGaji(Model model) {
		VariableGajiModel variableGaji = variableGajiService.getVariableGaji();
		
		model.addAttribute("variableGaji", variableGaji);
		return "form_variable_gaji";
	}
	
	@RequestMapping(value="/variable-gaji/update",method=RequestMethod.POST)
	public RedirectView updateVariableGajiSubmit(Model model, 
												HttpServletRequest req) {
		VariableGajiModel newVar = new VariableGajiModel();
		newVar.setBPJSK(Float.parseFloat(req.getParameter("BPJSK")));
		newVar.setBPJSTK(Float.parseFloat(req.getParameter("BPJSTK")));
		newVar.setPersenan_pph(Float.parseFloat(req.getParameter("persenan_pph")));
		newVar.setPTKP(Integer.parseInt(req.getParameter("PTKP")));
		variableGajiService.updateVariableGaji(newVar);
		return new RedirectView("/variable-gaji");
	}
	
}
