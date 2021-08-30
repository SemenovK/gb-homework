package gb.spring.homework5.service;


import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class DBConnectionService {
    private static SessionFactory factory;

    public static final DBConnectionService INSTANCE = new DBConnectionService();

    public DBConnectionService(){
        init();
    }

    public void init() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        log.info("DB Connection Factory Initialised");

    }

    public static SessionFactory getFactory() {
        return factory;
    }

    public void close(){
        factory.close();
    }
}
