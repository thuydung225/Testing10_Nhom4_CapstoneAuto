package testcase.login;

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
import pages.dialog.CommonDialog;
import report.ExtentReportManager;

import java.time.Duration;


public class Login01_Verify_Login_Successfully extends BaseTest {

    @Test(description = "Verify that user can login with valid account")
    public void Login01_Login_Successfully() {

        String username = "7a12a27e-7156-4cd5-a9b9-fee4bb5732bc";
        String password = "Test123456@";

        WebDriver driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);

        //Step 1: go to https://demo6.cybersoft.edu.vn/
        LOG.info("Step 1: go to https://demo6.cybersoft.edu.vn/");
        ExtentReportManager.info("Step 1: go to https://demo6.cybersoft.edu.vn/");
        driver.get("https://demo6.cybersoft.edu.vn/");

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class);

        //Step 2: Click "Dang nhap" link on the top right
        LOG.info("Step 2: Click \"Dang Nhap\" link on the top right");
        ExtentReportManager.info("Step 2: Click \"Dang Nhap\" link on the top right");

        homePage.getTopBarNavigation().navigateLoginPage();

        LOG.info("Step 3: Enter username");
        ExtentReportManager.info("Step 3: Enter username");

        LOG.info("Step 4: Enter password");
        ExtentReportManager.info("Step 4: Enter password");

        LOG.info("Step 5: Click \"Dang Nhap\" button");
        ExtentReportManager.info("Step 5: Click \"Dang Nhap\" button");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password );

        //Step 6: Verify user login successfully
        //VP: Check 'Đăng nhập thành công' message display
        LOG.info("Step: 6 Verify user login successfully");
        LOG.info("VP: Check 'Đăng nhập thành công' message display");
        ExtentReportManager.info("Step 6: Verify user login successfully");
        ExtentReportManager.info("VP: Check 'Đăng nhập thành công' message display");
        CommonDialog dialog = new CommonDialog(driver);
        String recordedLoginMsg = dialog.getLoginTextMessage();
        Assert.assertEquals(recordedLoginMsg, "Đăng nhập thành công", "Incorrect login message !");
    }
}
