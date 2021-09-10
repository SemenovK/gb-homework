package gb.spring.homework5.repository;

import gb.spring.homework5.model.Company;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
