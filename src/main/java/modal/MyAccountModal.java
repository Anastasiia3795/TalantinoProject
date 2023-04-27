package modal;

import SingUp.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountModal extends BasePage {

    private String urlName;
    @FindBy(xpath = "//*[@id='root']/div/div/div[1]/div[2]/h6")
    private WebElement changedSponsorName; //Name in the sponsor profile
    @FindBy(xpath = "//p[contains(text(), 'Balance')]/span")
    private WebElement kudosNumSpan; //Quantity of kudos in the sponsor profile

    //FieldsForCreditCard
    @FindBy(xpath = "//input[@name='cardNumber']")
    private WebElement cardNumField;
    @FindBy(xpath = "//input[@name='cardName']")
    private WebElement holderNameField;
    @FindBy(xpath = "//input[@name='expiryDate']")
    private WebElement dateField;
    @FindBy(xpath = "//input[@name='cvc']")
    private WebElement cvcField;
    @FindBy(xpath = "//input[@name='kudos']")
    private WebElement kudosField;
    @FindBy(xpath = "//button[contains(@class, ' MuiButton-containedPrimary')]")
    private WebElement addKudosButton;

    public MyAccountModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getUrl() {
        urlName = driver.getCurrentUrl();
        return urlName;
    }

    public WebElement getSponsorName() {
        return changedSponsorName;
    }
    public String getChangedSponsorName() {
        return changedSponsorName.getText();
    }

    public void addKudosForSponsor(String cardNum, String holderName, String date, String cvc, String kudos) {
        this.cardNumField.sendKeys(cardNum);
        this.holderNameField.sendKeys(holderName);
        this.dateField.sendKeys(date);
        this.cvcField.sendKeys(cvc);
        this.kudosField.sendKeys(kudos);
        addKudosButton.click();
    }

    public WebElement getKudosSpan() {return kudosNumSpan;}
    public String getKudosNum() {
        return kudosNumSpan.getText();
    }
}
