package pages.dialog;

import base.BasePage;
import constants.WaitTimeOut;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonDialog extends BasePage {

    private By byLblMsgRequestLogin = By.xpath("//div[contains(text(),'đăng nhập')]");
    private By byLblMsgLoginSuccess = By.xpath("//div[contains(text(),'Đăng nhập thành công')]");
    private By byToastMessage = By.cssSelector(".toast-body");
    private By byLblRegisterError = By.cssSelector(".Toastify__toast--error");
    private By byLblRegisterSuccess = By.xpath("//div[contains(text(),'Registration successful')]");

    public CommonDialog(WebDriver driver) {
        super(driver);
    }

    public String getTextRequestLoginMessage() {
        return getText(byLblMsgRequestLogin);
    }

    public String getLoginTextMessage(){
        return getText(byLblMsgLoginSuccess, WaitTimeOut.DEFAULT_TIMEOUT);
    }

    public String getLoginFailedMessage(){
        return getText(byToastMessage,WaitTimeOut.DEFAULT_TIMEOUT);
    }

    public String getRegisterSuccessMessage() {
        return waitForVisibilityOfElementLocated(byLblRegisterSuccess, WaitTimeOut.DEFAULT_TIMEOUT).getText();
    }

    public String getRegisterErrorMessage() {
        return waitForVisibilityOfElementLocated(byLblRegisterError,WaitTimeOut.DEFAULT_TIMEOUT)
                .getText();
    }

}
