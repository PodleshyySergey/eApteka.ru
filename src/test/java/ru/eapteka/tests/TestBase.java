package ru.eapteka.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    public static void setUp() {
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://www.eapteka.ru/";
        Configuration.browser = "chrome";
    }

}
