package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.Card;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditGate {
    private final SelenideElement cardNumberField = $(byText("Номер карты")).parent().$("[class=\"input__control\"]");
    private final SelenideElement monthField = $(byText("Месяц")).parent().$("[class=\"input__control\"]");
    private final SelenideElement yearField = $(byText("Год")).parent().$("[class=\"input__control\"]");
    private final SelenideElement cardHolderField = $(byText("Владелец")).parent().$("[class=\"input__control\"]");
    private final SelenideElement cvvField = $(byText("CVC/CVV")).parent().$("[class=\"input__control\"]");
    private final SelenideElement approvedOperation = $(byText("Операция одобрена Банком.")).parent().$("[class=\"notification__content\"]");
    private final SelenideElement failureOperation = $(byText("Ошибка! Банк отказал в проведении операции.")).parent().$("[class=\"notification__content\"]");
    private final SelenideElement wrongFormatError = $(byText("Неверный формат"));
    private final ElementsCollection wrongFormat4Error = $$(byText("Неверный формат"));
    private final SelenideElement cardExpirationDateError = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpiredError = $(byText("Истёк срок действия карты"));
    private final SelenideElement requiredFieldError = $(byText("Поле обязательно для заполнения"));

    private final SelenideElement cancelField = $$("[class=\"icon-button__text\"]").first();
    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

    public CreditGate() {
        SelenideElement heading = $$("h3").find(exactText("Кредит по данным карты"));
        heading.shouldBe(visible);
    }

    public void inputData(Card card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        cardHolderField.setValue(card.getCardHolder());
        cvvField.setValue(card.getCvv());
        continueButton.click();
    }

    public void waitNotificationApproved() {
        approvedOperation.shouldBe(visible, Condition.text("Операция одобрена Банком"));
        cancelField.click();
    }

    public void waitNotificationFailure() {
        failureOperation.shouldBe(visible, Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    public void waitNotificationWrongFormat() {
        wrongFormatError.shouldBe(visible, Condition.text("Неверный формат"));
    }

    public void waitNotificationExpirationDateError() {
        cardExpirationDateError.shouldBe(visible, Condition.text("Неверно указан срок действия карты"));
    }

    public void waitNotificationExpiredError() {
        cardExpiredError.shouldBe(visible, Condition.text("Истёк срок действия карты"));
    }

    public void waitNotificationWrongFormat4Fields() {
        wrongFormat4Error.should();
        requiredFieldError.shouldBe(visible, Condition.text("Поле обязательно для заполнения"));
    }
}

