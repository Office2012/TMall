package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.ProductDAO;
import pojo.Order;
import service.OrderService;
import util.Page;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
public class OrderAction extends ActionSupport {
	private Page page;
	private Integer id;
	@Autowired //自动装配（类型）
	private OrderService orderService;

    public String list() {
        if (page == null) {
            page = new Page(0,10);
        }
        int total=orderService.total();
        page.setTotal(total);
        List<Order> orders = orderService.listByPage(page);
        Map<String,Object> request=(Map)ActionContext.getContext().get("request");
    	request.put("orders", orders);
        return "listOrder";
    }
    public String delivery(){
    	orderService.delivery(id);
    	return "success";
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
    
}
