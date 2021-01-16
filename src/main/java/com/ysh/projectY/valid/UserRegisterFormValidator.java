package com.ysh.projectY.valid;

import com.ysh.projectY.valid.impl.UserRegisterFormValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UserRegisterFormValidatorImpl.class})
public @interface UserRegisterFormValidator {
    /**
     * 校验的失败的时候返回的信息，由于这个注解被用于class，我们想返回具体的校验信息
     * 所以后面会通过buildConstraintViolationWithTemplate重写返回失败时具体哪些参数校验未通过
     */
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

