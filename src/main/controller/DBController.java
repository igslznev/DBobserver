package main.controller;

import java.sql.*;
import java.util.Properties;

public class DBController {
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private final String URL = "jdbc:mysql://localhost:3306/InsuranceCompany";
    private Connection connection;

    public void connectToDB() throws SQLException {

            Properties properties=new Properties();
            properties.setProperty("user",USERNAME);
            properties.setProperty("password",PASSWORD);
        /*
          настройки указывающие о необходимости конвертировать данные из Unicode
	  в UTF-8, который используется в нашей таблице для хранения данных
        */
            properties.setProperty("useUnicode","true");
            properties.setProperty("characterEncoding","UTF-8");
            connection = DriverManager.getConnection(URL, properties);

    }

    public Connection getConnection() {
        return connection;
    }
}
