package modal;

import SingUp.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SponsorSettingsModal extends BasePage {

    @FindBy(xpath = "//input[@name='name']")
    private WebElement nameField;
    @FindBy(xpath = "//input[@name='surname']")
    private WebElement surnameField;
    @FindBy(xpath = "//input[@name='avatar']")
    private WebElement imageField;
    @FindBy(xpath = "//*[@id='root']/div/div/form/div[1]/button")
    private WebElement saveButton;
    @FindBy(xpath = "//p[contains(@class, 'MuiFormHelperText-root Mui-error')]")
    private WebElement errorMess;

    public SponsorSettingsModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getNameField() {
        return nameField;
    }
    public void enterNewData(String name, String surname, String image) {
        this.nameField.clear();
        this.surnameField.clear();
        this.imageField.clear();
        this.nameField.sendKeys(name);
        this.surnameField.sendKeys(surname);
        this.imageField.sendKeys(image);
        saveButton.click();
    }

    public void enterNewData(String field, String str) {
        switch (field) {
            case "name": {
                this.nameField.clear();
                this.nameField.sendKeys(str);
                break;
            }
            case "surname": {
                this.surnameField.clear();
                this.surnameField.sendKeys(str);
                break;
            }
        }
    }

    public String getErrorMess() {
        return errorMess.getText();
    }
}
