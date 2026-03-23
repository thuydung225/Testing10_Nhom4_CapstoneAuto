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
import report.ExtentReportManager;

import java.time.Duration;

public class Profile01_Open_ProfilePage extends BaseTest {
    @Test(description = "Verify that user can open profile page successfully")
    public void Profile01_Open_ProfilePage(){
        String account = "meimei001";
        String password = "meimei123";
        String fullname = "Van";

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

        //Step 2: Click 'Đăng nhập' link on the top right
        LOG.info("Step 2: Click 'Đăng nhập' link on the top right");
        ExtentReportManager.info("Step 2: Click 'Đăng nhập' link on the top right");
        homePage.getTopBarNavigation().navigateLoginPage();

        //Step 3: Enter account
        //Step 4: Enter password
        //Step 5: Click 'Dang Nhap' button
        LOG.info("Step 3: Enter account");
        LOG.info("Step 4: Enter password");
        LOG.info("Step 5: Click 'Dang Nhap' button");
        ExtentReportManager.info("Step 3: Enter account");
        ExtentReportManager.info("Step 4: Enter password");
        ExtentReportManager.info("Step 5: Click 'Dang Nhap' button");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(account, password);
        homePage.waitForPageStable();

        //Step 6: Click 'Avatar' on the top right
        LOG.info("Step 6: Click 'Avatar' on the top right");
        ExtentReportManager.info("Step 6: Click 'Avatar' on the top right");
        homePage.getTopBarNavigation().openDropdownMenu();

        //Step 7: Click 'Thông tin tài khoản'
        LOG.info("Step 7: Click 'Thông tin tài khoản'");
        ExtentReportManager.info("Step 7: Click 'Thông tin tài khoản'");
        homePage.getTopBarNavigation().navigateAccountInfoPage();

        //Step 8: Verify profile page displayed
        LOG.info("Step 8: Verify profile page displayed");
        ExtentReportManager.info("Step 8: Verify profile page displayed");
        ProfilePage profilePage = new ProfilePage(driver);

        //VP1: Profile page displayed
        LOG.info("VP1: Verify profile page displayed");
        ExtentReportManager.info("VP1: Verify profile page displayed");
        Assert.assertTrue(profilePage.isProfilePageDisplayed(),
                "Profile page is not displayed");

        //VP2: Avatar
        LOG.info("VP2: Verify avatar");
        ExtentReportManager.info("VP2: Verify avatar");
        Assert.assertTrue(profilePage.isAvatarDisplayed(),
                "Avatar not displayed");

        //VP3: Thông tin cơ bản
        LOG.info("Verify fullname");
        ExtentReportManager.info("Verify fullname");
        Assert.assertEquals(profilePage.getUserFullname(), fullname,
                "Fullname incorrect");


        LOG.info("Verify email displayed");
        ExtentReportManager.info("Verify email displayed");
        Assert.assertNotNull(profilePage.getUserEmail(), "Email is null");
        Assert.assertFalse(profilePage.getUserEmail().trim().isEmpty(), "Email is empty");

        //VP4: Tabs hoạt động
        LOG.info("VP4: Verify tabs navigation");
        ExtentReportManager.info("VP4: Verify tabs navigation");
        profilePage.openWaitingListTab();
        profilePage.openAppointmentTab();
        profilePage.openPrescriptionTab();
        profilePage.openInvoiceTab();

        //VP5: Thông tin cá nhân hiển thị
        LOG.info("Verify personal info section");
        ExtentReportManager.info("VP5: Verify personal info section");
        Assert.assertTrue(profilePage.isPersonalInfoDisplayed(),
                "Personal info section not displayed");

        LOG.info(" Verify join date");
        ExtentReportManager.info(" Verify join date");
        Assert.assertTrue(profilePage.getJoinDate().length() > 0,
                "Join date not displayed");

        LOG.info("Verify phone displayed");
        ExtentReportManager.info("Verify phone displayed");
        Assert.assertNotNull(profilePage.getPhone(), "Phone is null");
        Assert.assertTrue( profilePage.getPhone()!= null && !profilePage.getPhone().trim().isEmpty(),
                "Phone is invalid");

        LOG.info("Verify address field displayed");
        ExtentReportManager.info("Verify address field displayed");
        Assert.assertTrue(profilePage.isAddressDisplayed(),
                "Address field is not displayed");

    }

}
