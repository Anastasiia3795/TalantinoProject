package SingUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    @FindBy(xpath = "//*[@id='root']/header/div/button")
    private WebElement loginButton;
    @FindBy(xpath = "//*[@id='root']/header/div/p")
    private WebElement userNameText;
    @FindBy(xpath = "//*[@id='root']/div/div/div[2]/div/div[5]/div/button/div[2]/h6")
    private WebElement talentProfileButton;

    @FindBy(xpath = "//*[@id='root']/header/div/p/button")
    private WebElement menuButton;
    @FindBy(xpath = "//*[@id='account-menu']/div[3]/ul/li[1]/a")
    private WebElement myProfileButton;

    @FindBy(xpath = "//a[contains(text(), 'Settings')]")
    private WebElement sponsorSettingsButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement isDisplayedLoginButton() {
        if (loginButton.isDisplayed()) {
            System.out.println("Login button is displayed");
            return loginButton;
        }
        else {
            System.out.println("Login button is not displayed");
            return null;
        }
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public WebElement getUserName() {
        return userNameText;
    }

    public WebElement getTalentProfileButton() {
        return talentProfileButton;
    }

    public void clickTalentProfileButton() {
        talentProfileButton.click();
    }

    public String getHomePageUrl() {
        return driver.getCurrentUrl();
    }

    public WebElement getMenuButton() {
        return menuButton;
    }

    public void clickMenuButton() {
        menuButton.click();
    }

    public WebElement getMyProfileButton() {
        return myProfileButton;
    }

    public void clickMyProfileButton() {
        myProfileButton.click();
    }

    public WebElement getSponsorSettingsButton() {
        return sponsorSettingsButton;
    }

    public void clickSponsorSettingsButton() {
        sponsorSettingsButton.click();
    }
}
