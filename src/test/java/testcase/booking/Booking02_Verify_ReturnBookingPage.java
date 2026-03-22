package testcase.booking;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookingPage;
import pages.HomePage;
import pages.LoginPage;
import report.ExtentReportManager;

import java.time.Duration;

public class Booking02_Verify_ReturnBookingPage extends BaseTest {

    @Test(description = "Verify that user is redirected to BookingPage after login")
    public void Booking02_Verify_ReturnToBookingPage_After_Login() throws InterruptedException {
        String account = "f6d1da71-e8e1-421b-abc3-0e552fe8116d";
        String password = "Test123.";

        WebDriver driver = DriverFactory.getDriver();

        HomePage homePage = new HomePage(driver);
        BookingPage bookingPage = new BookingPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        //Step 1: Go to https://demo6.cybersoft.edu.vn/
        LOG.info("Step 1: Go to https://demo6.cybersoft.edu.vn/");
        ExtentReportManager.info("Step 1: Go to https://demo6.cybersoft.edu.vn/");
        driver.get("https://demo6.cybersoft.edu.vn/");

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class);

        //Step 2: Click 'Đặt lịch khám' link on the top
        LOG.info("Step 2: Click 'Đặt lịch khám' link on the top");
        ExtentReportManager.info("Step 2: Click 'Đặt lịch khám' link on the top");
        homePage.getNavigationBar().navigateBookingPage();

        //Step 3: Enter account
        //Step 4: Enter password
        //Step 5: Click 'Đăng nhập' button
        LOG.info("Step 3: Enter account");
        LOG.info("Step 4: Enter password");
        LOG.info("Step 5: Click 'Dang Nhap' button");
        ExtentReportManager.info("Step 3: Enter account");
        ExtentReportManager.info("Step 4: Enter password");
        ExtentReportManager.info("Step 5: Click 'Dang Nhap' button");
        loginPage.login(account, password);

        //Step 6: Verify user is redirected to Booking Page
        //VP1: Check user is redirected to Booking Page
        LOG.info("Step 3: Verify user is redirected to Booking Page");
        LOG.info("VP1: Verify user is redirected to Booking Page");
        ExtentReportManager.info("Step 3: Verify user is redirected to Booking Page");
        ExtentReportManager.info("VP1: Verify user is redirected to Booking Page");
        Assert.assertTrue(bookingPage.isBookingPageDisplayed(), "User is not redirected to the Booking page");
    }

}
