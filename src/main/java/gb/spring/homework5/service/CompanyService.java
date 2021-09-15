package gb.spring.homework5.service;

import gb.spring.homework5.model.Company;
import gb.spring.homework5.model.dto.CompanyDto;
import gb.spring.homework5.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Data
public class CompanyService {
    private CompanyRepository companyRepository;

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public boolean checkCompanyByName(String companyName) {
        Company company = new Company(0, companyName);

        ExampleMatcher modelMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id");
        Example<Company> example = Example.of(company, modelMatcher);

        return companyRepository.exists(example);
    }

    public List<CompanyDto> getCompaniesList() {
        return companyRepository.findAll().stream()
                .map(CompanyDto::valueOf)
                .collect(Collectors.toList());
    }
}
