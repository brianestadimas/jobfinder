package com.apap.HrPayrollSystem.Service;

import java.util.List;

import com.apap.HrPayrollSystem.Model.AccountModel;

public interface AccountService {
	List<AccountModel> get_all_account();
	AccountModel get_account_by_id(long id);
	void delete_account(AccountModel account);
	void save_account(AccountModel account);
}
