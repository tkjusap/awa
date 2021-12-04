package com.funtap.awass.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContent {
    private final String serverName = "localhost";
    private final String portNumber = "3306";
    private final String userID = "root";
    private final String passwd = "7122461311";
    private final String dbName = "awassdb";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://" + serverName + ":"  + portNumber +"/" + dbName  + "?useUnicode=true&characterEncoding=UTF-8";
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url,userID,passwd);
    }


}
