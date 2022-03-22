package com.funtap.awass.repository;

import com.funtap.awass.jpaentity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndEnable(String username, int i);

    User findUserByUsernameAndEnable(String username, int enableActive);

    User findByUsername(String userName);
}
