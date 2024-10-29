package com.joaoguedes.jotagearmazem.utils.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    private Connection connection;

    private final String HOST = "localhost";
    private final int PORT = 3306;
    private final String DATABASE = "jotage_armazem_db";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false",
                USERNAME,
                PASSWORD);
    }

    public boolean isConnected() { return connection != null; }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
