package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pojo.Category;
import pojo.Product;
import service.ProductService;
import service.ProductServiceImpl;
import util.Page;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProductAction extends ActionSupport {
	private Product product;
	private Page page;
	private Integer start;
	private Integer id;

	@Autowired
	private ProductService productService;

    //产品列表
	public String list() {
        if (start == null) {
            page = new Page(0,10);
        } else {
        	page= new Page(start, 10);
        }
        int total=productService.total();      
        page.setTotal(total);
    	List<Product> products= productService.listByPage(page);
    	Map<String,Object> request=(Map)ActionContext.getContext().get("request");
    	request.put("products", products);


        return "listProduct";
    }

    public String add() {
    	//TODO 
        return "";
    }

    //删除商品
    public String delete() {
		productService.delete((int)id);
        return "success";
    }
    
    //编辑
    public String edit() {

    	List list= productService.get(id);
    	Product product=(Product)list.get(0);
    	List<Category> categories=(List<Category>)list.get(1);
    	Map<String,Object> request=(Map)ActionContext.getContext().get("request");
    	request.put("product", product);
    	request.put("categories", categories);
    	return "editProduct";
    }
    
    //更新编辑后的商品
    public String update() {
    	productService.update(product);
        return "success";
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

}
