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

public class Booking03_Verify_Booking_Button_Enable extends BaseTest {

    @Test(description = "Verify booking button is enable when all required fields are filled correctly")
    public void Booking03_Verify_Booking_Button_Is_Enable() throws InterruptedException {

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
        LOG.info("Step 7: Click 'Đặt lịch khám' link on the top");
        ExtentReportManager.info("Step 7: Click 'Đặt lịch khám' link on the top");
        homePage.getTopBarNavigation().navigateBookingPage();

        //Step 8: Select branch
        LOG.info("Step 8: Select branch");
        ExtentReportManager.info("Step 8: Select branch");
        bookingPage.selectBranch("1");

        //Step 9: Select doctor
        LOG.info("Step 9: Select doctor");
        ExtentReportManager.info("Step 9: Select doctor");
        bookingPage.selectDoctor("5");

        //Step 10: Select date
        LOG.info("Step 10: Select date");
        ExtentReportManager.info("Step 10: Select date");
        bookingPage.selectDate(31);

        //Step 11: Select package
        LOG.info("Step 11: Select package");
        ExtentReportManager.info("Step 11: Select package");
        bookingPage.selectPackage("Bảo hiểm");

        //Step 12: Enter note
        LOG.info("Step 12: Enter note");
        ExtentReportManager.info("Step 12: Enter note");
        bookingPage.enterNote("Đau bụng");

        //Step 13: Verify booking button is enable
        //VP2: Check booking button is enabled
        LOG.info("Step 13: Verify booking button is enable");
        ExtentReportManager.info("VP2: Verify booking button is enable");
        Assert.assertTrue(bookingPage.isBookingButtonEnabled(),"Booking button is still disabled");
    }
}
