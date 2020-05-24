package dao;

import pojo.Order;

import java.util.List;

public interface OrderDAO extends BaseDao<Order> {
    @Override
    List<List> getListByHQL(final String HQL, final Integer startRow, final Integer pageSize);
    public List<Order> getOrders(String HQL);
}