package org.example.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private WebDriver driver;

    private By orderButton1 = By.cssSelector(".Header_Nav__AGCXC .Button_Button__ra12g");

    private By orderButton2 = By.cssSelector(".Button_Button__ra12g.Button_UltraBig__UU3Lp");

    private By nameField = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Имя']");

    private By surnameField = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Фамилия']");

    private By addressField = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Адрес: куда привезти заказ']");

    private By metroField = By.cssSelector(".select-search__input");

    private By phoneField = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Телефон: на него позвонит курьер']");

    private By continueButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text()='Далее']");

    private By dateField = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Когда привезти самокат']");

    private By timePeriodField = By.cssSelector(".Dropdown-control");

    private By colorOptionBlack = By.id("black");

    private By colorOptionGrey = By.id("grey");

    private By commentField = By.cssSelector(".Input_Input__1iN_Z[placeholder='Комментарий для курьера']");

    private By confirmOrderButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");

    private By yesButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text()='Да']");

    private By orderConfirmation = By.xpath(".//*[contains(@class, 'Order_ModalHeader__3FDaJ') and contains(text(), 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openSite() {
        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    public void scrollToOrderButton() {
        WebElement button = driver.findElement(orderButton2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
    }

    public void clickFirstOrderButton() {
        driver.findElement(orderButton1).click();
    }

    public void clickSecondOrderButton() {
        driver.findElement(orderButton2).click();
    }

    public void fillNameField(String name) {
        WebElement nameElement = driver.findElement(nameField);
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOf(nameElement));
        nameElement.sendKeys(name);
    }

    public void fillSurnameField(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void fillAddressField(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void fillMetroField(String metro) {
        driver.findElement(metroField).click();
        driver.findElement(metroField).sendKeys(metro);

        String metroOptionXPath = String.format(".//div[@class='select-search__select']//div[text()='%s']", metro);

        driver.findElement(By.xpath(metroOptionXPath)).click();
    }

    public void fillPhoneNumberField(String phoneNumber) {
        driver.findElement(phoneField).sendKeys(phoneNumber);
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    public void fillDateField(String date) {
        WebElement dateFieldElement = driver.findElement(dateField);
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOf(dateFieldElement));
        dateFieldElement.sendKeys(date);
        dateFieldElement.sendKeys(Keys.ENTER);
    }

    public void fillTimePeriodField(String timePeriod) {
        driver.findElement(timePeriodField).click();
        String optionXPath = String.format(".//div[@class='Dropdown-option' and text()='%s']", timePeriod);
        driver.findElement(By.xpath(optionXPath)).click();
    }

    public void selectColor(String color) {
        if (color.equals("чёрный жемчуг")) {
            driver.findElement(colorOptionBlack).click();
        } else if (color.equals("серая безысходность")) {
            driver.findElement(colorOptionGrey).click();
        } else {
            driver.findElement(colorOptionBlack).click();
            driver.findElement(colorOptionGrey).click();
        }
    }

    public void fillCommentField(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickConfirmationButton() {
        driver.findElement(confirmOrderButton).click();
    }

    public void clickYesButton() {
        WebElement yesButtonElement = driver.findElement(yesButton);
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOf(yesButtonElement));
        yesButtonElement.click();
    }

    public boolean isOrderConfirmationVisible() {
        WebElement orderConfirmationElement = driver.findElement(orderConfirmation);
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOf(orderConfirmationElement));
        return orderConfirmationElement.isDisplayed();
    }

    public void fillPersonalData(String name, String surname, String address, String metro, String phoneNumber) {
        fillNameField(name);
        fillSurnameField(surname);
        fillAddressField(address);
        fillMetroField(metro);
        fillPhoneNumberField(phoneNumber);
        clickContinueButton();
    }

    public void fillRentData(String date, String timePeriod, String color, String comment) {
        fillDateField(date);
        fillTimePeriodField(timePeriod);
        selectColor(color);
        fillCommentField(comment);
        clickConfirmationButton();
    }
}
