package service;

import java.util.ArrayList;
import java.util.List;

import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import dao.ProductDAO;
import dao.ProductDAOImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pojo.Category;
import pojo.Product;
import util.HibernateUtil;
import util.Page;

public class ProductServiceImpl extends BaseServiceImpl implements ProductService {
	private ProductDAO productDAO=new ProductDAOImpl();  
	private CategoryDAO categoryDAO=new CategoryDAOImpl(); 
	
	@Override
	public List listByPage(Page page) {
		List products= productDAO.list(page.getStart(), page.getCount());
		return products;
	}

	@Override
	public int total() {
	   int total=0;
	   total=(int) productDAO.getTotal(0);
	   return total;
	}
	
	public List get(int id){
		List list=new ArrayList<>();
		Product product=productDAO.get(id);
		List categories=categoryDAO.list();
		list.add(product);
		list.add(categories);
		return list;
	}

	@Override
	public Boolean delete(int id) {
		//此处根据id使用hql语句查询到指定的product 然后将其delete
		Session session = HibernateUtil.getSession();

		String a = "SELECT p from Product as p where id = ?1";
		Query query = session.createQuery(a);
		query.setParameter(1,id);

		Product product = (Product)query.uniqueResult();

		Transaction transaction = session.beginTransaction();

		session.delete(product);
		transaction.commit();

		session.close();

		return true;
	}

	@Override
	public Boolean update(Product product) {
		//此处获取session 直接save即可
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();

		session.saveOrUpdate(product);
		transaction.commit();
		session.close();
		
		return true;
	}




}
