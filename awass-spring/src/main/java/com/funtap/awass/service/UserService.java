package com.funtap.awass.service;

import com.funtap.awass.jpaentity.User;
import com.funtap.awass.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    private static final Logger logger = Logger.getLogger(UserService.class);
    public User getUserByUsernamePassword(String username, String password) {
        logger.info("Đăng nhập "+ username +" | ");
        User user = userRepository.findUserByUsernameAndEnable(username, User.ENABLE_ACTIVE);
        if (user != null) {
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
