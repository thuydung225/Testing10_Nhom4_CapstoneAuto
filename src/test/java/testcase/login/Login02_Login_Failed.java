package testcase.login;

import base.BaseTest;
import constants.constantsURL;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.dialog.CommonDialog;
import report.ExtentReportManager;

public class Login02_Login_Failed extends BaseTest {

    @Test(description = "Login with EmptyPassword")
    public void Login02_Login_Failed(){

        String username = "7a12a27e-7156-4cd5-a9b9-fee4bb5732bc";

        WebDriver driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);

        //Step 1: Go to https://demo6.cybersoft.edu.vn/
        LOG.info("Step 1: Open website");
        ExtentReportManager.info("Step 1: go to https://demo6.cybersoft.edu.vn/");
        driver.get(constantsURL.BASE_URL);

        // Step 2: Navigate Login page
        LOG.info("Step 2: Navigate Login page");
        ExtentReportManager.info("Step 2: Navigate Login page");
        homePage.getTopBarNavigation().navigateLoginPage();
        LoginPage loginPage = new LoginPage(driver);

        // Step 3: Enter username ONLY
        LOG.info("Step 3: Enter username");
        ExtentReportManager.info("Step 3: Enter username");
        loginPage.enterAccount(username);

        // Step 4: Click login
        LOG.info("Step 4: Click Login button");
        ExtentReportManager.info("Step 4: Click Login button");
        loginPage.clickLogin();

        // Step 5: Verify error message
        //VP: Check 'Vui lòng nhập mật khẩu' message display
        LOG.info("Step 5: Verify login failed message");
        LOG.info("VP: Check 'Vui lòng nhập mật khẩu' message display");
        ExtentReportManager.info("Step 5: Verify login failed message");
        ExtentReportManager.info("VP: Check 'Vui lòng nhập mật khẩu' message display");
        CommonDialog dialog = new CommonDialog(driver);
        String errorMsg = dialog.getLoginTextMessage();
        Assert.assertEquals(
                errorMsg,
                "Vui lòng nhập mật khẩu",
                "Incorrect error message!"
        );
    }
}