import SingUp.HomePage;
import modal.MyAccountModal;
import modal.ProofModal;
import modal.SignInModal;
import modal.TalentProfileModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertTrue;

public class TalantinoTestStage4 {
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    SignInModal signInModal;
    MyAccountModal myAccountModal;
    TalentProfileModal talentProfileModal;
    ProofModal proofModal;

    @BeforeTest
    public void initDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        authorization();
    }

    public void authorization() {
        driver.get("http://18.156.56.85:8084/talents");
        homePage = new HomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(homePage.isDisplayedLoginButton()));
        homePage.clickLoginButton();
        signInModal = new SignInModal(driver);
        signInModal.logIn("maschenkonastya@gmail.com", "test12345");
    }

    public void chooseTypeProofs(String Type) {
        proofModal = new ProofModal(driver);
        wait.until(ExpectedConditions.elementToBeClickable(proofModal.getTypeProofs()));
        proofModal.clickTypeProofs();
        wait.until(ExpectedConditions.elementToBeClickable(proofModal.findType(Type)));
        proofModal.clickChosenType();
    }

    @Test
    public void checkDeletingAllSkillEditProof() {
        wait.until(ExpectedConditions.visibilityOf(homePage.getMenuButton()));
        homePage.clickMenuButton();
        wait.until(ExpectedConditions.visibilityOf(homePage.getMyProfileButton()));
        homePage.clickMyProfileButton();
        talentProfileModal = new TalentProfileModal(driver);
        wait.until((ExpectedConditions.elementToBeClickable(talentProfileModal.getMyProofsButton())));
        talentProfileModal.clickMyProofsButton();
        chooseTypeProofs("Draft");
        wait.until(ExpectedConditions.elementToBeClickable(proofModal.getEditProofButton()));
        proofModal.clickEditProofButton();
        wait.until(ExpectedConditions.visibilityOf(proofModal.getTitleFieldProof()));
        int numOfSkills = proofModal.getNumOfSkills();
        proofModal.deleteAllSkills();
        assertTrue(proofModal.getNumOfSkills()<numOfSkills);
        proofModal.clickSaveChanges();
    }

    @Test
    public void checkDeletingSkillEditProof() {
        wait.until(ExpectedConditions.visibilityOf(homePage.getMenuButton()));
        homePage.clickMenuButton();
        wait.until(ExpectedConditions.visibilityOf(homePage.getMyProfileButton()));
        homePage.clickMyProfileButton();
        talentProfileModal = new TalentProfileModal(driver);
        wait.until((ExpectedConditions.elementToBeClickable(talentProfileModal.getMyProofsButton())));
        talentProfileModal.clickMyProofsButton();
        chooseTypeProofs("Draft");
        wait.until(ExpectedConditions.elementToBeClickable(proofModal.getEditProofButton()));
        proofModal.clickEditProofButton();
        wait.until(ExpectedConditions.visibilityOf(proofModal.getTitleFieldProof()));
        int numOfSkills = proofModal.getNumOfSkills();
        proofModal.clickDeleteSkillButton();
        assertTrue(proofModal.getNumOfSkills()<numOfSkills);
        proofModal.clickSaveChanges();
    }

    @Test
    public void checkDeletingSkillPublishedProof() {
        wait.until(ExpectedConditions.visibilityOf(homePage.getMenuButton()));
        homePage.clickMenuButton();
        wait.until(ExpectedConditions.visibilityOf(homePage.getMyProfileButton()));
        homePage.clickMyProfileButton();
        talentProfileModal = new TalentProfileModal(driver);
        wait.until((ExpectedConditions.elementToBeClickable(talentProfileModal.getMyProofsButton())));
        talentProfileModal.clickMyProofsButton();
        chooseTypeProofs("Published");
        wait.until(ExpectedConditions.elementToBeClickable(proofModal.getEditProofButton()));
        proofModal.clickEditProofButton();
        assertTrue(proofModal.isClickableDeleteSkillButton() == false);
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}