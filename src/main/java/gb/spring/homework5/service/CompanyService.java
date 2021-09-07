package gb.spring.homework5.service;

import gb.spring.homework5.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public boolean checkCompanyByName(String companyName) {
        return companyRepository.checkCompanyByName(companyName);
    }
}
