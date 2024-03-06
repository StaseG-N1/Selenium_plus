package example;

import example.pages.WebElementsPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebTest {

    public static WebElementsPage webElementsPage;
    public static WebDriver driver;

    @BeforeAll
    public static void open() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        webElementsPage = new WebElementsPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfProperties.getProperty("homepage"));
    }

    @Test
    @Description(value = "Тест проверяет открытие страницы и поиск на сайте")
    public void webtest1() {
        step1();
        step2("тестирование");
    }

    @Step("Проверка открытия главной страницы сайта")
    public void step1() {
        assertTrue(webElementsPage.getMainMenu().isDisplayed(), "Верхнее меню сайта НЕ отображено на странице");
        addScreenshot();
    }

    @Step("Проверка поска на сайте по ключевому слову '{text}'")
    public void step2(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        webElementsPage.getSerchIcon().click();
        wait.until(ExpectedConditions.visibilityOf(webElementsPage.getSerchField()));
        webElementsPage.getSerchField().sendKeys(text);
        webElementsPage.getSerchField().sendKeys(Keys.ENTER);
        assertTrue(webElementsPage.getPosts().size() > 0, "Список постов НЕ отображен на странице");
        addScreenshot();
    }

    @AfterAll
    public static void close() {
        driver.quit();
    }

    private void addScreenshot() {
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Allure.addAttachment("Screenshot", new FileInputStream(screenshotAs));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

