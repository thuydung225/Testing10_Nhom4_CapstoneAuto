package pages;

import constants.WaitTimeOut;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class LoginPage extends CommonPage{

    private By byTxtAccount = By.xpath("//input[@placeholder='Tên đăng nhập *']");
    private By byTxtPassword = By.xpath("//input[@placeholder='Mật khẩu *']");
    private By byBtnLogin = By.xpath("//button[contains(text(),'Đăng nhập')]");

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
        scrollToView(byBtnLogin);
        clickLogin();
    }

    public boolean isLoginPageDisplayed() {
        try {
            return waitForVisibilityOfElementLocated(byTxtAccount, WaitTimeOut.MEDIUM_TIMEOUT).isDisplayed();

        } catch (TimeoutException e) {
            return false;
        }
    }
}
