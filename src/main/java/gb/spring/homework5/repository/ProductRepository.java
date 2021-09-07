package gb.spring.homework5.repository;

import gb.spring.homework5.controller.CustomerController;
import gb.spring.homework5.model.Customer;
import gb.spring.homework5.model.Order;
import gb.spring.homework5.model.OrderDetail;
import gb.spring.homework5.model.Product;
import gb.spring.homework5.utils.QueryUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

@Log4j2
@Repository
public class ProductRepository {

    private SessionFactory factory;
    private OrdersRepository ordersRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Autowired
    public void setOrdersRepository(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addProduct(Product product) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            try {
                session.persist(product);
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }

        }
    }

    public List<Product> getProductsList() {
        List<Product> productList = null;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            productList = session.createNamedQuery("Product.getAll", Product.class)
                    .getResultList();
            session.getTransaction().commit();
        }

        return productList;
    }

    public Product getProduct(BigInteger id) {

        QueryUtil<Product, BigInteger> queryUtil = new QueryUtil<>();
        return queryUtil.getSingleResultById(factory, Product.class, id);
    }

    public void deleteProduct(BigInteger id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            try {
                Product p = session.get(Product.class, id);
                session.delete(p);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }

    }

    public void replaceProduct(Product product) {

        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            try {
                Product p = session.get(Product.class, product.getProductId());
                session.detach(p);
                session.saveOrUpdate(product);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }

    }

    public Set<Product> getProductsListByCustomerId(BigInteger id) {
        Set<Product> productSet = new CopyOnWriteArraySet<>();
        List<Order> orderList =ordersRepository.getCustomerOrders(id);
        for (Order order : orderList) {
            List<OrderDetail> orderDetailList = ordersRepository.getOrderDetails(order);
            for (OrderDetail detail : orderDetailList) {
                productSet.add(detail.getProduct());
            }
        }
        return productSet;
    }

    public Set<Product> getProductsListByCustomerName(String customerName) {
        Set<Product> productSet = new CopyOnWriteArraySet<>();
        productSet = getProductsListByCustomerId(customerRepository.getCustomer(customerName).getCustomerId());
        return productSet;
    }
}
