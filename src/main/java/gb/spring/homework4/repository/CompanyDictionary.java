package gb.spring.homework4.repository;

import gb.spring.homework4.model.Company;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CompanyDictionary {

    public static final CompanyDictionary  INSTANCE = new CompanyDictionary();
    private List<Company> companyList;

    public CompanyDictionary() {
        companyList = new CopyOnWriteArrayList<>();
        companyList.add(new Company("Company1"));
        companyList.add(new Company("Company2"));
        companyList.add(new Company("TestCompany"));
    }

    public boolean checkCompanyByName(String companyName) {
        Company c = companyList.stream().filter(company -> {
            return company.getCompanyName().equals(companyName);
        }).findFirst().orElse(null);

        return c == null ? false : true;
    }
}
