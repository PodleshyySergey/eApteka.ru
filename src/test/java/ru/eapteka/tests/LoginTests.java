package ru.eapteka.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static ru.eapteka.config.ConfigHelper.getEaptekaMail;
import static ru.eapteka.config.ConfigHelper.getEaptekaPassword;

public class LoginTests extends TestBase {

    @Test
    public void successfulLogin() {
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
            $(byName("email")).setValue(getEaptekaMail());
            $(byName("password")).setValue(getEaptekaPassword());
        });
        step("Обращение к \"Войти\"", ()-> {
            $(byText("Войти")).click();
        });

        step("Проверка отображения ссылки для перехода в Личный кабинет", ()-> {
            $("[href=\"/personal/\"]").shouldHave(text("Личный кабинет"));
        });

        step("Выход из учетной записи", ()-> {
            $(byText("Выход")).click();
        });
    }

    @Test
    public void unSuccessfulLoginWithInvalidMail() {
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
            $(byName("email")).setValue("test@mail.ru");
            $(byName("password")).setValue(getEaptekaPassword());
        });
        step("Обращение к \"Войти\"", ()-> {
            $(byText("Войти")).click();
        });

        step("Проверка отображения сообщения валидатора \"Неверная почта или пароль\".", ()-> {
            $(byText("Неверная почта или пароль")).shouldBe(visible);
        });
    }

    @Test
    public void unSuccessfulLoginWithInvalidPassword() {
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
            $(byName("email")).setValue(getEaptekaMail());
            $(byName("password")).setValue("Password123");
        });
        step("Обращение к \"Войти\"", ()-> {
            $(byText("Войти")).click();
        });

        step("Проверка отображения сообщения валидатора \"Неверная почта или пароль\".", ()-> {
            $(byText("Неверная почта или пароль")).shouldBe(visible);
        });
    }
}
