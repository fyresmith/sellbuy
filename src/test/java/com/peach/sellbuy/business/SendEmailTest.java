package com.peach.sellbuy.business;

import com.peach.sellbuy.util.SendEmail;
import org.junit.jupiter.api.Test;

class SendEmailTest {

    @Test
    void resetPassword() {
        SendEmail.resetPassword(new String[]{"jaleeldh09@outlook.com"}, "This was a test email lol. I guess it works.");
    }
}