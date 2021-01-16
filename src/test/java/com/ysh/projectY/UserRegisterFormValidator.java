package com.ysh.projectY;

import com.ysh.projectY.entity.User;
import com.ysh.projectY.service.UserDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRegisterFormValidator {

    @Autowired
    UserDetailService userDetailService;
//    private static Validator validator;

//    @BeforeAll
//    public static void setupValidator() {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = (Validator) factory.getValidator();
//    }

    @Test
    public void validatorTest() {
        User user = new User();
        user.setUsername("XPP");
        user.setPassword("12345678");
        user.setMobilePhone("6049929377");
        userDetailService.addUser(user);
//        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
//        for (ConstraintViolation<User> constraintViolation : constraintViolations) {
//            System.out.println(constraintViolation.getMessage());
//        }

    }
}
