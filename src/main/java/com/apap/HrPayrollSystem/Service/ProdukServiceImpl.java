package com.apap.HrPayrollSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.HrPayrollSystem.Model.ProdukModel;
import com.apap.HrPayrollSystem.Repository.ProdukDb;

@Service
@Transactional
public class ProdukServiceImpl implements ProdukService{

	@Autowired
	ProdukDb produkDb;
	
	@Override
	public List<ProdukModel> getAllProduk() {
		// TODO Auto-generated method stub
		return produkDb.findAll();
	}
	
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

	@Override
	public void saveProduk(ProdukModel produk) {
		// TODO Auto-generated method stub
		produkDb.save(produk);
	}

	@Override
	public ProdukModel getProdukById(long id) {
		// TODO Auto-generated method stub
		return produkDb.findById(id).get();
	}
	


}
