package testcase.profile;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;
import pages.RegisterPage;
import report.ExtentReportManager;

import java.time.Duration;

public class Profile04_E2E_Register_Login_Profile extends BaseTest {
    @Test(description = "E2E: Register → Login → Open Profile → Verify info")
    public void Profile04_E2E_Register_Login_Profile() {
        // ====== TEST DATA ======
        String username = "user" + System.currentTimeMillis();
        String password = "123456";
        String fullname = "Tester";
        String email = "test@gmail.com";
        String phone = "0123456789";

        WebDriver driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);

        //Step 1: Go to https://demo6.cybersoft.edu.vn/
        LOG.info("Step 1: Go to https://demo6.cybersoft.edu.vn/");
        ExtentReportManager.info("Step 1: Go to https://demo6.cybersoft.edu.vn/");
        driver.get("https://demo6.cybersoft.edu.vn/");
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class);


        //Step 2: Go to Register Page
        LOG.info("Step 2: Navigate to Register page");
        ExtentReportManager.info("Step 2: Navigate to Register page");
        homePage.getTopBarNavigation().navigateRegisterPage();

        //Step 3: Register new account
        LOG.info("Step 3: Register new account");
        ExtentReportManager.info("Step 3: Register new account");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(
                username,
                password,
                fullname,
                email,
                phone,
                "01-01-2000",
                "Male"
        );

        homePage.waitForPageStable();
        //Step 5: Login
        LOG.info("Step 5: Login with new account");
        ExtentReportManager.info("Step 5: Login with new account");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        homePage.waitForPageStable();

        //Step 6: Open Profile
        LOG.info("Step 6: Open Profile page");
        ExtentReportManager.info("Step 6: Open Profile page");

        homePage.getTopBarNavigation().openDropdownMenu();
        homePage.getTopBarNavigation().navigateAccountInfoPage();
        //Step 7: Verify Profile info
        LOG.info("Step 7: Verify profile info");
        ExtentReportManager.info("Step 7: Verify profile info");
        ProfilePage profilePage = new ProfilePage(driver);

        //VP1: Profile page displayed
        LOG.info("VP1: Verify profile page is displayed");
        ExtentReportManager.info("VP1: Verify profile page is displayed");
        Assert.assertTrue(profilePage.isProfilePageDisplayed(),
                "Profile page is not displayed");

        //VP2: Avatar displayed
        LOG.info("VP2: Verify avatar is displayed");
        ExtentReportManager.info("VP2: Verify avatar is displayed");
        Assert.assertTrue(profilePage.isAvatarDisplayed(),
                "Avatar not displayed");

        //VP3: Fullname correct
        LOG.info("VP3: Verify fullname");
        ExtentReportManager.info("VP3: Verify fullname");
        Assert.assertEquals(profilePage.getUserFullname(), fullname,
                "Fullname incorrect");

        //VP4: Email correct
        LOG.info("VP4: Verify email");
        ExtentReportManager.info("VP4: Verify email");
        Assert.assertEquals(profilePage.getUserEmail(), email,
                "Email incorrect");

        //VP5: Phone correct
        LOG.info("VP5: Verify phone");
        ExtentReportManager.info("VP5: Verify phone");
        Assert.assertEquals(profilePage.getPhone(), phone,
                "Phone incorrect");

        //VP6: Tabs navigation
        LOG.info("VP6: Verify tabs navigation");
        ExtentReportManager.info("VP6: Verify tabs navigation");
        profilePage.openWaitingListTab();
        profilePage.openAppointmentTab();
        profilePage.openPrescriptionTab();
        profilePage.openInvoiceTab();

    //VP7: Personal info section
        LOG.info("VP7: Verify personal info section displayed");
        ExtentReportManager.info("VP7: Verify personal info section displayed");
        Assert.assertTrue(profilePage.isPersonalInfoDisplayed(),
                "Personal info section not displayed");

    //VP8: Join date
        LOG.info("VP8: Verify join date displayed");
        ExtentReportManager.info("VP8: Verify join date displayed");
        Assert.assertTrue(profilePage.getJoinDate().length() > 0,
                "Join date not displayed");

    //VP9: Address (skip)
        LOG.info("VP9: Skip verify address (not provided during registration)");
        ExtentReportManager.info("VP9: Skip verify address (not provided during registration)");
    }
}

