package com.dib.dib.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dib.dib.Constant.Constant;
import com.dib.dib.dao.LoginRepository;
import com.dib.dib.model.Login;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private LoginRepository logRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	Login user = new Login();
    	if(userName.equals(Constant.INITIAL_USER)) {
    		user.setUser(userName);
    		user.setPassword(Constant.INITIAL_PASSWORD);
    	} else {
    		List<Login> users = logRepository.findByUser(userName);
    		
    		if(users.size() > 0) {
    			user = users.get(0);
    		} else {
    			throw new UsernameNotFoundException("User not found");
    		}
    	}

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        String roleName = "ROLE_ADMIN";
        
            grantedAuthorities.add(new SimpleGrantedAuthority(roleName));
        return new org.springframework.security.core.userdetails.User(
                user.getUser(), user.getPassword(), grantedAuthorities);
    }

}