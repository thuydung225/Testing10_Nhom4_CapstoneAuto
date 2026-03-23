package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends CommonPage{
    private By byTxtUsername = By.xpath("//input[@name='username']");
    private By byTxtFullname = By.xpath("//input[@name='fullName']");
    private By byTxtYourEmail = By.xpath("//input[@name='email']");
    private By byTxtPhoneNumber = By.xpath("//input[@name='phone']");
    private By byTxtYourPassword = By.xpath("//input[@name='password']");
    private By byTxtConfirmPassword = By.xpath("//input[@name='confirmPassword']");
    private By byTxtBirth = By.xpath("//input[@name='dateOfBirth']");
    private By bySelGender = By.id("gender");
    private By byBtnRegister = By.xpath("//button[@type='submit']");

    public RegisterPage(WebDriver driver){
        super(driver);
    }

    public void enterUsername(String username){
        sendKeys(byTxtUsername,username);
    }

    public void enterFullname(String fullname){
        sendKeys(byTxtFullname,fullname);
    }

    public void enterYourEmail(String y_email){
        sendKeys(byTxtYourEmail,y_email);
    }

    public void enterPhoneNumber(String phone_number){
        sendKeys(byTxtPhoneNumber,phone_number);
    }

    public void enterYourPassword(String password){
        sendKeys(byTxtYourPassword,password);
    }

    public void enterConfirmPassword(String password){
        sendKeys(byTxtConfirmPassword,password);
    }

    public void enterDateOfBirth(String dob){
        sendKeys(byTxtBirth, dob);
    }

    public void selectGender(String gender){
        selectDropdownByText(bySelGender, gender);
    }

    public String getUsernameValidationMessage() {
        return getValidationMessage(byTxtUsername);
    }

    public String getFullNameValidationMessage() {
        return getValidationMessage(byTxtFullname);
    }

    public String getEmailValidationMessage() {
        return getValidationMessage(byTxtYourEmail);
    }

    public String getPhoneValidationMessage() {
        return getValidationMessage(byTxtPhoneNumber);
    }

    public String getPasswordValidationMessage() {
        return getValidationMessage(byTxtYourPassword);
    }

    public String getConfirmPasswordValidationMessage() {
        return getValidationMessage(byTxtConfirmPassword);
    }

    public void clickRegister() {
        click(byBtnRegister);
    }

    public void register(String username, String password, String fullname,
                         String email, String phone, String dob, String gender){

        enterUsername(username);
        enterFullname(fullname);
        enterYourEmail(email);
        enterPhoneNumber(phone);
        enterYourPassword(password);
        enterConfirmPassword(password);
        enterDateOfBirth(dob);
        selectGender(gender);
        scrollToElement(byBtnRegister);
        clickRegister();
    }

}
