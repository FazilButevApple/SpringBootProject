package tr.com.fazil.helper;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MaxSpacesValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxSpaces {
    String message() default "En fazla {value} boşluk içerebilir";
    int value() default 3;  // Varsayılan olarak 3 boşluk
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
