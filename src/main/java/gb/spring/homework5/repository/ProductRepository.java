package gb.spring.homework5.repository;

import gb.spring.homework5.model.Product;
import gb.spring.homework5.service.DBConnectionService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Log4j2
@Repository
public class ProductRepository {

    private SessionFactory factory;

    @Autowired
    public void setDbConnectionService(DBConnectionService dbConnectionService) {
        factory = dbConnectionService.getFactory();
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

    public List<Product> getProduct(BigInteger id) {

        List<Product> result;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            //Здесь почемуто когда хотел получить всего лишь одну запись через getSingleResult  вылетала
            //java.lang.ClassCastException: class gb.spring.homework5.model.Product cannot be cast to class gb.spring.homework5.model.Product
            //хотя возвращалась одна запись - поэтому переписал на List
            result = session.createNamedQuery("Product.getById", Product.class)
                    .setParameter("id", id)
                    .getResultList();

            session.getTransaction().commit();

        }
        return result;
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

}
