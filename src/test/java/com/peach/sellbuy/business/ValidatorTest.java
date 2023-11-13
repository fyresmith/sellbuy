package com.peach.sellbuy.business;

import com.peach.sellbuy.util.Validator;
import org.junit.jupiter.api.Test;

class ValidatorTest {

    @Test
    void isValidEmail() {
        System.out.println(Validator.isValidEmail("jaleeldh09@outlook.com"));
    }
}