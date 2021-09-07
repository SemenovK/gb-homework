package gb.spring.homework5.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class QueryUtil<T,B> {

    public  <T, B> T getSingleResultById(SessionFactory factory, Class clazz, B id) {
        T result = null;
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                result = (T) session.find(clazz, id);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public <T, B> void deleteById(SessionFactory factory, Class clazz, B id){
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(session.find(clazz, id));
                transaction.commit();

            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T,B> void add(SessionFactory factory, T item){
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(item);
                transaction.commit();

            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
