package com.apap.HrPayrollSystem.Service;

import java.util.List;

import com.apap.HrPayrollSystem.Model.ProdukModel;

public interface ProdukService {

	
	String [] getAllProdukName();

	List<ProdukModel> getAllProduk();
	void saveProduk(ProdukModel produk);
	ProdukModel getProdukById(long id);
	void  deleteProdukById(long id);
}
