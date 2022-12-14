package org.example.dao;

import org.example.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {



    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO user(id, name, password) VALUES (?,?,?)");
        ps.setString(1,user.getId());
        ps.setString(2,user.getName());
        ps.setString(3,user.getPassword());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public User get(String id) throws SQLException, ClassNotFoundException{
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setString(1,id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        User users = new User();
        users.setId(rs.getString("id"));
        users.setName(rs.getString("name"));
        users.setPassword(rs.getString("password"));

        ps.close();
        conn.close();
        rs.close();

        return users;
    }
}
