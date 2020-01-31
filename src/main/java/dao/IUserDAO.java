package dao;

import entity.User;

public interface IUserDAO {

    void RegisterUser(User user);

    boolean  userVerification(String login, String password);

    int getUserId (String login);

}
