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

public class TalantinoTestStage3 {
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    SignInModal signInModal;
    MyAccountModal myAccountModal;
    SponsorSettingsModal sponsorSettingsModal;

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
        driver.get("http://talantino.pepega.cloud/");
        homePage = new HomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(homePage.isDisplayedLoginButton()));
        homePage.clickLoginButton();
        signInModal = new SignInModal(driver);
        signInModal.logIn("Test@mail.com", "test12345");
    }

    @Test
    public void testAuth() {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getMenuButton()));
        homePage.clickMenuButton();
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getMyProfileButton()));
        homePage.clickMyProfileButton();
        myAccountModal = new MyAccountModal(driver);
        assertThat(driver.getCurrentUrl(), equalTo(myAccountModal.getUrl()));
    }


    @Test
    public void checkEditProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getMenuButton()));
        homePage.clickMenuButton();
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getSponsorSettingsButton()));
        homePage.clickSponsorSettingsButton();
        sponsorSettingsModal = new SponsorSettingsModal(driver);
        wait.until(ExpectedConditions.visibilityOf(sponsorSettingsModal.getNameField()));
        sponsorSettingsModal.enterNewData("Antonina", "Petrova",
                "https://kh.vgorode.ua/img/forall/u/1261/66/e4d7c2ed419a9a421.jpg");
        myAccountModal = new MyAccountModal(driver);
        wait.until(ExpectedConditions.visibilityOf(myAccountModal.getSponsorName()));
        assertThat(myAccountModal.getChangedSponsorName(), equalTo("Antonina Petrova"));
    }

    @Test
    public void checkErrorMessName() {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getMenuButton()));
        homePage.clickMenuButton();
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getSponsorSettingsButton()));
        homePage.clickSponsorSettingsButton();
        sponsorSettingsModal = new SponsorSettingsModal(driver);
        wait.until(ExpectedConditions.visibilityOf(sponsorSettingsModal.getNameField()));
        sponsorSettingsModal.enterNewData("name","Antonina23");
        assertThat(sponsorSettingsModal.getErrorMess(), equalTo("First name can only contain letters"));
    }

    @Test
    public void checkErrorMessSurname() {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getMenuButton()));
        homePage.clickMenuButton();
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getSponsorSettingsButton()));
        homePage.clickSponsorSettingsButton();
        sponsorSettingsModal = new SponsorSettingsModal(driver);
        wait.until(ExpectedConditions.visibilityOf(sponsorSettingsModal.getNameField()));
        sponsorSettingsModal.enterNewData("surname", "Petrova23");
        assertThat(sponsorSettingsModal.getErrorMess(), equalTo("Last name can only contain letters"));
    }

    @Test
    public void checkMaxErrorMessName() {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getMenuButton()));
        homePage.clickMenuButton();
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getSponsorSettingsButton()));
        homePage.clickSponsorSettingsButton();
        sponsorSettingsModal = new SponsorSettingsModal(driver);
        wait.until(ExpectedConditions.visibilityOf(sponsorSettingsModal.getNameField()));
        sponsorSettingsModal.enterNewData("name","AntoninaAntoninaAntoninaA");
        assertThat(sponsorSettingsModal.getErrorMess(), equalTo("Your name is too long"));
    }

    @Test
    public void checkMinErrorMessageName() {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getMenuButton()));
        homePage.clickMenuButton();
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getSponsorSettingsButton()));
        homePage.clickSponsorSettingsButton();
        sponsorSettingsModal = new SponsorSettingsModal(driver);
        wait.until(ExpectedConditions.visibilityOf(sponsorSettingsModal.getNameField()));
        sponsorSettingsModal.enterNewData("name","A");
        assertThat(sponsorSettingsModal.getErrorMess(), equalTo("Your name is too short"));
    }

    @Test
    public void checkMaxErrorMessageSurname() {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getMenuButton()));
        homePage.clickMenuButton();
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getSponsorSettingsButton()));
        homePage.clickSponsorSettingsButton();
        sponsorSettingsModal = new SponsorSettingsModal(driver);
        wait.until(ExpectedConditions.visibilityOf(sponsorSettingsModal.getNameField()));
        sponsorSettingsModal.enterNewData("surname", "PetrovaPetrovaPetrovaPetrovaP");
        assertThat(sponsorSettingsModal.getErrorMess(), equalTo("Your surname is too long"));
    }

    @Test
    public void checkMinErrorMessageSurname() {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getMenuButton()));
        homePage.clickMenuButton();
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getSponsorSettingsButton()));
        homePage.clickSponsorSettingsButton();
        sponsorSettingsModal = new SponsorSettingsModal(driver);
        wait.until(ExpectedConditions.visibilityOf(sponsorSettingsModal.getNameField()));
        sponsorSettingsModal.enterNewData("surname", "P");
        assertThat(sponsorSettingsModal.getErrorMess(), equalTo("Your surname is too short"));
    }

    @Test
    public void checkAddKudosForSponsor() {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getMenuButton()));
        homePage.clickMenuButton();
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getMyProfileButton()));
        homePage.clickMyProfileButton();
        myAccountModal = new MyAccountModal(driver);
        myAccountModal.addKudosForSponsor("9999999999999999", "John Doe", "1424",
                "123", "0");
        wait.until(ExpectedConditions.visibilityOf(myAccountModal.getKudosSpan()));
        System.out.println(myAccountModal.getKudosNum());
        assertThat(myAccountModal.getKudosNum(), equalTo("25 kudos"));
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}