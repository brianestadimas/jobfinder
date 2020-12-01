package com.apap.joblist.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.joblist.Model.AccountModel;
import com.apap.joblist.Repository.AccountDb;

@Service
@Transactional
@Qualifier(value = "UserDetailServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AccountDb akun_db;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		AccountModel user = akun_db.findByUsername(username);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		// grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
		grantedAuthorities.add(new SimpleGrantedAuthority("Admin"));
		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

}
