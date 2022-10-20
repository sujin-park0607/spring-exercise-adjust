package org.example.dao;

import org.example.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    @Test
    void ConnectionTest() throws SQLException, ClassNotFoundException {
        UserDao dao = context.getBean("awsUserDao",UserDao.class);
        String id = "24";

        dao.add(new User(id,"SuJin","1111"));

        User selectedUser = dao.get(id);
        Assertions.assertEquals("SuJin",selectedUser.getName());
    }



}