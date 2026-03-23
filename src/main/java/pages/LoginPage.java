package pages;

import constants.WaitTimeOut;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class LoginPage extends CommonPage{

    private By byTxtAccount = By.xpath("//input[@placeholder='Tên đăng nhập *']");
    private By byTxtPassword = By.xpath("//input[@placeholder='Mật khẩu *']");
    private By byBtnLogin = By.xpath("//button[contains(text(),'Đăng nhập')]");
    private By byLnkForgotPassword = By.xpath("//a[@href='/forgot-password']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterAccount(String account) {
        sendKeys(byTxtAccount, account);
    }

    public void enterPassword(String password) {
        sendKeys(byTxtPassword, password);
    }

    public void clickLogin() {
        click(byBtnLogin);
    }

    public void login(String username, String password) {
        enterAccount(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLoginPageDisplayed() {
        try {
            return waitForVisibilityOfElementLocated(byTxtAccount, WaitTimeOut.MEDIUM_TIMEOUT).isDisplayed();

        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean loginAndCheckSuccess(String username, String password) {
        login(username, password);
        return getTopBarNavigation().isUserLoggedIn();
    }

    public void clickForgotPassword(){
        click(byLnkForgotPassword);
    }

    public String getPasswordValidationMessage(){
        return driver.findElement(byTxtPassword)
                .getAttribute("validationMessage");
    }
    public String getUsernameValidationMessage(){
        return driver.findElement(byTxtAccount)
                .getAttribute("validationMessage");

    }

}
