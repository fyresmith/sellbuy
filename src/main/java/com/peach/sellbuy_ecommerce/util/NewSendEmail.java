package com.peach.sellbuy_ecommerce.util;

import models.SendRequestMessageRouting;
import services.Courier;
import services.SendService;
import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import services.Courier;
import services.SendService;
import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;

public class NewSendEmail {
    public static void resetPassword(String recipient, String name, String code) {
        Courier.init("pk_prod_CGETQ73AP34556M879XS7QC7CTKB");

        SendEnhancedRequestBody request = new SendEnhancedRequestBody();
        SendRequestMessage message = new SendRequestMessage();

        HashMap<String, String> to = new HashMap<String, String>();
        to.put("email", recipient);
        message.setTo(to);
        message.setTemplate("YSBSQ2JFFVMR41NE3WDBS9EPWPCY");

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("recipientName", name);
        data.put("sixDigitCode", code);
        message.setData(data);

        request.setMessage(message);
        try {
            SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        resetPassword("me@calebmsmith.com", "Caleb Smith", "123456");
    }
}