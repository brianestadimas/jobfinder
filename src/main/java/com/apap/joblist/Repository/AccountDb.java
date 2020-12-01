package com.apap.joblist.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.joblist.Model.AccountModel;

@Repository
public interface AccountDb extends JpaRepository<AccountModel, Long>{
	AccountModel findByUsername(String username);
}
