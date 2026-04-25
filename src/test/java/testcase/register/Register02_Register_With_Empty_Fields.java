package testcase.register;

import base.BaseTest;
import constants.constantsURL;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;
import report.ExtentReportManager;

public class Register02_Register_With_Empty_Fields extends BaseTest {

    @Test(dataProvider = "registerEmptyData")
    public void Register_Empty_Fields(
            String testName,
            String description,
            String username,
            String email,
            String password,
            String expectedField
    ) {

        LOG.info("===== RUNNING: " + testName + " =====");
        LOG.info("Description: " + description);

        ExtentReportManager.info("===== RUNNING: " + testName + " =====");
        ExtentReportManager.info("Description: " + description);

        WebDriver driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);

        //Step 1: go to https://demo1.cybersoft.edu.vn/
        LOG.info("Step 1: Go to https://demo6.cybersoft.edu.vn/");
        ExtentReportManager.info("Step 1: Go to https://demo6.cybersoft.edu.vn/");
        driver.get(constantsURL.BASE_URL);

        // Step 2: Navigate Register page
        LOG.info("Step 2: Navigate Register page");
        ExtentReportManager.info("Step 2: Navigate Register page");
        homePage.getTopBarNavigation().navigateRegisterPage();
        RegisterPage registerPage = new RegisterPage(driver);

        // Step 3: Input data (chỉ nhập nếu có)
        LOG.info("Step 3: Input data (chỉ nhập nếu có)");
        ExtentReportManager.info("Step 3: Input data (chỉ nhập nếu có)");
        if (username != null) {
            registerPage.enterUsername(username);
        }

        if (email != null) {
            registerPage.enterYourEmail(email);
        }

        if (password != null) {
            registerPage.enterYourPassword(password);
            registerPage.enterConfirmPassword(password);
        }

        // Input the others fields
        registerPage.enterFullname("Trinh Van T");
        registerPage.enterPhoneNumber("0386580855");
        registerPage.enterDateOfBirth("08/15/2002");
        registerPage.selectGender("Female");

        // Step 4: Click Register
        LOG.info("Step 4: Click Register");
        ExtentReportManager.info("Step 4: Click Register");
        registerPage.clickRegister();

        // Step 5: Verify validation
        LOG.info("Step 5: Verify validation");
        ExtentReportManager.info("Step 5: Verify validation");
        String actualMessage = "";

        switch (expectedField) {
            case "USERNAME":
                actualMessage = registerPage.getUsernameValidationMessage();
                break;

            case "PASSWORD":
                actualMessage = registerPage.getPasswordValidationMessage();
                break;

            case "EMAIL":
                actualMessage = registerPage.getEmailValidationMessage();
                break;
        }

        Assert.assertEquals(
                actualMessage,
                "Please fill out this field.",
                "Validation message incorrect!"
        );
    }

    @DataProvider(name = "registerEmptyData")
    public Object[][] registerEmptyData() {
        return new Object[][]{

                {
                        "RG_01_Empty_All",
                        "All fields empty",
                        null,
                        null,
                        null,
                        "USERNAME"
                },

                {
                        "RG_02_Empty_Username",
                        "Empty username",
                        null,
                        "test006@gmail.com",
                        "Test006@abc",
                        "USERNAME"
                },

                {
                        "RG_03_Empty_Password",
                        "Empty password",
                        "TestT111",
                        "test006@gmail.com",
                        null,
                        "PASSWORD"
                },

                {
                        "RG_04_Empty_Email",
                        "Empty email",
                        "TestT111",
                        null,
                        "Test006@abc",
                        "EMAIL"
                }
        };
    }
}