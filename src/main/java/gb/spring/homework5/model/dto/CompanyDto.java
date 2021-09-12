package gb.spring.homework5.model.dto;

import gb.spring.homework5.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private int id;
    private String title;

    public static CompanyDto valueOf(Company company){
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(company.getId());
        companyDto.setTitle(company.getCompanyName());
        return companyDto;
    }
}
