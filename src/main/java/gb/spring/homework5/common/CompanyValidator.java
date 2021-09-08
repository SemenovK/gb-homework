package gb.spring.homework5.common;

import gb.spring.homework5.annotations.Company;
import gb.spring.homework5.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
@NoArgsConstructor
public class CompanyValidator implements ConstraintValidator<Company, String> {

    private CompanyService companyService;

    @Autowired
    public CompanyValidator(CompanyService companyService) {
        this.companyService = companyService;

    }

    @Override
    public void initialize(Company constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return companyService.checkCompanyByName(s);
    }
}
