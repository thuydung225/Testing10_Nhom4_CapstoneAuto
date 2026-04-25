package testcase.forgotpassword;

import base.BaseTest;
import constants.constantsURL;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;
import report.ExtentReportManager;

public class ForgotPassword02_Send_Email_Invalid extends BaseTest {

    @DataProvider(name = "invalidEmails")
    public Object[][] invalidEmails() {
        return new Object[][]{
                {"", "Vui lòng nhập email của bạn"},
                {"abc", "Invalid email"},
                {"abc@", "Invalid email"},
                {"@gmail.com", "Invalid email"},
                {"abc@gmail", "Invalid email"},
                {"abc@gmail.", "Invalid email"},
                {"abc@.com", "Invalid email"},
        };
    }

    @Test(
            dataProvider = "invalidEmails",
            description = "Verify error message when user enters invalid email"
    )
    public void ForgotPassword02_Send_Link_Invalid_Email(String email, String expectedError) {

        WebDriver driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);

        // ================= STEP 1 =================
        LOG.info("Step 1: Navigate to homepage");
        ExtentReportManager.info("Step 1: Navigate to homepage");
        driver.get(constantsURL.BASE_URL);

        // ================= STEP 2 =================
        LOG.info("Step 2: Navigate to Login page");
        ExtentReportManager.info("Step 2: Navigate to Login page");
        homePage.getTopBarNavigation().navigateLoginPage();

        // ================= STEP 3 =================
        LOG.info("Step 3: Click 'Forgot Password'");
        ExtentReportManager.info("Step 3: Click 'Forgot Password'");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPassword();

        // ================= STEP 4 =================
        LOG.info("Step 4: Disable HTML5 validation");
        ExtentReportManager.info("Step 4: Disable HTML5 validation");

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.disableHtml5Validation();

        // ================= STEP 5 =================
        LOG.info("Step 5: Input email");
        ExtentReportManager.info("Step 5: Input email");

        LOG.info("Test Data → Email: [" + email + "]");
        ExtentReportManager.info("Test Data → Email: [" + email + "]");

        forgotPasswordPage.enterEmail(email);

        // ================= STEP 6 =================
        LOG.info("Step 6: Click 'Send Reset Link'");
        ExtentReportManager.info("Step 6: Click 'Send Reset Link'");
        forgotPasswordPage.clickResetPassword();

        // ================= STEP 7 =================
        LOG.info("Step 7: Get error message");
        ExtentReportManager.info("Step 7: Get error message");

        String actualMessage = forgotPasswordPage.getErrorMessageEmail();

        LOG.info("Actual message: " + actualMessage);
        ExtentReportManager.info("Actual message: " + actualMessage);

        // ================= STEP 8 =================
        LOG.info("Step 8: Verify error message");
        ExtentReportManager.info("Step 8: Verify error message");

        Assert.assertTrue(
                actualMessage.toLowerCase().contains(expectedError.toLowerCase()),
                "Expected: " + expectedError + " | Actual: " + actualMessage
        );

        // ================= STEP 9 =================
        LOG.info("Step 9: Test completed");
        ExtentReportManager.info("Step 9: Test completed");
    }
}
