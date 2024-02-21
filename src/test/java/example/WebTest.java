package example;

import example.pages.WebElementsPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;


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



 }


    @AfterAll
    public static void close() {
        driver.quit(); }



}

