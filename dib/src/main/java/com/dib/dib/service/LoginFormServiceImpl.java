package com.dib.dib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dib.dib.dao.LoginRepository;
import com.dib.dib.dto.LoginForm;
import com.dib.dib.model.Login;

@Component
public class LoginFormServiceImpl implements LoginFormService {
	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	public void registerNewAccount(LoginForm newAccount) {
		Login saveAccount = new Login();
		saveAccount.setUser(newAccount.getUser());
		saveAccount.setPassword(newAccount.getPassword());
		loginRepository.save(saveAccount);
	}
}
