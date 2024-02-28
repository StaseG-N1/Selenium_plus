package example;



import example.pages.WebElementsPage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    public void webtest1() {
        assertTrue( webElementsPage.getMainMenu().isDisplayed(),"Верхнее меню сайта НЕ отображено на странице");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        webElementsPage.getSerchIcon().click();
        wait.until(ExpectedConditions.visibilityOf(webElementsPage.getSerchField()));
        webElementsPage.getSerchField().sendKeys("тестирование");
        webElementsPage.getSerchField().sendKeys(Keys.ENTER);
        assertTrue( webElementsPage.getPosts().size() > 0,"Список постов НЕ отображен на странице");
 }

    @AfterAll
    public static void close() {
        driver.quit(); }
}

