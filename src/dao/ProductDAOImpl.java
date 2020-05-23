package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pojo.Product;
import pojo.ProductImage;
import util.DBUtil;
import util.DateUtil;
import pojo.Category;
import dao.CategoryDAOImpl;

import javax.annotation.Resource;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
	public int getTotal(int cid) {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
        	String sql=null;
        	if(cid==0)    //不按类别
        		sql = "select count(*) from Product where 1=1 " ;
        	else 
        		sql = "select count(*) from Product where cid = " + cid;

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
	public void add(Product bean) {

        String sql = "insert into Product values(null,?,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, bean.getName());
            ps.setString(2, bean.getSubTitle());
            ps.setFloat(3, bean.getOriginalPrice());
            ps.setFloat(4, bean.getPromotePrice());
            ps.setInt(5, bean.getStock());
            ps.setInt(6, bean.getCategory().getId());
            ps.setTimestamp(7, DateUtil.d2t(bean.getCreateDate()));
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
	public void update(Product bean) {
        Session session = sessionFactory.getCurrentSession();

        session.update(bean);
    }

    @Override
	public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "SELECT p from Product as p where id = ?1";
        Query query = session.createQuery(queryString);
        query.setParameter(1,id);

        Product product = (Product)query.uniqueResult();

        session.delete(product);
    }

    @Override
	public Product get(int id) {
        Product bean = new Product();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select * from Product where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {

                String name = rs.getString("name");
                String subTitle = rs.getString("subTitle");
                float originalPrice = rs.getFloat("originalPrice");
                float promotePrice = rs.getFloat("promotePrice");
                int stock = rs.getInt("stock");
                int cid = rs.getInt("cid");
                Date createDate = DateUtil.t2d(rs.getTimestamp("createDate"));

                bean.setName(name);
                bean.setSubTitle(subTitle);
                bean.setOriginalPrice(originalPrice);
                bean.setPromotePrice(promotePrice);
                bean.setStock(stock);
                Category category = new CategoryDAOImpl().get(cid);
                bean.setCategory(category);
                bean.setCreateDate(createDate);
                bean.setId(id);
                setFirstProductImage(bean);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
    }

    @Override
	public List<Product> list(int cid) {
        return list(cid, 0, Short.MAX_VALUE);
    }

    @Override
	public List<Product> list(int cid, int start, int count) {
        List<Product> beans = new ArrayList<Product>();
        Category category = new CategoryDAOImpl().get(cid);
        String sql = "select * from Product where cid = ? order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, cid);
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product bean = new Product();
                int id = rs.getInt(1);
                String name = rs.getString("name");
                String subTitle = rs.getString("subTitle");
                float originalPrice = rs.getFloat("originalPrice");
                float promotePrice = rs.getFloat("promotePrice");
                int stock = rs.getInt("stock");
                Date createDate = DateUtil.t2d(rs.getTimestamp("createDate"));

                bean.setName(name);
                bean.setSubTitle(subTitle);
                bean.setOriginalPrice(originalPrice);
                bean.setPromotePrice(promotePrice);
                bean.setStock(stock);
                bean.setCreateDate(createDate);
                bean.setId(id);
                bean.setCategory(category);
                setFirstProductImage(bean);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    @Override
	public List<Product> list() {
        return list(0, Short.MAX_VALUE);
    }

    @Override
	public List<Product> list(int start, int count) {
        List<Product> beans = new ArrayList<Product>();

        String sql = "select * from Product limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product bean = new Product();
                int id = rs.getInt(1);
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                String subTitle = rs.getString("subTitle");
                float originalPrice = rs.getFloat("originalPrice");
                float promotePrice = rs.getFloat("promotePrice");
                int stock = rs.getInt("stock");
                Date createDate = DateUtil.t2d(rs.getTimestamp("createDate"));

                bean.setName(name);
                bean.setSubTitle(subTitle);
                bean.setOriginalPrice(originalPrice);
                bean.setPromotePrice(promotePrice);
                bean.setStock(stock);
                bean.setCreateDate(createDate);
                bean.setId(id);

                Category category = new CategoryDAOImpl().get(cid);
                bean.setCategory(category);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    @Override
	public void fill(List<Category> categories) {
        for (Category c : categories)
            fill(c);
    }

    @Override
	public void fill(Category c) {
        List<Product> ps = this.list(c.getId());
        c.setProducts(ps);
    }

    @Override
	public void fillByRow(List<Category> categories) {
        int productNumberEachRow = 8;
        for (Category c : categories) {
            List<Product> products = c.getProducts();
            List<List<Product>> productsByRow = new ArrayList<>();
            for (int i = 0; i < products.size(); i += productNumberEachRow) {
                int size = i + productNumberEachRow;
                size = size > products.size() ? products.size() : size;
                List<Product> productsOfEachRow = products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            c.setProductsByRow(productsByRow);
        }
    }

    @Override
	public void setFirstProductImage(Product p) {
        List<ProductImage> pis = new ProductImageDAOImpl().list(p, ProductImageDAO.type_single);
        if (!pis.isEmpty())
            p.setFirstProductImage(pis.get(0));
    }


    @Override
	public List<Product> search(String keyword, int start, int count) {
        List<Product> beans = new ArrayList<Product>();

        if (null == keyword || 0 == keyword.trim().length())
            return beans;
        String sql = "select * from Product where name like ? limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword.trim() + "%");
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product bean = new Product();
                int id = rs.getInt(1);
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                String subTitle = rs.getString("subTitle");
                float originalPrice = rs.getFloat("originalPrice");
                float promotePrice = rs.getFloat("promotePrice");
                int stock = rs.getInt("stock");
                Date createDate = DateUtil.t2d(rs.getTimestamp("createDate"));

                bean.setName(name);
                bean.setSubTitle(subTitle);
                bean.setOriginalPrice(originalPrice);
                bean.setPromotePrice(promotePrice);
                bean.setStock(stock);
                bean.setCreateDate(createDate);
                bean.setId(id);

                Category category = new CategoryDAOImpl().get(cid);
                bean.setCategory(category);
                setFirstProductImage(bean);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }
}