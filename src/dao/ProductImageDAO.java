package dao;

import java.util.List;

import pojo.Product;
import pojo.ProductImage;

public interface ProductImageDAO {

	String type_single = "type_single";
	String type_detail = "type_detail";

	int getTotal();

	void add(ProductImage bean);

	void update(ProductImage bean);

	void delete(int id);

	ProductImage get(int id);

	List<ProductImage> list(Product p, String type);

	List<ProductImage> list(Product p, String type, int start, int count);

}