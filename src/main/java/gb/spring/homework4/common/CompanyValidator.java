package gb.spring.homework4.common;

import gb.spring.homework4.annotations.Company;
import gb.spring.homework4.repository.CompanyDictionary;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompanyValidator implements ConstraintValidator<Company, String> {

    @Override
    public void initialize(Company constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return CompanyDictionary.INSTANCE.checkCompanyByName(s);
    }
}
