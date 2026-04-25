package testcase.forgotpassword;

import base.BaseTest;
import constants.constantsURL;
import drivers.DriverFactory;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;
import report.ExtentReportManager;

import java.time.Duration;

public class ForgotPassword01_Send_Reset_Link_Successfully extends BaseTest {

    @Test(description = "Verify that user can send reset password link successfully with valid email")
    public void ForgotPassword01_Send_Reset_Link_Successfully(){
        String email = "nttvan204@gmail.com";
        WebDriver driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);

        //Step 1: Go to https://demo6.cybersoft.edu.vn/
        LOG.info("Step 1: Go to https://demo6.cybersoft.edu.vn/");
        ExtentReportManager.info("Step 1: Go to https://demo6.cybersoft.edu.vn/");
        driver.get(constantsURL.BASE_URL);

        //Step 2: Click 'Đăng nhập' link on the top right
        LOG.info("Step 2: Click 'Đăng nhập' link on the top right");
        ExtentReportManager.info("Step 2: Click 'Đăng nhập' link on the top right");
        homePage.getTopBarNavigation().navigateLoginPage();

        //Step 3: Click 'Quên mật khẩu'
        LOG.info("Step 3: Click 'Quên mật khẩu'");
        ExtentReportManager.info("Step 3: Click 'Quên mật khẩu'");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPassword();

        //Step 4: Enter Email
        LOG.info("Step 4: Enter Email");
        ExtentReportManager.info("Step 4: Enter Email");
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.enterEmail(email);

        //Step 5: Click 'Gửi Link đặt lại mật khẩu'
        LOG.info("Step 5: Click 'Gửi Link đặt lại mật khẩu'");
        ExtentReportManager.info("Step 5: Click 'Gửi Link đặt lại mật khẩu'");
        forgotPasswordPage.clickResetPassword();

        // VP1 : Verify gửi email thành công
        LOG.info("VP1 : Verify gửi email thành công");
        Assert.assertTrue(forgotPasswordPage.isSendEmailSuccessMessageDisplayed(),
                "Không hiển thị message gửi email thành công");

    }

}
