package testcase.register;

import base.BaseTest;
import constants.constantsURL;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;
import pages.dialog.CommonDialog;
import report.ExtentReportManager;

public class Register03_Register_With_Existed_Email extends BaseTest {
    @Test //(description: Email Already Exists)
    public void RG_076_EmailAlreadyExists() {
        WebDriver driver = DriverFactory.getDriver();

        HomePage homePage = new HomePage(driver);

        //Step 1: go to https://demo1.cybersoft.edu.vn/
        LOG.info("Step 1: Go to https://demo6.cybersoft.edu.vn/");
        ExtentReportManager.info("Step 1: Go to https://demo6.cybersoft.edu.vn/");
        driver.get(constantsURL.BASE_URL);

        //Step 2: Navigate Register page
        LOG.info("Step 2: Navigate Register page");
        ExtentReportManager.info("Step 2: Navigate Register page");
        homePage.getTopBarNavigation().navigateRegisterPage();
        RegisterPage registerPage = new RegisterPage(driver);

        //Step 3: Enter Username
        LOG.info("Step 3: Enter Username");
        ExtentReportManager.info("Step 3: Enter Username");
        registerPage.enterUsername("tester_final8");

        //Step 4: Enter Full Name
        LOG.info("Step 4: Enter Full Name");
        ExtentReportManager.info("Step 4: Enter Full Name");
        registerPage.enterFullname("Tran Thi Thao");

        //Step 5: Enter duplicated Email
        LOG.info("Step 5: Enter duplicated Email");
        ExtentReportManager.info("Step 5: Enter duplicated Email");
        registerPage.enterYourEmail("test005@gmail.com");

        //Step 6: Enter Phone Number
        LOG.info("Step 6: Enter Phone Number");
        ExtentReportManager.info("Step 6: Enter Phone Number");
        registerPage.enterPhoneNumber("0944445555");

        //Step 7: Enter Password
        LOG.info("Step 7: Enter Password");
        ExtentReportManager.info("Step 7: Enter Password");
        registerPage.enterYourPassword("Test@Final2025");

        //Step 8: Enter Confirm Password
        LOG.info("Step 8: Enter Confirm Password");
        ExtentReportManager.info("Step 8: Enter Confirm Password");
        registerPage.enterConfirmPassword("Test@Final2025");

        //Step 9: Enter Date Of Birth
        LOG.info("Step 9: Enter Date Of Birth");
        ExtentReportManager.info("Step 9: Enter Date Of Birth");
        registerPage.enterDateOfBirth("05/09/2002");

        //Step 10: Select Gender
        LOG.info("Step 10: Select Gender");
        ExtentReportManager.info("Step 10: Select Gender");
        registerPage.selectGender("Female");

        //Step 11: Click Register
        LOG.info("Step 11: Click Register");
        ExtentReportManager.info("Step 11: Click Register");
        registerPage.clickRegister();

        registerPage.pause(5000);

        //Step 12: Get error message
        LOG.info("Step 12: Get error message");
        ExtentReportManager.info("Step 12: Get error message");
        CommonDialog dialog = new CommonDialog(driver);
        String actualMessage = null;

        //VP1: Check 'Email already exists' message display
        LOG.info("VP1: Check 'Email already exists' message display");
        ExtentReportManager.info("VP: Check 'Email already exists' message display");

        //VP2: Check user remain on Register page
        LOG.info("VP2: Check user remain on Register page");
        ExtentReportManager.info("VP2: Check user remain on Register page");

        try {
            actualMessage = dialog.getRegisterErrorMessage();
            LOG.info("Actual message: " + actualMessage);
            Assert.assertEquals(actualMessage, "Email already exists");
        } catch (org.openqa.selenium.TimeoutException e) {
            Assert.fail("Expected error message 'Email already exists', but user was redirected to Login page");
        }

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/register"),
                "User should remain on Register page after duplicate email attempt");
    }
}