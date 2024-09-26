package utilities;

import java.sql.*;

public class JDBCReusableMethods {

    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;




    public static void createConnection(){

        String URL= ConfigReader.getProperty("URL");
        String USERNAME = ConfigReader.getProperty("USERNAME");
        String PASSWORD = ConfigReader.getProperty("PASSWORD");

        try {
            DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }



    public static Connection getConnection(){

        String URL= ConfigReader.getProperty("URL");
        String USERNAME = ConfigReader.getProperty("USERNAME");
        String PASSWORD = ConfigReader.getProperty("PASSWORD");

        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

       return connection;
    }


    public static Statement getStatement(){

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;
    }

    public static ResultSet executeQuery(String Query){ //bu benim oluşturdugum method


        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            resultSet = statement.executeQuery(Query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return resultSet;
    }


    public static void closeConnection(){

        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }









}

