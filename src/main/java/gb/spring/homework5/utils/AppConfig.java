package gb.spring.homework5.utils;

import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("gb.spring.homework5")
public class AppConfig {

    @Bean
    public SessionFactory sessionFactory(){
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

    }

}
