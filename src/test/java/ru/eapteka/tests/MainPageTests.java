package ru.eapteka.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static ru.eapteka.helper.DriverHelper.getConsoleLogs;

public class MainPageTests {

    @Test
    @DisplayName("Проверка отсутствия ошибок в консоли (SEVERE) при открытии главной странцы.")
    void consoleLogShouldNotHaveErrors() {
        step("ткрытие главной страницы \"https://www.eapteka.ru/\"", () -> open(""));

        step("Проверка лога консоли на отсутствие ошибок (SEVERE)", () -> {
            String consoleLogs = getConsoleLogs();
            assertThat(consoleLogs, not(containsString("SEVERE")));
        });
    }

}
