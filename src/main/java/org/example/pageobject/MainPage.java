package org.example.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    //Локатор кнопки закрытия сообщения о куки
    private By cookieButton = By.className("App_CookieButton__3cvqF");

    //Локатор кнопок с вопросом
    private By questions = By.className("accordion__button");

    //Локатор ответов на вопросы
    private By answers = By.cssSelector(".accordion__panel p");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openSite() {
        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    public void scrollToFaq() {
        WebElement element = driver.findElements(questions).get(7);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void closeCookies() {
        driver.findElement(cookieButton).click();
    }

    public void clickTheQuestion(int index) {
        driver.findElements(questions).get(index).click();
    }

    public String getTheAnswer(int index) {
        WebElement answerElement = driver.findElements(answers).get(index);
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOf(answerElement));
        return answerElement.getText();
    }
}
