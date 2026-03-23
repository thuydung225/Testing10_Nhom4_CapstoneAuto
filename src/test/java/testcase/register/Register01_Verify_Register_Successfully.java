package testcase.register;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;
import pages.dialog.CommonDialog;
import report.ExtentReportManager;

import java.time.Duration;
import java.util.UUID;

public class Register01_Verify_Register_Successfully extends BaseTest {

    @Test(description = "Verify that user can register with valid information")
    public void Register01_Register_Successfully() {
        UUID uuid = UUID.randomUUID();

        String username = uuid.toString(); //unique
        String email = username + "@example.com"; // unique
        String fullname = "Park Jimin";
        System.out.println(username);
        System.out.println(email);

        WebDriver driver = DriverFactory.getDriver();

        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        //Step 1: go to https://demo1.cybersoft.edu.vn/
        LOG.info("Step 1: Go to https://demo6.cybersoft.edu.vn/");
        ExtentReportManager.info("Step 1: Go to https://demo6.cybersoft.edu.vn/");
        driver.get("https://demo6.cybersoft.edu.vn/");

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class);

        //Step 2: Click "Dang ki" link on the top right
        LOG.info("Step 2: Click \"Dang ki\" link on the top right");
        ExtentReportManager.info("Step 2: Click \"Dang ki\" link on the top right");
        homePage.getTopBarNavigation().navigateRegisterPage();

        // Step 3: Enter username
        LOG.info("Step 3: Enter username");
        ExtentReportManager.info("Step 3: Enter username");
        registerPage.enterUsername(username);

        //Step 4: Enter password
        LOG.info("Step 4: Enter password");
        ExtentReportManager.info("Step 4: Enter password");
        registerPage.enterYourPassword("Test123456@");

        //Step 5: Enter confirm password
        LOG.info("Step 5: Enter confirm password");
        ExtentReportManager.info("Step 5: Enter confirm password");
        registerPage.enterConfirmPassword("Test123456@");

        //Step 6: Enter fullname
        LOG.info("Step 6: Enter fullname");
        ExtentReportManager.info("Step 6: Enter fullname");
        registerPage.enterFullname(fullname);

        //Step 7: Enter Email
        LOG.info("Step 7: Enter Email");
        ExtentReportManager.info("Step 7: Enter Email");
        registerPage.enterYourEmail(email);

        //Step 8: Enter Phone number
        LOG.info("Step 8: Enter Phone number");
        ExtentReportManager.info("Step 8: Enter Phone number");
        registerPage.enterPhoneNumber("0123456789");

        //Step 9: Select Gender
        LOG.info("Step 9: Select Gender");
        ExtentReportManager.info("Step 9: Select Gender");;
        registerPage.selectGender("Male");

        //Step 10: Pick DateOfBirth
        LOG.info("Step 10: Pick DateOfBirth");
        ExtentReportManager.info("Step 10: Pick DateOfBirth");
        registerPage.enterDateOfBirth("03212026");

        //Step 11: Click 'Dang ky'
        LOG.info("Step 11: Click 'Dang ky'");
        ExtentReportManager.info("Step 11: Click 'Dang ky'");
        registerPage.clickRegister();

        //Step 12: Verify user register successfully
        //VP: Check 'Đăng ký thành công' message display
        LOG.info("Step 12: Verify user register successfully");
        LOG.info("VP: Check 'Đăng ký thành công' message display");
        ExtentReportManager.info("Step 12: Verify user register successfully");
        ExtentReportManager.info("VP: Check 'Đăng ký thành công' message display");
        CommonDialog dialog = new CommonDialog(driver);
        String recordedLoginMsg = dialog.getRegisterSuccessMessage();
        Assert.assertEquals(recordedLoginMsg, "Đăng ký thành công", "Incorrect register message !");
    }
}

