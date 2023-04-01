package modal;

import SingUp.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MyAccountModal extends BasePage {

    private String urlName;

    public MyAccountModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getUrl() {
        urlName = driver.getCurrentUrl();
        return urlName;
    }
}
