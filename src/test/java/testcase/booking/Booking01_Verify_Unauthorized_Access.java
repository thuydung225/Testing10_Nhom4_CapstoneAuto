package testcase.booking;

import base.BaseTest;
import constants.constantsURL;
import drivers.DriverFactory;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.dialog.CommonDialog;
import report.ExtentReportManager;

import java.time.Duration;

public class Booking01_Verify_Unauthorized_Access extends BaseTest {
    @Test(description = "Verify unauthorized user is redirected to Login page when accessing Booking URL ")
    public void Booking01_verify_RedirectToLogin_When_Unauthorized() throws InterruptedException {

        WebDriver driver = DriverFactory.getDriver();

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        //Step 1: Go to https://demo6.cybersoft.edu.vn/
        LOG.info("Step 1: Go to https://demo6.cybersoft.edu.vn/");
        ExtentReportManager.info("Step 1: Go to https://demo6.cybersoft.edu.vn/");
        driver.get(constantsURL.BASE_URL);

        //Step 2: Click 'Đặt lịch khám' link on the top
        LOG.info("Step 2: Click 'Đặt lịch khám' link on the top");
        ExtentReportManager.info("Step 2: Click 'Đặt lịch khám' link on the top");
        homePage.getTopBarNavigation().navigateBookingPage();

        //Step 3: Verify user is redirected to Login Page
        //VP1: Check user is redirected to Login Page
        LOG.info("Step 3: Verify user is redirected to Login Page");
        LOG.info("VP1: Verify user is redirected to Login Page");
        ExtentReportManager.info("Step 3: Verify user is redirected to Login Page");
        ExtentReportManager.info("VP1: Verify user is redirected to Login Page");
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "User is not redirected to the Login page");

        // VP2: Check "Vui lòng đăng nhập để đặt lịch khám." message display
        LOG.info("VP2: Check 'Vui lòng đăng nhập để đặt lịch khám.' message display");
        ExtentReportManager.info("VP2: Check 'Vui lòng đăng nhập để đặt lịch khám.' message display");
        CommonDialog dialog = new CommonDialog(driver);
        String recordedLoginMsg = dialog.getTextRequestLoginMessage();
        Assert.assertEquals(recordedLoginMsg, "Vui lòng đăng nhập để đặt lịch khám.", "Incorrect register message!");
    }
}
