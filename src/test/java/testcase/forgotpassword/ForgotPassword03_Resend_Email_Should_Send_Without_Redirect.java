package testcase.forgotpassword;

import base.BaseTest;
import constants.constantsURL;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;
import report.ExtentReportManager;

public class ForgotPassword03_Resend_Email_Should_Send_Without_Redirect extends BaseTest {
    @Test(description = "Verify resend email should send directly without redirect (BUG EXPECTED)")
    public void ForgotPassword03_Resend_Email_Should_Send_Without_Redirect() {
        String email = "test@gmail.com";
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

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        // ================= STEP 4 =================
        LOG.info("Step 4: Enter email and send reset link");
        ExtentReportManager.info("Step 4: Enter email and send reset link");

        forgotPasswordPage.enterEmail(email);
        forgotPasswordPage.clickResetPassword();

        // ================= STEP 5 =================
        LOG.info("Step 5: Verify success screen is displayed");
        ExtentReportManager.info("Step 5: Verify success screen is displayed");

        Assert.assertTrue(
                forgotPasswordPage.isSendEmailSuccessMessageDisplayed(),
                "Không hiển thị màn hình gửi email thành công"
        );

        // ================= STEP 6 =================
        LOG.info("Step 6: Click 'Resend Email'");
        ExtentReportManager.info("Step 6: Click 'Resend Email'");

        forgotPasswordPage.clickResendPassword();

        // ================= STEP 7 =================
        LOG.info("Step 7: Verify system stays on success screen");
        ExtentReportManager.info("Step 7: Verify system stays on success screen");

        boolean isStillOnSuccess = forgotPasswordPage.isSendEmailSuccessMessageDisplayed();

        // ================= STEP 8 =================
        LOG.info("Step 8: Verify user is NOT redirected to email input form");
        ExtentReportManager.info("Step 8: Verify user is NOT redirected to email input form");

        boolean isBackToForm = forgotPasswordPage.isEmailInputDisplayed();

        LOG.info("isStillOnSuccess = " + isStillOnSuccess);
        LOG.info("isBackToForm = " + isBackToForm);

        // Verify chuẩn hơn
        Assert.assertTrue(
                isStillOnSuccess && !isBackToForm,
                "BUG: Click Resend Email nhưng hệ thống redirect.\n" +
                        "Actual → isStillOnSuccess: " + isStillOnSuccess +
                        ", isBackToForm: " + isBackToForm
        );
        // ================= STEP 9 =================
        LOG.info("Step 9: Test completed");
        ExtentReportManager.info("Step 9: Test completed");
    }
}

