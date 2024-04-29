package db_objs;

import java.math.BigDecimal;
import java.sql.*;

public class MyJDBC {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/bankapp";
    private static final String DB_LOGIN = "root";
    private static final String DB_PASSWORD = "antplay228";
    public static User validateLogin(String login, String password) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE login = ? AND password = ?"

            );
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                int userId = resultSet.getInt("id");
                BigDecimal currentBalance = resultSet.getBigDecimal("current_Balance");
                return new User(userId,login,password,currentBalance);

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean register(String login, String password){
        try {
            if(!checkUser(login)){
                Connection connection = DriverManager.getConnection(DB_URL, DB_LOGIN,DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO users(login, password) " +
                                "VALUES(?, ?)"
                );

                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);

                preparedStatement.executeUpdate();
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private static boolean checkUser(String login){
        try {
            Connection conection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
            PreparedStatement preparedStatement = conection.prepareStatement(
                    "SELECT * FROM users WHERE login = ?"
            );

            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
