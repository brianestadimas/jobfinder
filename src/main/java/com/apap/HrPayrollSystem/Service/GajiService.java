package com.apap.HrPayrollSystem.Service;

import java.util.List;

import com.apap.HrPayrollSystem.Model.GajiModel;

public interface GajiService {
	void save_all_gaji(List<GajiModel> list_penggajian);
	void save_gaji(GajiModel gaji);
}
