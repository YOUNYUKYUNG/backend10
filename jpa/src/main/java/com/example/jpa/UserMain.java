package com.example.jpa;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class UserMain {
    //private static final Logger log = LoggerFactory.getLogger(UserMain.class);
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        //findUser()
        log.info("=====findUser()===");
        User findUser = userDAO.findUser(1l);
        log.info("Found user : {}",findUser.getName());

        User findUser2 = userDAO.findUser(1L);

        if(findUser == findUser2)
        log.info("findUser == findUser2");
        else
            log.info("findUser != findUser2");

//        UserDAO userDAO = new UserDAO();
//
//        User younUser = new User("kim","kim@exam.com");
//
//        userDAO.createUser(younUser);
//        log.info("Create user: " +younUser.getName());
//        log.info("User email: {}",younUser.getEmail());

    }
}
