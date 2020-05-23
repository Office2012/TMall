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
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pojo.Category;
import pojo.Product;
import util.HibernateUtil;
import util.Page;

@Service
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {
	@Autowired
	private ProductDAO productDAO;
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
		productDAO.delete(id);

		return true;
	}

	@Override
	public Boolean update(Product product) {
		productDAO.update(product);
		
		return true;
	}




}
