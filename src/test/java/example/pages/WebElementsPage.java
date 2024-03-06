package example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WebElementsPage {

    public WebDriver driver;

    public WebElementsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(how = How.XPATH, using = "//div[@class='tm-main-menu']")
    private WebElement mainMenu;

    @FindBy(how = How.XPATH, using = "//a[@href=\"/ru/search/\"]")
    private WebElement serchIcon;

    @FindBy(how = How.XPATH, using = "//form[@action=\"/ru/search/\"]//input")
    private WebElement serchField;

    @FindBy(how = How.XPATH, using = "//article")
    private List<WebElement> posts;


    public WebElement getMainMenu() {
        return this.mainMenu;
    }

    public WebElement getSerchIcon() {
        return serchIcon;
    }

    public WebElement getSerchField() {
        return serchField;
    }

    public List<WebElement> getPosts() {
        return posts;
    }

}
