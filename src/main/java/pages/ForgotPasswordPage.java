package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends CommonPage {
    private By byTxtInputEmail = By.xpath("//input[@type='email']");
    private By byBtnResetPassword = By.xpath("//button[@type='submit']");
    private By successMessage = By.xpath("//h3[contains(text(),'Email Đã Được Gửi')]");
    private By byErrorMgsEmail = By.xpath("//div[contains(@class,'alert-danger')]");
    private By byBtnResendEmail = By.xpath("//button[normalize-space()='Gửi lại email']");
    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }
    public void enterEmail(String email){
        sendKeys(byTxtInputEmail,email);
    }

    public void clickResetPassword(){
        click(byBtnResetPassword,3000);
    }
    public boolean isSendEmailSuccessMessageDisplayed(){
        return isElementDisplayed(successMessage);
    }
    public String getErrorMessageEmail() {
        waitForVisibilityOfElementLocated(byErrorMgsEmail, 5);
        return getText(byErrorMgsEmail);
    }

    public void clickResendPassword(){
        click(byBtnResendEmail);
    }

    public boolean isEmailInputDisplayed(){
        return isElementDisplayed(byTxtInputEmail);
    }

}
