package service;

import pojo.Order;
import pojo.OrderItem;
import pojo.User;
import util.Page;

import java.util.List;

public interface OrderService {
	public List listByPage(Page page);
	Order delivery(Integer orderId);
    public int total();
    public float createOrder(Order order, List<OrderItem> ois);
    public List<Order> listByUserWithoutDelete(User user);
}
