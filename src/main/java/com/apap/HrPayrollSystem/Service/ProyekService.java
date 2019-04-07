package com.apap.HrPayrollSystem.Service;

import java.util.List;
import java.util.Optional;
import com.apap.HrPayrollSystem.Model.ProyekModel;

public interface ProyekService {
	Optional<ProyekModel> getProyekById(Long id);
	
	List<ProyekModel> getAllProyek();
	
	//Add
	void addProyek(ProyekModel proyekBaru);
	
	//Update
	void updateProyek(Long id, ProyekModel proyekBaru);
	
	//Delete
	void deleteById(Long id);
	
	ProyekModel getProyekByName(String namaProyek);
}
