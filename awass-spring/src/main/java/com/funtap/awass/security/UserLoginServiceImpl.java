package com.funtap.awass.security;

import com.funtap.awass.jpaentity.User;
import com.funtap.awass.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserDetailsService {
	final static Logger logger = Logger.getLogger(UserLoginServiceImpl.class);
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Check user authern: "+ username);
		User user = userRepository.findByUsernameAndEnable(username,1);
		if (user == null) {
			throw new UsernameNotFoundException("Username is not found");
		}
		CustomUserSecurity customeUser = new CustomUserSecurity();
		customeUser.setUser(user);

		return customeUser;
	}

}
