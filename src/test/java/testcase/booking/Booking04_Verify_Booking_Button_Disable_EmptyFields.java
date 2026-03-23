package testcase.booking;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookingPage;
import pages.HomePage;
import pages.LoginPage;
import pages.dialog.CommonDialog;
import report.ExtentReportManager;

import java.time.Duration;

public class Booking04_Verify_Booking_Button_Disable_EmptyFields extends BaseTest {

    @Test(description = "Veryfi booking button is diasble when no data is entered")
    public void Booking04_Booking_Button_Disable_Empty_Fileds() throws InterruptedException {
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

        //Step 2: Click 'Đăng nhập' link on the top right
        LOG.info("Step 2: Click 'Đăng nhập' link on the top right");
        ExtentReportManager.info("Step 2: Click 'Đăng nhập' link on the top right");
        homePage.getTopBarNavigation().navigateLoginPage();

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

        //Step 6: Verify user login successfully
        //VP1: Check 'Đăng nhập thành công' message display
        LOG.info("Step: 6 Verify user login successfully");
        LOG.info("VP1: Check 'Đăng nhập thành công' message display");
        ExtentReportManager.info("Step 6: Verify user login successfully");
        ExtentReportManager.info("VP1: Check 'Đăng nhập thành công' message display");
        CommonDialog dialog = new CommonDialog(driver);
        String recordedLoginSuccessMsg = dialog.getLoginTextMessage();
        Assert.assertEquals(recordedLoginSuccessMsg, "Đăng nhập thành công", "Incorrect login message !");

        //Step 7: Click 'Đặt lịch khám' link on the top
        LOG.info("Step 6: Click 'Đặt lịch khám' link on the top");
        ExtentReportManager.info("Step 7: Click 'Đặt lịch khám' link on the top");
        homePage.getTopBarNavigation().navigateBookingPage();

        //Step 8: Verify booking button is disable
        //VP2: Check booking button is diable
        LOG.info("Step 8: Verify booking button is disable");
        ExtentReportManager.info("VP2: Verify booking button is disable");
        Assert.assertFalse(bookingPage.isBookingButtonEnabled(),"Booking button is enable");
    }
}
