package dao;


import pojo.User;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

	@Override
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select count(*) from User";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

	@Override
    public void add(User bean) {

        String sql = "insert into user values(null ,? ,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

	@Override
    public void update(User bean) {
        

    }

	@Override
    public void delete(int id) {

      
    }
	
	@Override
    public User get(int id) {
       

        
        return null;
    }

	@Override
    public List<User> list() {

	    List<User> users = new ArrayList<>();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from User";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getNString("name"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

       return users;
    }

	@Override
    public List<User> list(int start, int count) {
       return null;
    }

	@Override
    public boolean isExist(String name) {
        User user = get(name);
        return user != null;

    }

	@Override
    public User get(String name) {
        return null;
    }

    public User get(String name, String password) {
       return null;
    }

}