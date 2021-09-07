package gb.spring.homework5.repository;

import gb.spring.homework5.model.Customer;
import gb.spring.homework5.model.Order;
import gb.spring.homework5.model.OrderDetail;
import gb.spring.homework5.service.CustomerService;
import gb.spring.homework5.utils.QueryUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
@Repository
public class OrdersRepository {

    private SessionFactory factory;
    private CustomerService customerService;
    private Map<BigInteger, Order> preparedOrders;

    public OrdersRepository() {
        preparedOrders = new ConcurrentHashMap<>();
    }

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<Order> getOrders() {
        List<Order> orderList = null;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            try {
                orderList = session.createQuery("Select o from Order o", Order.class).getResultList();
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }

        }
        return orderList;
    }

    public Order getOrder(BigInteger orderId) {
        QueryUtil<Order, BigInteger> queryUtil = new QueryUtil<>();
        return queryUtil.getSingleResultById(factory, Order.class, orderId);
    }

    public List<Order> getCustomerOrders(BigInteger customerId) {
        List<Order> orderList = null;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            try {
                orderList = session.createQuery("Select o from Order o where o.customer.customerId=:id", Order.class)
                        .setParameter("id", customerId).getResultList();
                session.getTransaction().commit();

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }

        }


        return orderList;
    }

    public List<OrderDetail> getOrderDetails(Order order) {
        List<OrderDetail> orderDetailList = null;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            try {
                orderDetailList = order.getOrderDetailList();


                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }

        }
        return orderDetailList;
    }

    public void addOrder(BigInteger customerId, Order order) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            try {
                Customer customer = session.find(Customer.class, customerId);
                if (customer != null) {
                    System.out.println(order);
                    session.persist(order);
                    order.getOrderDetailList().forEach(e -> {
                        e.getOrderDetailID().setOrder_id(order.getOrder_id());
                        session.persist(e);
                    });
                }


                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }

        }
    }

    public Order prepareOrderToCustomer(BigInteger customerId) {
        Order order = new Order();
        order.setCustomer(customerService.getCustomer(customerId));
        order.setDate(new Date());
        preparedOrders.put(customerId, order);
        return order;
    }

    public void addOrderDetailByCustomerId(BigInteger customerId, OrderDetail orderDetail) {
        preparedOrders.get(customerId).getOrderDetailList().add(orderDetail);
    }

    public void resetPreparedOrderByCustomerId(BigInteger customerId) {
        preparedOrders.remove(customerId);
    }

    public Order getPreparedOrderByCustomerId(BigInteger customerId) {
        return preparedOrders.get(customerId);
    }
}
