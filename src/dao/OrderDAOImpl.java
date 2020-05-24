package dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import pojo.Order;

import java.util.List;

@Repository
public class OrderDAOImpl extends BaseDaoImpl<Order> implements OrderDAO {
    @Override
    public List<List> getListByHQL(String HQL, Integer startRow, Integer pageSize) {
        return super.getListByHQL(HQL, startRow, pageSize);
    }

    public List<Order> getOrders(String HQL) {
        return sessionFactory.getCurrentSession().createQuery(HQL).list();
    }
}
