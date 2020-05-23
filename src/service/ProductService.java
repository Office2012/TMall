package service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pojo.Category;
import pojo.Product;
import util.Page;

public interface ProductService extends BaseService {
	Boolean delete(int id);
	Boolean update(Product product);
	List get(int id);
}
