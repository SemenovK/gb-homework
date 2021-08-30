package gb.spring.homework5.service;

import gb.spring.homework5.repository.CompanyDictionary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyDictionary companyDictionary;

    public boolean checkCompanyByName(String companyName) {
        return companyDictionary.checkCompanyByName(companyName);
    }
}
