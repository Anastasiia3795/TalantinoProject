import SingUp.HomePage;
import modal.*;
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

public class TalantinoTestStage2 {
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
        signInModal.logIn("testEmail@mail.com", "test12345");
    }

    public void chooseTypeProofs(String Type) {
        proofModal = new ProofModal(driver);
        wait.until(ExpectedConditions.elementToBeClickable(proofModal.getTypeProofs()));
        proofModal.clickTypeProofs();
        wait.until(ExpectedConditions.elementToBeClickable(proofModal.findType(Type)));
        proofModal.clickChosenType();
    }

    @Test
    public void testEditButtonPresence() {
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
        assertTrue(proofModal.isAvailableTitleProof());
    }

    @Test
    public void checkChangingProofFields() {
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
        proofModal.proofChange("Title was changed", "Description was changed");
        assertThat(proofModal.getChangedTitleProof(), equalTo("Title was changed"));
    }

    @Test
    public void checkMaxErrorMessageTitleProof() {
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
        proofModal.proofTitleChange("It is a changed text content of the title");
        assertThat(proofModal.getErrorMessTitleProof(), equalTo("Title shouldn't be larger " +
                "than 40 symbols"));
    }

    @Test
    public void checkMinErrorMessageTitleProof() {
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
        proofModal.proofTitleChange("I");
        assertThat(proofModal.getErrorMessTitleProof(), equalTo("Title should be at least 2 symbols long"));
    }

    @Test
    public void checkMaxErrorMessageDescProof() {
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
        proofModal.proofDescChange("It's a changed content of the description of my proof, and this description cannot include more then two thoursand symbols and also it cannot include less quantoty of symbols than two. Therfore, this text does not have any semantic load and works only like stub of my prood description text.\n" +
                "It's a changed content of the description of my proof, and this description cannot include more then two thoursand symbols and also it cannot include less quantoty of symbols than two. Therfore, this text does not have any semantic load and works only like stub of my prood description text.\n" +
                "It's a changed content of the description of my proof, and this description cannot include more then two thoursand symbols and also it cannot include less quantoty of symbols than two. Therfore, this text does not have any semantic load and works only like stub of my prood description text.\n" +
                "It's a changed content of the description of my proof, and this description cannot include more then two thoursand symbols and also it cannot include less quantoty of symbols than two. Therfore, this text does not have any semantic load and works only like stub of my prood description text.\n" +
                "It's a changed content of the description of my proof, and this description cannot include more then two thoursand symbols and also it cannot include less quantoty of symbols than two. Therfore, this text does not have any semantic load and works only like stub of my prood description text.\n" +
                "It's a changed content of the description of my proof, and this description cannot include more then two thoursand symbols and also it cannot include less quantoty of symbols than two. Therfore, this text does not have any semantic load and works only like stub of my prood description text.\n" +
                "It's a changed content of the description of my proof, and this description cannot include more then two thoursand symbols and also it cannot include less quantoty of symbols than two. Therfore, this text does not have any semantic load and works only like stub of my prood description text.");
        assertThat(proofModal.getErrorMessDescProof(), equalTo("Proof shouldn't be larger than" +
                " 2000 symbols"));
    }

    @Test
    public void checkMinErrorMessageDescProof() {
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
        proofModal.proofDescChange("I");
        assertThat(proofModal.getErrorMessDescProof(), equalTo("Proof should be at least 2 symbols long"));
    }

    @Test
    public void checkPublishedStatusProof() {
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
        wait.until(ExpectedConditions.elementToBeClickable(proofModal.getPublishedButton()));
        proofModal.clickPublishedButton();
        assertTrue(proofModal.isSubmitAvailable(proofModal.getPublishedButton()));
    }

    @Test
    public void checkHiddenStatusProof() {
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
        wait.until(ExpectedConditions.elementToBeClickable(proofModal.getHiddenButton()));
        proofModal.clickHiddenButton();
        assertTrue(proofModal.isSubmitAvailable(proofModal.getHiddenButton()));
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}