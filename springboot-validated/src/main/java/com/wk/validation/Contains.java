package com.wk.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.groups.Default;
import java.lang.annotation.*;

/**
 * @author: Sparks
 * @Date: 2021/7/15 22:54
 * @Description
 */
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MyValidate.class)
public @interface Contains {

    boolean required() default true;

    String message() default "message";

    Class<?>[] groups() default {Default.class};

    Class<? extends Payload>[] payload() default {};
}
