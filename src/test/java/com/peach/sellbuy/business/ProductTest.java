package com.peach.sellbuy.business;

import org.junit.jupiter.api.Test;

class ProductTest {
    @Test
    public void test() {
        Access<Product> access = new Access<>("product", "productID", Product.class);
        access.delete(10000109);
    }
}