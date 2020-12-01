package com.apap.joblist.Service;

import java.util.List;

import com.apap.joblist.Model.AccountModel;

public interface AccountService {
	List<AccountModel> get_all_account();
	AccountModel get_account_by_id(long id);
	void delete_account(AccountModel account);
	void save_account(AccountModel account);
	AccountModel addAccount(AccountModel akun);
	String encrypt(String password);
	boolean decrypt(String old_input, String old_get);
	AccountModel findByUsername(String username);
	void changePassword(String new_password, String username);

}
