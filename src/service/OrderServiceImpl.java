package service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dao.OrderDAO;
import dao.OrderItemDAO;
import pojo.Order;
import pojo.OrderItem;
import pojo.User;
import util.Page;

import javax.management.Query;

@Service
public class OrderServiceImpl  implements OrderService {
	@Autowired
	private  OrderDAO orderDAO;
	@Autowired
	private OrderItemDAO orderItemDao;

	@Override
	public List listByPage(Page page) {
		//String cond1="";
		// TODO Auto-generated method stub
		String hql=" FROM Order WHERE 1=1 ";
		/*
		 * if(cond1!=null || cond1.equals("")) { hql+= " and p1 like :p1"; }
		 */
//		List<Order> orders= orderDAO.findVO(hql, page.getStart()/page.getCount(), page.getCount());
//		for(Order order:orders){
//			hql="from OrderItem as o where o.order.id="+order.getId();
//			List<OrderItem> orderItems = orderItemDao.findVO(hql);
//	        order.setOrderItems(orderItems);
//	        float total = 0;
//	        int totalNumber = 0;
//	        for (OrderItem orderItem : orderItems) {
//	            total += orderItem.getNumber() * orderItem.getProduct().getPromotePrice();
//	            totalNumber += orderItem.getNumber();
//	            //productImageService.setFirstProductImage(orderItem.getProduct());
//	        }
//	        order.setTotal(total);
//	        order.setOrderItems(orderItems);
//	        order.setTotalNumber(totalNumber);
//		}

		String queryString = "select order from Order order";


		List<Order> orders = orderDAO.getOrders(queryString);

		return orders;
	}

	@Override
	public int total() {
		// TODO Auto-generated method stub
		Long count=orderDAO.findAllCount();	
		return count.intValue();
	}

	@Override
	public float createOrder(Order order, List<OrderItem> ois) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Order> listByUserWithoutDelete(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order delivery(Integer orderId) {
		// TODO Auto-generated method stub
		Order order=orderDAO.get(Order.class, orderId);
		order.setDeliveryDate(new Date());
		order.setStatus("waitConfirm");
		return order;
	}
}
