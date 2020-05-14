package dao;

import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import pojo.User;
import java.util.List;

public class UserDAOImpl implements UserDAO {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

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
		String sql = "update user set password=? where id=?";
		try {
			connection = DBUtil.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, bean.getPassword());
			pstmt.setInt(2, bean.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, connection);
		}

	}

	@Override
	public void delete(int id) {

	}

	@Override
	public User get(int id) {
		User bean = null;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
			String sql = "select * from User where id = " + id;
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				bean = new User();
				String name = rs.getString("name");
				bean.setName(name);
				String password = rs.getString("password");
				bean.setPassword(password);
				bean.setId(id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public List<User> list() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM user WHERE 1=1 ";
		// 获取连接；创建PreparedSatemant对象
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery(); // 得到结果集
			// 遍历
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				// String password=rs.getString("password");
				User user = new User();
				user.setId(id);
				user.setName(name);
				// user.setPassword(password);
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