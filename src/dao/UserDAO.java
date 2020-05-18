package dao;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pojo.User;

import java.util.List;

@Repository
public interface UserDAO {
    int getTotal();
    void add(User user);
    void update(User user);
    void delete(int id) ;
    User get(int id);
    List<User> list();
    List<User> list(int start, int count) ;
    boolean isExist(String name) ;
    User get(String name) ;
    User get(String name, String password) ;
}