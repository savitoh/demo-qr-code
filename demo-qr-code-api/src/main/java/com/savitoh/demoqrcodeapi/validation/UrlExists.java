package com.savitoh.demoqrcodeapi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = UrlExistsValidator.class)
public @interface UrlExists {

    String message() default "Erro ao tentar acessar URL";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
