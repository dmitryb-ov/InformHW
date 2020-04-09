package com.example.demo.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchValidation.class)
@Documented
public @interface FieldMatch {
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        FieldMatch[] value();
    }

    public String message() default "{constraints.fieldmatch}";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
    /**
     * @return The first field
     */
    public String first();
    /**
     * @return The second field
     */
    public String second();
}
