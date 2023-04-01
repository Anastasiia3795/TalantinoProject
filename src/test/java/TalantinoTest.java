import SingUp.HomePage;
import modal.MyAccountModal;
import modal.SignInModal;
import modal.SignUpModal;
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

public class TalantinoTest {
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    SignInModal signInModal;
    SignUpModal signUpModal;
    MyAccountModal myAccountModal;
    TalentProfileModal talentProfileModal;

    @BeforeTest
    public void initDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testRegistrationTalantino() {
        driver.get("http://18.156.56.85:8084/talents");
        homePage = new HomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(homePage.isDisplayedLoginButton()));
        homePage.clickLoginButton();
        signInModal = new SignInModal(driver);
        wait.until(ExpectedConditions.visibilityOf(signInModal.getSignInButton()));
        signInModal.clickSignInButton();
        signUpModal = new SignUpModal(driver);
        signUpModal.signUp("Test", "Testerov", "testEmail@mailinator.com",
                "testPassword123", "testPassword123", "QA");
        signUpModal.clickSignUpButton();
        myAccountModal = new MyAccountModal(driver);
        assertThat(driver.getCurrentUrl(), equalTo(myAccountModal.getUrl()));
    }

    @Test
    public void checkFillingSomeFieldsRegistration() {
        driver.get("http://18.156.56.85:8084/talents");
        homePage = new HomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(homePage.isDisplayedLoginButton()));
        homePage.clickLoginButton();
        signInModal = new SignInModal(driver);
        wait.until(ExpectedConditions.visibilityOf(signInModal.getSignInButton()));
        signInModal.clickSignInButton();
        signUpModal = new SignUpModal(driver);
        signUpModal.signUp("Test", "Testerov", "",
                "testPassword123", "", "");
        assertThat(signUpModal.getSignUpButton().isEnabled(), equalTo(false));
    }

    @Test
    public void checkRedirectToLoginForm() {
        driver.get("http://18.156.56.85:8084/talents");
        homePage = new HomePage(driver);
        wait.until(ExpectedConditions.visibilityOf(homePage.getTalentProfileButton()));
        homePage.clickTalentProfileButton();
        assertThat(driver.getCurrentUrl(), equalTo("http://18.156.56.85:8084/login"));
    }

    @Test
    public void testLoginTalantino() {
        driver.get("http://18.156.56.85:8084/talents");
        homePage = new HomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(homePage.isDisplayedLoginButton()));
        homePage.clickLoginButton();
        signInModal = new SignInModal(driver);
        signInModal.logIn("testEmail@mailinator.com", "testPassword123");
        wait.until(ExpectedConditions.visibilityOf(signInModal.getSignInButton()));
        signInModal.clickSignInButton();
        wait.until(ExpectedConditions.visibilityOf(homePage.getUserName()));
        assertTrue(homePage.getUserName().getText().contains("Hi, TestFirst"));
    }

    @Test
    public void checkEnteringNoDataToLoginForm() {
        driver.get("http://18.156.56.85:8084/talents");
        homePage = new HomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(homePage.isDisplayedLoginButton()));
        homePage.clickLoginButton();
        signInModal = new SignInModal(driver);
        signInModal.logIn("", "");
        assertThat(signInModal.getLoginSubmit().isEnabled(), equalTo(false));
    }

    @Test
    public void checkErrorMessageLoginForm() {
        driver.get("http://18.156.56.85:8084/talents");
        homePage = new HomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(homePage.isDisplayedLoginButton()));
        homePage.clickLoginButton();
        signInModal = new SignInModal(driver);
        signInModal.logIn("Test@mailcom", "password");
        assertTrue(signInModal.getEmailErrorMes().isDisplayed());
        assertThat(signInModal.getPasswordErrorMes(), equalTo("Your password doesn't meet requirements"));
    }

    @Test
    public void checkButtonsOnTalentProfile() {
        driver.get("http://18.156.56.85:8084/talents");
        homePage = new HomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(homePage.isDisplayedLoginButton()));
        homePage.clickLoginButton();
        signInModal = new SignInModal(driver);
        signInModal.logIn("testEmail@mailinator.com", "testPassword123");
        wait.until(ExpectedConditions.visibilityOf(homePage.getTalentProfileButton()));
        homePage.clickTalentProfileButton();
        talentProfileModal = new TalentProfileModal(driver);
        wait.until(ExpectedConditions.elementToBeClickable(talentProfileModal.getNextButton()));
        talentProfileModal.clickNextButton();
        assertThat(homePage.getHomePageUrl(), equalTo("http://18.156.56.85:8084/talent/11"));
        wait.until(ExpectedConditions.elementToBeClickable(talentProfileModal.getPreviousButton()));
        talentProfileModal.clickPreviousButton();
        assertThat(homePage.getHomePageUrl(), equalTo("http://18.156.56.85:8084/talent/10"));
        wait.until(ExpectedConditions.elementToBeClickable(talentProfileModal.getCloseButton()));
        talentProfileModal.clickCloseButton();
        assertThat(homePage.getHomePageUrl(), equalTo("http://18.156.56.85:8084/talents/?page=1"));
    }

    @Test
    public void checkRedirectToTalentProfile() {
        driver.get("http://18.156.56.85:8084/talents");
        homePage = new HomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(homePage.isDisplayedLoginButton()));
        homePage.clickLoginButton();
        signInModal = new SignInModal(driver);
        signInModal.logIn("testEmail@mailinator.com", "testPassword123");
        wait.until(ExpectedConditions.visibilityOf(homePage.getTalentProfileButton()));
        homePage.clickTalentProfileButton();
        assertThat(homePage.getHomePageUrl(), equalTo("http://18.156.56.85:8084/talent/10"));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}