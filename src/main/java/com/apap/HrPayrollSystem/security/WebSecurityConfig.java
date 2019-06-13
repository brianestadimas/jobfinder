package com.apap.HrPayrollSystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/proyek").permitAll()
			.antMatchers("/proyek-hapus/**").hasAnyAuthority("busdev","manager","admin")
			.antMatchers("/proyek-tambah").hasAnyAuthority("busdev","manager","admin")
			.antMatchers("/proyek-ubah/**").hasAnyAuthority("busdev","manager","admin")
			.antMatchers("/proyek-pegawai/**").hasAnyAuthority("busdev","manager","admin")
			.antMatchers("/account/**").hasAnyAuthority("hr","manager","admin")
			.antMatchers("/proyek/**/kehadiran/**/tambah").hasAnyAuthority("hr","manager","admin")
			.antMatchers("/proyek/**/kehadiran/**/update").hasAnyAuthority("hr","manager","admin")
			.antMatchers("/proyek/**/kehadiran/**/hapus").hasAnyAuthority("hr","manager","admin")
			.antMatchers("/proyek/**/kehadiran/**/penggajian").hasAnyAuthority("hr","manager","admin")
			.antMatchers("/pegawai/ubah/").hasAnyAuthority("hr","manager","admin")
			.antMatchers("/pegawai-berhenti-assign").hasAnyAuthority("hr","manager","admin")
			.antMatchers("/pegawai-hapus").hasAnyAuthority("hr","manager","admin")
			.antMatchers("/pegawai/assign").hasAnyAuthority("hr","manager","admin")
			.antMatchers("/pegawai-detail/**/feedback/**").hasAnyAuthority("hr","manager","admin")
			.antMatchers("/pelamar/ubah/**").hasAnyAuthority("hr","manager","admin")
			.antMatchers("/pelamar/hapus/").hasAnyAuthority("hr","manager","admin")
			.antMatchers("/pelamar/assign/").hasAnyAuthority("hr","manager","admin")
			.antMatchers("/pelamar/daftar").hasAnyAuthority("hr","manager","admin","pelamar")
			.antMatchers("/produk/").hasAnyAuthority("busdev","manager","admin")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
			.permitAll();
	}

//	@Autowired
//	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication()
//			.passwordEncoder(encoder())
//			.withUser("admin").password(encoder().encode("admin"))
//			.roles("manager");
//		
//	}
	
	@Bean
	public BCryptPasswordEncoder encoder()	{
		return new BCryptPasswordEncoder();
	}
	
	
	@Autowired
	private UserDetailsService userDetailsService;
		
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	
}
