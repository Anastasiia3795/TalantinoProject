package modal;

import SingUp.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProofModal extends BasePage {

    @FindBy(xpath = "//*[@id='root']/div/div/div[2]/div/div[1]/div/div/div")
    private WebElement typeProofs;
    @FindBy(xpath = "//button[contains(@title, 'Edit')]")
    private WebElement editProofButton;
    @FindBy(xpath = "//../div/div/form/div[1]/div[1]/div/input")
    private WebElement titleProofInput;
    @FindAll({@FindBy(name = "description")})
    private List<WebElement> descriptionProofInput;
    @FindBy(xpath = "//div[contains(@class, 'MuiBox-root css-1ocsmqa')]/button[contains(@value, 'DRAFT')]")
    private WebElement saveChanges;
    @FindBy(xpath = "//../div[2]/div/div[2]/div/div/div/div[1]/h6")
    private WebElement changedTitleProof;
    @FindBy(xpath = "//../div[2]/div/div[2]/div/div/form/div[3]/span")
    private WebElement errorMessTitleProof;
    @FindBy(xpath = "//../div[2]/div/div[2]/div/div/form/div[3]/span")
    private WebElement errorMessDescProof;
    @FindBy(xpath = "//div[contains(@aria-label, 'Platform')]/button[contains(@value, 'PUBLISHED')]")
    private WebElement publishedButton;
    @FindBy(xpath = "//div[contains(@aria-label, 'Platform')]/button[contains(@value, 'HIDDEN')]")
    private WebElement hiddenButton;
    @FindBy(xpath = "//div[contains(@class, 'MuiBox-root css-1ocsmqa')]/button[contains(@value, 'PUBLISHED')]")
    private WebElement publishedSubmit; //Published button at the bottom of the edit-form
    @FindBy(xpath = "//div[contains(@class, 'MuiBox-root css-1ocsmqa')]/button[contains(@value, 'HIDDEN')]")
    private WebElement hideSubmit; //Hide button at the bottom of the edit-form

    private WebElement chosenType;

    public ProofModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getTypeProofs() {
        return typeProofs;
    }

    public void clickTypeProofs() {
        typeProofs.click();
    }

    public WebElement findType(String Type) {
        chosenType = driver.findElement(By.xpath("//li[contains(text(), '" + Type + "')]"));
        return chosenType;
    }

    public void clickChosenType() {
        chosenType.click();
    }

    public WebElement getEditProofButton() {
        return editProofButton;
    }

    public void clickEditProofButton() {
        editProofButton.click();
    }

    public boolean isAvailableTitleProof() {
        if (titleProofInput.isDisplayed())
            return true;
        else return false;
    }

    public void clickSaveChanges() {
        saveChanges.click();
    }

    public WebElement getDescProofInput() {
        return descriptionProofInput.get(1);
    }
    public void proofChange(String title, String description) {
        titleProofInput.clear();
        titleProofInput.sendKeys(title);
        getDescProofInput().clear();
        getDescProofInput().sendKeys(description);
        clickSaveChanges();
    }

    public void proofTitleChange(String title) {
        titleProofInput.clear();
        titleProofInput.sendKeys(title);
    }

    public void proofDescChange(String title) {
        getDescProofInput().clear();
        getDescProofInput().sendKeys(title);
    }

    public String getChangedTitleProof() {
        return changedTitleProof.getText();
    }

    public String getErrorMessTitleProof() {
        return errorMessTitleProof.getText();
    }

    public String getErrorMessDescProof() {
        return errorMessDescProof.getText();
    }

    public WebElement getPublishedButton() { return publishedButton; }

    public WebElement getHiddenButton() { return hiddenButton; }

    public void clickPublishedButton() {
        publishedButton.click();
    }

    public void clickHiddenButton() {
        hiddenButton.click();
    }

    public boolean isSubmitAvailable(WebElement submit) {
        if (submit.isEnabled())
            return true;
        else return false;
    }
}
