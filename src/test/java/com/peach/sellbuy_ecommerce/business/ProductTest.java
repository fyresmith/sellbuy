package com.peach.sellbuy_ecommerce.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    public void test() {
        Access<Product> access = new Access<>("product", "productID", Product.class);
        access.delete(10000109);
    }
}