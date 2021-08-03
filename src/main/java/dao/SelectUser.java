package dao;

import module.UserPerson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectUser {
    static String select = "select *from users";

    public static List<UserPerson> seclect() throws SQLException, ClassNotFoundException {
        ArrayList<UserPerson> list = new ArrayList<>();

        Connection connection = ConnectMySQL.getConnect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(select);

        while (resultSet.next()) {
            int id = Integer.parseInt(resultSet.getString("idUsers"));
            String userName = resultSet.getString("userName");
            String passWord = resultSet.getString("passWord");
            String email = resultSet.getString("email");

            list.add(new UserPerson(id, userName, passWord, email));
        }
        return list;
    }

    private static final String INSERT_USERS_SQL = "insert into users" + "(userName,passWord,email)values" + "(?,?,?);";

    public void insertUser(UserPerson users) {
        System.out.println(INSERT_USERS_SQL);
        try (Connection connection = ConnectMySQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);){

            preparedStatement.setString(1,users.getUserName());
            preparedStatement.setString(2,users.getPassWord());
            preparedStatement.setString(3,users.getEmail());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

}
