package com.example.demo.util;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidation implements ConstraintValidator<FieldMatch, Object> {
    private String errorMessage;
    private String firstFieldName;
    private String secondFieldName;
    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        errorMessage = constraintAnnotation.message();
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        try {
            final BeanWrapperImpl bean = new BeanWrapperImpl(value);
            final Object firstObj = bean.getPropertyValue(firstFieldName);
            final Object secondObj = bean.getPropertyValue(secondFieldName);
            boolean returnValue = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
            //If the validation failed
            if (!returnValue) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                //In the initialiaze method you get the errorMessage: constraintAnnotation.message();
                constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage).addPropertyNode(secondFieldName).addConstraintViolation();
            }
            return returnValue;
        } catch (final Exception ignore) {
            // ignore
        }
        return true;
    }
}
