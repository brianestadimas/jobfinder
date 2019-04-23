package com.apap.HrPayrollSystem.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apap.HrPayrollSystem.Model.AccountModel;
import com.apap.HrPayrollSystem.Repository.AccountDb;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDb account_db;

	@Override
	public List<AccountModel> get_all_account() {
		// TODO Auto-generated method stub
		return account_db.findAll();
	}

	@Override
	public AccountModel get_account_by_id(long id) {
		// TODO Auto-generated method stub
		return account_db.findById(id).get();
	}

	@Override
	public void delete_account(AccountModel account) {
		// TODO Auto-generated method stub
		account_db.delete(account);
	}

	@Override
	public void save_account(AccountModel account) {
		// TODO Auto-generated method stub
		account_db.save(account);
	}

	@Override
	public AccountModel addAccount(AccountModel akun) {
		// TODO Auto-generated method stub
		String password = encrypt(akun.getPassword());
		akun.setPassword(password);
		return account_db.save(akun);
	}

	@Override
	public String encrypt(String password) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	@Override
	public boolean decrypt(String old_input, String old_get) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean hashed_password = passwordEncoder.matches(old_input,old_get);
		return hashed_password;
	}

	@Override
	public AccountModel findByUsername(String username) {
		// TODO Auto-generated method stub
		return account_db.findByUsername(username);
	}
	
	
}
