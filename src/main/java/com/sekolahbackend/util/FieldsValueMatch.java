package com.sekolahbackend.util;


import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({ ElementType.ANNOTATION_TYPE.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {
}
