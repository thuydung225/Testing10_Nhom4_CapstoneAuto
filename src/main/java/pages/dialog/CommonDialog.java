package pages.dialog;

import base.BasePage;
import constants.WaitTimeOut;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonDialog extends BasePage {

    private By byLblMsgLogin = By.xpath("//div[contains(text(),'đăng nhập')]");

    public CommonDialog(WebDriver driver) {
        super(driver);
    }

    public String getTextMessage() {
        return getText(byLblMsgLogin);
    }

    public void waitDialogDisappear() {
        waitForInvisibilityOfElementLocated(byLblMsgLogin, WaitTimeOut.DEFAULT_TIMEOUT);
    }
}
