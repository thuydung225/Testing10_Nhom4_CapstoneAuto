package testcase.login;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.dialog.CommonDialog;
import report.ExtentReportManager;

public class Login3_Login_With_Incomplete_Data extends BaseTest {

    @Test(
            dataProvider = "loginIncompleteData",
            description = "Verify login failed case with invalid and missing data")
    public void Login03_Login_Invalid_Cases(
            String testName,
            String username,
            String password,
            String expectedType,
            String expectedMessage
    ) throws InterruptedException {

        LOG.info("===== RUNNING: " + testName + " =====");
        ExtentReportManager.info("===== RUNNING: " + testName + " =====");

        LOG.info("Test data → username: " + username +
                " | password: " + password +
                " | type: " + expectedType);

        WebDriver driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);

        // Step 1: Open website
        LOG.info("Step 1: Open website");
        ExtentReportManager.info("Step 1: Open website");
        driver.get("https://demo6.cybersoft.edu.vn/");

        // Step 2: Navigate Login page
        LOG.info("Step 2: Navigate Login page");
        ExtentReportManager.info("Step 2: Navigate Login page");
        homePage.getTopBarNavigation().navigateLoginPage();
        LoginPage loginPage = new LoginPage(driver);

        // Step 3: Input data
        LOG.info("Step 3: Input data");
        ExtentReportManager.info("Step 3: Input data");

        if (username != null && !username.isEmpty()) {
            loginPage.enterAccount(username);
        }

        if (password != null && !password.isEmpty()) {
            loginPage.enterPassword(password);
        }

        // Step 4: Click login
        LOG.info("Step 4: Click login");
        ExtentReportManager.info("Step 4: Click login");
        loginPage.clickLogin();

        // Step 5: Verify
        LOG.info("Step 5: Verify result");
        ExtentReportManager.info("Step 5: Verify result");

        switch (expectedType) {

            case "EMPTY_PASSWORD":
                Assert.assertEquals(
                        loginPage.getPasswordValidationMessage(),
                        expectedMessage,
                        "Incorrect validation message!"
                );
                break;

            case "EMPTY_USERNAME":
                Assert.assertEquals(
                        loginPage.getUsernameValidationMessage(),
                        expectedMessage,
                        "Incorrect validation message!"
                );
                break;

            case "INVALID_PASSWORD":
            case "INVALID_USERNAME":
                String actualMsg = new CommonDialog(driver).getLoginFailedMessage();
                Assert.assertEquals(
                        actualMsg.trim(),
                        expectedMessage,
                        "Incorrect error message!"
                );
                break;
        }
    }

    @DataProvider(name = "loginIncompleteData")
    public Object[][] loginInvalidData() {
        return new Object[][]{
                {
                        "Login01_EmptyPassword",
                        "7a12a27e-7156-4cd5-a9b9-fee4bb5732bc",
                        "",
                        "EMPTY_PASSWORD",
                        "Please fill out this field."
                },
                {
                        "Login02_EmptyUsername",
                        "",
                        "Test123456@",
                        "EMPTY_USERNAME",
                        "Please fill out this field."
                },
                {
                        "Login03_InvalidPassword",
                        "7a12a27e-7156-4cd5-a9b9-fee4bb5732bc",
                        "Test12346@",
                        "INVALID_PASSWORD",
                        "Đăng nhập thất bại. Vui lòng kiểm tra lại thông tin."
                },
                {
                        "Login04_InvalidUsername",
                        "wrong_user_123",
                        "Test123456@",
                        "INVALID_USERNAME",
                        "Đăng nhập thất bại. Vui lòng kiểm tra lại thông tin."
                }
        };
    }
}