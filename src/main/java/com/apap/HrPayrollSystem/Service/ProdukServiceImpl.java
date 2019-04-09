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
	
}
