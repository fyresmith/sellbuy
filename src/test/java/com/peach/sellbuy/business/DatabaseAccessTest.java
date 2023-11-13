package com.peach.sellbuy.business;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;

class DatabaseAccessTest {
    @Test
    public void test() {
        Dotenv dotenv = Dotenv.load();
        String database = dotenv.get("DATABASE");
        System.out.println(database);
    }

    @Test
    public void searchTest() {
        Access<Product> access = new Access<>(Product.class);
        System.out.println(access.search("electronic").size());
    }
}