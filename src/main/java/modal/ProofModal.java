package modal;

import SingUp.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v109.input.Input;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProofModal extends BasePage {

    @FindBy(xpath = "//div[contains(@role, 'button')]")
    private WebElement typeProofs;
    @FindBy(xpath = "//button[contains(@title, 'Edit')]")
    private WebElement editProofButton;
    @FindBy(xpath = "//../div/div/form/div[1]/div[1]/div/input")
    private WebElement titleProofInput;
    @FindAll({@FindBy(name = "description")})
    private List<WebElement> descriptionProofInput;
    @FindBy(xpath = "//button[contains(@class, 'colorInherit css-1fqsg3k')]")
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
    //Creating proof
    @FindBy(xpath = "//input[contains(@name,'title')]")
    private WebElement titleFieldProof; //input title in create-form of proof
    @FindBy(xpath = "//textarea[contains(@name,'description')]")
    private WebElement descFieldProof; //input description in create-form of proof
    @FindBy(xpath = "//input[contains(@id, 'tags-outlined')]")
    private WebElement skillFieldProof; //skills in create-form of proof
    @FindBy(xpath = "//button[contains(@title,'Save as draft')]")
    private WebElement saveProofButton; //save button in create-form of proof
    //Edit skills
    @FindAll({@FindBy(xpath = "//*[contains(@class, 'MuiChip-deleteIcon')]")})
    private List<WebElement> deleteSkillButton;
    @FindAll({@FindBy(xpath = "//div[contains(@class, 'MuiBox-root css-bb3h56')]/div")})
    private List<WebElement> skillList; //List of skills on the proof
    @FindBy(xpath = "//button[contains(@title, 'Clear')]")
    private WebElement deleteAllSkills; //delete button on the edit proof form
    @FindBy(xpath = "//div[contains(@class, 'MuiAutocomplete-inputRoot css-11zx5rc')]/input")
    private WebElement skillFieldEdit; //input skill field on the edit proof form

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

    public WebElement getTitleFieldProof() {return titleFieldProof;}

    public WebElement getDeleteSkillButton() {
        return deleteSkillButton.get(1);
    }

    public boolean isClickableDeleteSkillButton() {
        if(deleteSkillButton.isEmpty())
            return false;
        else return true;
    }

    public void clickDeleteSkillButton() {
        getDeleteSkillButton().click();
    }

    public int getNumOfSkills() {
        int numOfSkills = deleteSkillButton.size();
        return numOfSkills;
    }

    public WebElement getDeleteAllSkills() { return deleteAllSkills; }

    public WebElement getSkillFieldEdit() { return skillFieldEdit; }
    public void deleteAllSkills() {
        getSkillFieldEdit().click();
        getDeleteAllSkills().click();
    }

}
