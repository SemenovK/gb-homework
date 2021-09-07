package gb.spring.homework5.repository;

import gb.spring.homework5.model.Company;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
public class CompanyRepository {

    private SessionFactory factory;

    @Autowired
    public CompanyRepository(SessionFactory factory) {
        this.factory = factory;
    }


    public boolean checkCompanyByName(String companyName) {
        Boolean result = false;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            result = session.createNamedQuery("findByName", Company.class)
                    .setParameter("name", companyName)
                    .getResultList().size() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
