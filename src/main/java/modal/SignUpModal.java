package modal;

import SingUp.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpModal extends BasePage {

    @FindBy(xpath = "//*[@id='fName']")
    private WebElement firstNameField;
    @FindBy(xpath = "//*[@id='lName']")
    private WebElement lastNameField;
    @FindBy(xpath = "//*[@id='email']")
    private WebElement emailField;
    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//*[@id='cpassword']")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "//*[@id='kindOfTalent']")
    private WebElement kindOfTalentField;
    @FindBy(xpath = "//*[@id='root']/div/div/form/button")
    private WebElement signUpButton;

    public SignUpModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getSignUpButton() {return signUpButton; }
    public void clickSignUpButton() {
        signUpButton.click();
    }

    public void signUp(String firstName, String lastName, String email,
                       String password, String confirmPassword, String kindOfTalent) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmPassword);
        kindOfTalentField.sendKeys(kindOfTalent);
    }
}
