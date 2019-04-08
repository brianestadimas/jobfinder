package com.apap.HrPayrollSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.apap.HrPayrollSystem.Model.ProdukModel;
import com.apap.HrPayrollSystem.Repository.ProdukDb;

public class ProdukServiceImpl implements ProdukService {
	
	@Autowired
	ProdukDb produkDb;

	@Override
	public String[] getAllProdukName() {
		List<ProdukModel> list_produk = produkDb.findAll();
		String[] listNama_produk = new String[list_produk.size()];
		// TODO Auto-generated method stub
		for (int i = 0 ; i < list_produk.size(); i ++) {
			String nama_produk = list_produk.get(i).getNama_produk();
			listNama_produk[i]=nama_produk;
		}
		return listNama_produk ;
	}
	
	

}
