package testcase.profile;

import base.BaseTest;
import constants.constantsURL;
import drivers.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;
import report.ExtentReportManager;

public class Profile02_Change_Password extends BaseTest {
    private final String account = "user12345";
    private final String DEFAULT_PASSWORD = "user12345";
    private final String NEW_PASSWORD = "user54321";
    private String currentPassword;
    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setupState() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);

        ensurePasswordIsCorrect(); // đã login xong
    }
    @Test(description = "Verify that the user can change their password successfully from the profile page")
    public void profile02_Change_Password(){

        //Step 4: Open profile page
        LOG.info("Step 4: Open profile page");
        ExtentReportManager.info("Step 4: Open profile page");
        homePage.getTopBarNavigation().openDropdownMenu();
        homePage.getTopBarNavigation().navigateAccountInfoPage();

        //Step 5: Click 'Đổi mật khẩu'
        LOG.info("Step 5: Click 'Đổi mật khẩu'");
        ExtentReportManager.info("Step 5: Click 'Đổi mật khẩu'");
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickChangePassword();

        //Step 6: Enter CurrentPassword
        //Step 7: Enter NewPassword
        //Step 8: Enter ConfirmPassword
        LOG.info("Step 6: Enter CurrentPassword");
        LOG.info("Step 7: Enter NewPassword");
        LOG.info("Step 8: Enter ConfirmPassword");
        ExtentReportManager.info("Step 6: Enter CurrentPassword");
        ExtentReportManager.info("Step 7: Enter NewPassword");
        ExtentReportManager.info("Step 8: Enter ConfirmPassword");
        profilePage.changePassword(currentPassword, NEW_PASSWORD);

        //Step 9: Verify success message
        LOG.info("Step 9: Verify change password success");
        ExtentReportManager.info("Step 9: Verify change password success");
        Assert.assertTrue(profilePage.getSuccessMessage().contains("Đổi mật khẩu thành công"),
                "Change password failed");

        //Step 10: Logout
        LOG.info("Step 10: Logout");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        homePage.getTopBarNavigation().navigateLogout();


        //Step 11: Login again with new password
        LOG.info("Step 11: Login again with new password");
        ExtentReportManager.info("Step 11: Login again with new password");
        LoginPage loginPage2 = new LoginPage(driver);
        Assert.assertTrue(
                loginPage2.loginAndCheckSuccess(account, NEW_PASSWORD),
                "Login with new password failed"
        );

        homePage.waitForPageStable();
    }

    // ================= CORE LOGIC =================
    private void ensurePasswordIsCorrect() {

        LoginPage loginPage = new LoginPage(driver);

        LOG.info("Ensure correct password for login");

        driver.get(constantsURL.BASE_URL);
        homePage.getTopBarNavigation().navigateLoginPage();

        // ===== TRY DEFAULT =====
        if (loginPage.loginAndCheckSuccess(account, DEFAULT_PASSWORD)) {

            LOG.info("Login successful with DEFAULT password");
            homePage.waitForPageStable();
            currentPassword = DEFAULT_PASSWORD;
            return;
        }

        // ===== TRY NEW =====
        driver.get("https://demo6.cybersoft.edu.vn/");
        homePage.getTopBarNavigation().navigateLoginPage();

        if (loginPage.loginAndCheckSuccess(account, NEW_PASSWORD)) {

            LOG.info("Login successful with NEW password");
            homePage.waitForPageStable();
            currentPassword = NEW_PASSWORD;
            return;
        }

        // ===== FAIL =====
        LOG.error("Cannot login with either DEFAULT or NEW password!");
        Assert.fail("Login failed with both DEFAULT and NEW password");
    }
}
