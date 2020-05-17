package service;

import java.util.List;

import pojo.Category;
import pojo.Product;
import util.Page;

public interface ProductService extends BaseService {
	Boolean delete(int id);
	Boolean update(Product product);
	List get(int id);
}
