package com.apap.HrPayrollSystem.Controller;

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
		VariableGajiModel variableGaji = variableGajiService.getVariableGaji();
		
		model.addAttribute("variableGaji", variableGaji);
		return "variable_gaji";
	}
	
	//update variable gaji
	@RequestMapping(value="/variable-gaji/update",method=RequestMethod.GET)
	private String updateVariableGaji(Model model) {
		VariableGajiModel variableGaji = variableGajiService.getVariableGaji();
		
		model.addAttribute("variableGaji", variableGaji);
		return "form_variable_gaji";
	}
	
	@RequestMapping(value="/variable-gaji/update",method=RequestMethod.POST)
	public RedirectView updateVariableGajiSubmit(@ModelAttribute VariableGajiModel newVarGaji, Model model) {
		variableGajiService.updateVariableGaji(newVarGaji);
		
		model.addAttribute("variableGaji", newVarGaji);
		return new RedirectView("/variable-gaji");
	}
	
}
