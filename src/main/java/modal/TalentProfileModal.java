package modal;

import SingUp.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TalentProfileModal extends BasePage {

    @FindBy(xpath = "//../header/div/button[5]")
    private WebElement closeButton;
    @FindBy(xpath = "//../header/div/button[4]")
    private WebElement nextButton;
    @FindBy(xpath = "//../header/div/button[3]")
    private WebElement previousButton;
    @FindBy(xpath = "//*[@id=/root/]//div/div/div/a[2]")
    private WebElement myProofsButton;

    public TalentProfileModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getCloseButton() {
        return closeButton;
    }

    public WebElement getNextButton() {
        return nextButton;
    }

    public WebElement getPreviousButton() {
        return previousButton;
    }

    public void clickCloseButton() {
        closeButton.click();
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public void clickPreviousButton() {
        previousButton.click();
    }

    public WebElement getMyProofsButton() {
        return myProofsButton;
    }

    public void clickMyProofsButton() {
        myProofsButton.click();
    }
}
