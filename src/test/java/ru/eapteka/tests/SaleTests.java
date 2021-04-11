package ru.eapteka.tests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ru.eapteka.config.ConfigHelper.getEaptekaMail;
import static ru.eapteka.config.ConfigHelper.getEaptekaPassword;
import static io.qameta.allure.Allure.step;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SaleTests extends TestBase {

    @Test
    @Order(0)
    public void addProductInBasket() {
        step("Открытие главной страницы \"https://www.eapteka.ru/\"", ()-> {
            open("");
        });
        step("Обращение к \"Вход\"", ()-> {
            $(byText("Вход")).click();
        });
        step("Выбор \"Войти по почте\"", ()-> {
            $(byText("Войти по почте")).click();
        });
        step("Ввод почты и пароля", ()-> {
            $(byName("email")).sendKeys(getEaptekaMail());
            $(byName("password")).sendKeys(getEaptekaPassword());
        });
        step("Обращение к \"Войти\"", ()-> {
            $(".modal-content").$(byText("Войти")).click();
        });

        step("Ввод поисковоо запроса \"арбидлол\" и нажатие \"Enter\".", ()-> {
            $(byName("q")).val("арбидол").pressEnter();
        });
        step("Проверка того, что количество результатов поиска больше нуля.", ()-> {
            $("[data-neon-alias='search_page']").$$(".cc-item").shouldHave(sizeGreaterThan(0));
        });
        step("Добавление в конзину товара, первого из списка.", ()-> {
            $("[data-neon-alias='search_page']").$(".cc-item").$(byText("Купить")).click();
        });
        step("Переход на страницу \"Корзина\".", ()-> {
            $("[data-action='toCart']").click();
        });
        step("Проверка наличия в корзине одного товара.", ()-> {
            $(".sec-cart__table").$$(".commodity ").shouldHave(size(1));
        });
        step("Выход из учетной записи", ()-> {
            $(byText("Выход")).click();
        });
    }

    @Test
    @Order(1)
    public void removeProductFromBasket() {
        step("Открытие главной страницы \"https://www.eapteka.ru/\"", ()-> {
            open("");
        });
        step("Обращение к \"Вход\"", ()-> {
            $(byText("Вход")).click();
        });
        step("Выбор \"Войти по почте\"", ()-> {
            $(byText("Войти по почте")).click();
        });
        step("Ввод почты и пароля", ()-> {
            $(byName("email")).sendKeys(getEaptekaMail());
            $(byName("password")).sendKeys(getEaptekaPassword());
        });
        step("Обращение к \"Войти\"", ()-> {
            $(".modal-content").$(byText("Войти")).click();
        });
        step("Переход на страницу \"Корзина\".", ()-> {
            $("[data-action='toCart']").$(".sum").click();
        });
        step("Проверка наличия в корзине одного товара.", ()-> {
            $(".sec-cart__table").$$(".commodity ").shouldHave(size(1));
        });
        step("Обращение к кнопке \"Очистить конзину\".", ()-> {
            $(byText("Очистить корзину")).click();
        });
        step("Выбор \"Да\" в конфирмейшене на удаление", ()-> {
            $("#ConfirmationDelete").$(byText("Да")).click();
        });
        step("Выход из учетной записи", ()-> {
            $(byText("Выход")).click();
        });
    }

}
