package service;

import java.util.ArrayList;
import java.util.List;

import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import dao.ProductDAO;
import dao.ProductDAOImpl;
import pojo.Category;
import pojo.Product;
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
		// TODO Auto-generated method stub
	
		return true;
	}

	@Override
	public Boolean update(Product product) {
		// TODO Auto-generated method stub
		//productDAO.update(product);
		
		return true;
	}




}
