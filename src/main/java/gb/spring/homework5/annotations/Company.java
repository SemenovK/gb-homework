package gb.spring.homework5.annotations;

import gb.spring.homework5.common.CompanyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CompanyValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Company {
    String message() default "Incorrect Company!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
