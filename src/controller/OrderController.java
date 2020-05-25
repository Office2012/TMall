package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;

import pojo.Order;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/listOrder.do", method = RequestMethod.GET)
    public String list(HttpServletRequest request) {
        List<Order> orders = orderService.listByPage(null);

        request.setAttribute("orders", orders);

        return "admin/order/listOrder";
    }

    @RequestMapping(value = "/deliveryOrder.do", method = RequestMethod.GET)
    public String delivery(Integer id) {
        orderService.delivery(id);
        return "forward:/order/listOrder.do";
    }

}
