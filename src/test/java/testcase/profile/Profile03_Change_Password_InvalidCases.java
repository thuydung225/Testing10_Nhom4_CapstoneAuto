package testcase.profile;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;
import report.ExtentReportManager;

public class Profile03_Change_Password_InvalidCases extends BaseTest {

    private final String account = "user12345";
    private final String DEFAULT_PASSWORD = "user12345";
    private final String NEW_PASSWORD = "user54321";

    private String currentPassword;

    private WebDriver driver;
    private HomePage homePage;
    private ProfilePage profilePage;

    // ================= TEARDOWN =================
    @AfterMethod
    public void tearDown() {
        if (homePage != null) {

            LOG.info("Step: Back to homepage");
            ExtentReportManager.info("Step: Back to homepage");

            driver.get("https://demo6.cybersoft.edu.vn/");

            if (homePage.getTopBarNavigation().isUserLoggedIn()) {
                LOG.info("Step: Logout after test");
                ExtentReportManager.info("Step: Logout after test");

                homePage.getTopBarNavigation().navigateLogout();
            }
        }
    }

    // ================= SETUP =================
    @BeforeMethod
    public void setupState() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);

        LOG.info("Step 1: Ensure user is logged in");
        ExtentReportManager.info("Step 1: Ensure user is logged in");

        ensurePasswordIsCorrect();

        LOG.info("Step 2: Navigate to Profile page");
        ExtentReportManager.info("Step 2: Navigate to Profile page");

        homePage.getTopBarNavigation().openDropdownMenu();
        homePage.getTopBarNavigation().navigateAccountInfoPage();

        LOG.info("Step 3: Open Change Password form");
        ExtentReportManager.info("Step 3: Open Change Password form");

        profilePage = new ProfilePage(driver);
        profilePage.clickChangePassword();
    }

    // ================= DATA =================
    @DataProvider(name = "invalidPasswordData")
    public Object[][] invalidPasswordData() {
        return new Object[][]{
                {"wrongPass", "new123", "new123", "Current password is incorrect"},
                {"", "new123", "new123", "Current password is incorrect"},
                {"user12345", "new123", "abc123", "Mật khẩu mới không khớp"}
        };
    }

    // ================= TEST =================
    @Test(dataProvider = "invalidPasswordData")
    public void changePassword_InvalidCases(
            String current,
            String newPass,
            String confirm,
            String expectedMessage) {

        LOG.info("Step 4: Input password data");
        ExtentReportManager.info("Step 4: Input password data");

        LOG.info("Data → Current: " + current +
                " | New: " + newPass +
                " | Confirm: " + confirm);

        // Disable HTML5 validation
        LOG.info("Step 5: Disable HTML5 validation");
        ExtentReportManager.info("Step 5: Disable HTML5 validation");
        profilePage.disableHtml5Validation();

        // Input data
        LOG.info("Step 6: Enter Current, New, Confirm password");
        ExtentReportManager.info("Step 6: Enter Current, New, Confirm password");
        profilePage.inputChangePassword(current, newPass, confirm);

        // Get error message
        LOG.info("Step 7: Get error message");
        ExtentReportManager.info("Step 7: Get error message");
        String actualMessage = profilePage.getErrorMessage();

        LOG.info("Actual message: " + actualMessage);

        // Verify
        LOG.info("Step 8: Verify error message");
        ExtentReportManager.info("Step 8: Verify error message");

        Assert.assertTrue(
                actualMessage.toLowerCase().contains(expectedMessage.toLowerCase()),
                "Expected: " + expectedMessage + " | Actual: " + actualMessage
        );

        LOG.info("Test completed");
        ExtentReportManager.info("Test completed");
    }

    // ================= CORE LOGIN LOGIC =================
    private void ensurePasswordIsCorrect() {

        LoginPage loginPage = new LoginPage(driver);

        LOG.info("Ensure correct password for login");

        driver.get("https://demo6.cybersoft.edu.vn/");

        // check login
        if (homePage.getTopBarNavigation().isUserLoggedIn()) {
            LOG.info("Already logged in → skip login");
            return;
        }

        LOG.info("Step: Navigate to Login page");
        ExtentReportManager.info("Step: Navigate to Login page");
        homePage.getTopBarNavigation().navigateLoginPage();

        // TRY DEFAULT
        LOG.info("Step: Try login with DEFAULT password");
        ExtentReportManager.info("Step: Try login with DEFAULT password");

        if (loginPage.loginAndCheckSuccess(account, DEFAULT_PASSWORD)) {

            LOG.info("Login successful with DEFAULT password");
            homePage.waitForPageStable();
            currentPassword = DEFAULT_PASSWORD;
            return;
        }

        // TRY NEW
        LOG.info("Step: Try login with NEW password");
        ExtentReportManager.info("Step: Try login with NEW password");

        driver.get("https://demo6.cybersoft.edu.vn/");
        homePage.getTopBarNavigation().navigateLoginPage();

        if (loginPage.loginAndCheckSuccess(account, NEW_PASSWORD)) {

            LOG.info("Login successful with NEW password");
            homePage.waitForPageStable();
            currentPassword = NEW_PASSWORD;
            return;
        }

        LOG.error("Cannot login with both passwords");
        Assert.fail("Login failed with both DEFAULT and NEW password");
    }
}