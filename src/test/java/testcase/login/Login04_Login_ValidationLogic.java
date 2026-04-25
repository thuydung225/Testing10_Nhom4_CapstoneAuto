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

public class Login04_Login_ValidationLogic extends BaseTest {

    @Test(description = "LG_014 - Username chỉ 1 ký tự")
    public void Login014_Login_Failed_InvalidUsernameLength() {

        String username = "1";
        String password = "1";

        WebDriver driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);

        //Step 1: Go to https://demo6.cybersoft.edu.vn/
        LOG.info("Step 1: Open website");
        ExtentReportManager.info("Step 1: go to https://demo6.cybersoft.edu.vn/");
        driver.get(constantsURL.BASE_URL);

        //Step 2: Navigate Login page
        LOG.info("Step 2: Navigate Login page");
        ExtentReportManager.info("Step 2: Navigate Login page");
        homePage.getTopBarNavigation().navigateLoginPage();

        LoginPage loginPage = new LoginPage(driver);

        //Step 3: Enter username (1 character)
        LOG.info("Step 3: Enter username (1 character)");
        ExtentReportManager.info("Step 3: Enter username (1 character)");
        loginPage.enterAccount(username);

        //Step 4: Enter password
        LOG.info("Step 4: Enter password");
        ExtentReportManager.info("Step 4: Enter password");
        loginPage.enterPassword(password);

        //Step 5: Click Login
        LOG.info("Step 5: Click Login");
        ExtentReportManager.info("Step 5: Click Login");
        loginPage.clickLogin();

        //Step 6: Verify error message
        //VP: Check 'Đăng nhập không thành công. Vui lòng nhập lại!' message display
        LOG.info("Step 6: Verify error message");
        LOG.info("VP: Check 'Đăng nhập không thành công. Vui lòng nhập lại!' message display");
        ExtentReportManager.info("Step 6: Verify error message");
        ExtentReportManager.info("VP: Check 'Đăng nhập không thành công. Vui lòng nhập lại!' message display");

        CommonDialog dialog = new CommonDialog(driver);

        String actualMessage = dialog.getLoginTextMessage();

        Assert.assertEquals(
                actualMessage,
                "Đăng nhập không thành công. Vui lòng nhập lại!",
                "BUG: System allowed invalid username length!"
        );
    }
    @Test(description = "LG_006 - Username chứa ký tự đặc biệt")
    public void Login006_Login_Failed_SpecialCharacterUsername() {

        String username = "lnp123@";
        String password = "123456";

        WebDriver driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);

        //Step 1: Go to https://demo6.cybersoft.edu.vn/
        LOG.info("Step 1: Go to https://demo6.cybersoft.edu.vn/");
        ExtentReportManager.info("Step 1: go to https://demo6.cybersoft.edu.vn/");
        driver.get("https://demo6.cybersoft.edu.vn/");

        //Step 2: Navigate Login page
        LOG.info("Step 2: Navigate Login page");
        ExtentReportManager.info("Step 2: Navigate Login page");
        homePage.getTopBarNavigation().navigateLoginPage();

        LoginPage loginPage = new LoginPage(driver);

        //Step 3: Enter username with special character
        LOG.info("Step 3: Enter username with special character");
        ExtentReportManager.info("Step 3: Enter username with special character");
        loginPage.enterAccount(username);

        //Step 4: Enter password
        LOG.info("Step 4: Enter password");
        ExtentReportManager.info("Step 4: Enter password");
        loginPage.enterPassword(password);

        //Step 5: Click Login
        LOG.info("Step 5: Click Login");
        ExtentReportManager.info("Step 5: Click Login");
        loginPage.clickLogin();

        //Step 6: Verify validation message
        //VP: Check 'Đăng nhập không thành công. Vui lòng nhập lại!' message display
        LOG.info("Step 6: Verify validation message");
        LOG.info("VP: Check 'Đăng nhập không thành công. Vui lòng nhập lại!' message display");
        ExtentReportManager.info("Step 6: Verify validation message");
        ExtentReportManager.info("VP: Check 'Đăng nhập không thành công. Vui lòng nhập lại!' message display");
        CommonDialog dialog = new CommonDialog(driver);

        String actualMessage = dialog.getLoginFailedMessage();

        Assert.assertEquals(
                actualMessage,
                "Đăng nhập không thành công. Vui lòng nhập lại!",
                "BUG: Special character username was accepted!"
        );
    }
}
