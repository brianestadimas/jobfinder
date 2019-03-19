package com.apap.HrPayrollSystem.Service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.HrPayrollSystem.Repository.AccountDb;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDb account_db;
	
	
}
