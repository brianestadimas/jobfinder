package com.apap.HrPayrollSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.HrPayrollSystem.Model.ProdukModel;
import com.apap.HrPayrollSystem.Repository.ProdukDb;

/**
 * Implementasi Service kelas Produk
 * 
 * @author Tim Pengembang SIMOS
 *
 */
@Service
@Transactional
public class ProdukServiceImpl implements ProdukService {

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
		String[] listNama_produk = new String[list_produk.size() - 1];
		// TODO Auto-generated method stub
		for (int i = 0; i < list_produk.size() - 1; i++) {
			String nama_produk = list_produk.get(i + 1).getNama_produk();
			listNama_produk[i] = nama_produk;
		}
		return listNama_produk;
	}
}