package com.peach.sellbuy_ecommerce.business;

import com.peach.sellbuy_ecommerce.util.SendEmail;
import org.junit.jupiter.api.Test;

class SendEmailTest {

    @Test
    void resetPassword() {
        SendEmail.resetPassword(new String[]{"jaleeldh09@outlook.com"}, "This was a test email lol. I guess it works.");
    }
}