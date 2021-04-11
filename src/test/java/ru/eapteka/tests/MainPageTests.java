package ru.eapteka.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static ru.eapteka.config.ConfigHelper.getWebUrl;
import static ru.eapteka.helper.DriverHelper.getConsoleLogs;

public class MainPageTests extends TestBase {

    @Test
    @DisplayName("Проверка отсутствия ошибок в консоли (SEVERE) при открытии главной странцы.")
    void consoleLogShouldNotHaveErrors() {
        step("Открытие главной страницы \"https://www.eapteka.ru/\"", () -> {
            open("https://www.eapteka.ru/");
        });

        step("Проверка лога консоли на отсутствие ошибок (SEVERE)", () -> {
            String consoleLogs = getConsoleLogs();
            assertThat(consoleLogs, not(containsString("SEVERE")));
        });
    }

    @Test
    void checkSectionCategory() {

        step("Открытие главной страницы \"https://www.eapteka.ru/\"", ()-> {
            open(getWebUrl());
        });
        step("Проверка отображения категории \"Лекарства\" на главное странице", ()-> {
            $("[data-scroll='xlg']").$(byText("Лекарства")).shouldBe(visible);
        });
        step("Проверка отображения категории \"Витамины и БАД\" на главное странице", ()-> {
            $("[data-scroll='xlg']").$(byText("Витамины и БАД")).shouldBe(visible);
        });
        step("Проверка отображения категории \"Красота\" на главное странице", ()-> {
            $("[data-scroll='xlg']").$(byText("Красота")).shouldBe(visible);
        });
        step("Проверка отображения категории \"Гигиена\" на главное странице", ()-> {
            $("[data-scroll='xlg']").$(byText("Гигиена")).shouldBe(visible);
        });
        step("Проверка отображения категории \"Линзы\" на главное странице", ()-> {
            $("[data-scroll='xlg']").$(byText("Линзы")).shouldBe(visible);
        });
        step("Проверка отображения категории \"Мать и дитя\" на главное странице", ()-> {
            $("[data-scroll='xlg']").$(byText("Мать и дитя")).shouldBe(visible);
        });
        step("Проверка отображения категории \"Медтовары\" на главное странице", ()-> {
            $("[data-scroll='xlg']").$(byText("Медтовары")).shouldBe(visible);
        });
        step("Проверка отображения категории \"Зоотовары\" на главное странице", ()-> {
            $("[data-scroll='xlg']").$(byText("Зоотовары")).shouldBe(visible);
        });
        step("Проверка отображения категории \"Медтехника\" на главное странице", ()-> {
            $("[data-scroll='xlg']").$(byText("Медтехника")).shouldBe(visible);
        });
    }

    @Test
    void searchTest() {
        step("Открытие главной страницы \"https://www.eapteka.ru/\"", ()-> {
            open(getWebUrl());
        });
        step("Ввод поисковоо запроса \"глицин\" и нажатие \"Enter\".", ()-> {
            $(byName("q")).val("глицин").pressEnter();
        });
        step("Проверка того, что количество результатов поиска больше нуля.", ()-> {
            $("[data-neon-alias='search_page']").$$(".cc-item").shouldHave(sizeGreaterThan(0));
        });
    }

}
