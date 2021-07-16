package com.wk.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Sparks
 * @Date: 2021/7/15 22:53
 * @Description
 */
/*
    Contains 是要校验的注解
    String 是要检验的值的类型
 */
public class MyValidate implements ConstraintValidator<Contains,String> {

    // private List<String> results = Arrays.asList("bj","nj","gz","sh");
    private List<String> results;
    private boolean required;
    @Override
    public void initialize(Contains constraintAnnotation) {
        required = constraintAnnotation.required();
        results = Arrays.asList(constraintAnnotation.addr());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required){
            return results.contains(value);
        }
        return false;
    }
}
