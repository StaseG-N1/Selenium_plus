package example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebElementsPage {

    public WebDriver driver;
    public WebElementsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }


    @FindBy(xpath = "//*[contains(@id, 'passp-field-login')]")
    private WebElement loginField;

        public void inputLogin(String login) {
        loginField.sendKeys(login); }


}
