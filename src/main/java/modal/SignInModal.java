package modal;

import SingUp.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInModal extends BasePage {

    @FindBy(xpath = "//*[@id='root']/div/div/form/p/a") //*[@id="root"]/header/div/p/button
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id='email']")
    private WebElement emailField;
    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='root']/div/div/form/button")
    private WebElement loginSubmit;

    @FindBy(id = "email-helper-text")
    private WebElement emailErrorMes;
    @FindBy(id = "password-helper-text")
    private WebElement passwordErrorMes;

    public SignInModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    public WebElement getLoginSubmit() {
        return loginSubmit;
    }

    public void clickLoginSubmit() {loginSubmit.submit();}

    public void logIn(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        clickLoginSubmit();
    }

    public String getUrlName() {
        return driver.getCurrentUrl();
    }

    public WebElement getEmailErrorMes() {
        return emailErrorMes;
    }

    public String getPasswordErrorMes() {
        return passwordErrorMes.getText();
    }


}
