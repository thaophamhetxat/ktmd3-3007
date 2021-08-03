package service;

import dao.SelectUser;
import module.UserPerson;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {
    public ArrayList<UserPerson> listUser = new ArrayList<>();

    public UserService() {
        try {
            listUser = (ArrayList<UserPerson>) SelectUser.seclect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserPerson> home() {
        return listUser;
    }
    public void save(UserPerson userPerson) {
        listUser.add(userPerson);
    }

    public void edit(UserPerson userPerson, int index) {
        listUser.set(index, userPerson);
    }

    public void delete(int index) {
        listUser.remove(index);
    }

}
