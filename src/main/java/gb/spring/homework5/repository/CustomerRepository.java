package gb.spring.homework5.repository;

import gb.spring.homework5.model.Customer;
import gb.spring.homework5.utils.QueryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;


@Repository
public class CustomerRepository {

    private SessionFactory factory;

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public Customer getCustomer(BigInteger id) {
        QueryUtil<Customer, BigInteger> queryUtil = new QueryUtil<>();
        return queryUtil.getSingleResultById(factory, Customer.class, id);
    }

    public Customer getCustomer(String customerName) {
        Customer customer = null;
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                customer = session.createQuery("Select c from Customer c where c.name like :customerName", Customer.class)
                        .setParameter("customerName", customerName.trim() + "%")
                        .getResultList()
                        .stream()
                        .findFirst()
                        .orElse(null);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public List<Customer> getCustomers() {
        List<Customer> customerList = null;
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                customerList = session.createQuery("Select c from Customer c", Customer.class).getResultList();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public void deleteCustomer(BigInteger id) {
        QueryUtil<Customer, BigInteger> queryUtil = new QueryUtil<>();
        queryUtil.deleteById(factory, Customer.class, id);
    }

    public void addCustomer(Customer customer) {

        QueryUtil<Customer, BigInteger> queryUtil = new QueryUtil<>();
        queryUtil.add(factory, Customer.class);
    }
}
