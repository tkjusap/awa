package com.funtap.awass.service;


import com.funtap.awass.Model.request.LoginModel;
import com.funtap.awass.Model.request.UserCreateDto;
import com.funtap.awass.Model.response.ApiResponse;
import com.funtap.awass.Model.response.UserAuthern;
import com.funtap.awass.jpaentity.User;
import com.funtap.awass.repository.UserRepository;
import com.funtap.awass.security.JwtService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class LoginService {
    private static final Logger logger = Logger.getLogger(LoginService.class);
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public ApiResponse loginService(LoginModel loginModel, HttpServletRequest httpReq) {
        logger.info("Login user: " + loginModel.getUsername());
        String username = loginModel.getUsername();
        String password = loginModel.getPassword();
        String result = "";
        Date startDate = new Date();
        String message = "";

        User user = userService.getUserByUsernamePassword(username, password);

        try {
            if (user != null) {
                int role = -1;
                message = "Đăng nhập thành công";
                result = jwtService.generateTokenLogin(user.getUsername());
                if (user.getRole().equals(User.ROLE_ADMIN)) {
                    role = 99;
                } else if (user.getRole().equals(User.ROLE_ADMIN)) {
                    role = 1;
                }  else if (user.getRole().equals(User.ROLE_USER)) {
                    role = 2;
                } else {
                    role = 0;
                }
                return ApiResponse.success(UserAuthern.of(user.getUsername(), result, role));
            } else {
                message = "Sai tài khoản hoặc mật khẩu";
                return ApiResponse.fail("Sai tài khoản hoặc mật khẩu");
            }
        } catch (Exception ex) {
            message = "ERROR";
            ex.printStackTrace();
            return ApiResponse.fail("ERROR");
        } finally {

        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public ApiResponse registerByUser(UserCreateDto userCreateDto, HttpServletRequest httpReq) throws Exception {
        logger.info("register user: " + userCreateDto.getUsername());
        if (userCreateDto.getUsername().length() > 30 || userCreateDto.getUsername().length() <= 3
                || userCreateDto.getPassword().length() < 3 || !userCreateDto.getUsername().matches("[a-zA-z0-9]+")) {
            throw new Exception("Độ dài tài khoản sai hoặc mật khẩu sai");
        }
        User userCheck = userRepository.findByUsername(userCreateDto.getUsername());
        if (userCheck != null) {
            throw new Exception("Tên tài khoản đã tồn tại");
        }
        User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userCreateDto.getPassword()));
        user.setPhone(userCreateDto.getPhone());

        userRepository.save(user);
        return ApiResponse.success("Tạo mới thành công");
    }


    @Transactional(rollbackFor = {Exception.class})
    public ApiResponse changePassword(String username, String password) throws Exception {
        User userLogin = jwtService.getUser();

        User user = userRepository.findByUsername(username);
        if(User.ROLE_ADMIN.equals(userLogin.getRole())){
            user.setPassword(bCryptPasswordEncoder.encode(password));
            userRepository.save(user);
        }
        return ApiResponse.success("thành công");
    }

}
