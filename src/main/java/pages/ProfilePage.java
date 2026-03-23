package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends CommonPage {
    private By byImgAvatar = By.xpath("//div[contains(@class,'position-relative') and contains(@class,'d-inline-block')]");
    private By byUserFullName = By.xpath("//h4[@class='mb-1']");
    private By byUserEmail = By.xpath("//p[@class='text-muted mb-3']");
    private By bySectionPersonalInfo = By.xpath("//h5[@class='mb-3']");
    private By byPhone = By.xpath("//small[text()='Số điện thoại']/following-sibling::div");
    private By byAddress = By.xpath("//small[text()='Địa chỉ']/following-sibling::div");
    private By byJoinDate = By.xpath("//small[text()='Ngày tham gia']/following-sibling::div");
    private By byTabAppointment = By.xpath("//a[@data-rr-ui-event-key='appointments']");
    private By byTabWaitingList = By.xpath("//a[@data-rr-ui-event-key='waiting']");
    private By byTabPrescription = By.xpath("//a[@data-rr-ui-event-key='prescriptions']");
    private By byTabInvoice = By.xpath("//a[@data-rr-ui-event-key='invoices']");
    private By byBtnChangePassword= By.xpath("//button[contains(@class,'btn-outline-primary') and contains(.,'Đổi mật khẩu')]");
    private By byTxtCurrentPassword = By.xpath("//input[@name='currentPassword']");
    private By byTxtNewPassword = By.xpath("//input[@name='newPassword']");
    private By byTxtConfirmPassword = By.xpath("//input[@name='confirmPassword']");
    private By byBtnUpdatePassword = By.xpath("//button[@type='submit' and contains(.,'Cập nhật mật khẩu')]");
    private By bySuccessAlert = By.xpath("//div[@role='alert' and contains(@class,'alert-success')]");
    private By byErrorMegs = By.xpath("//div[@role='alert' and contains(@class, 'alert-danger')]");
    public ProfilePage(WebDriver driver){
        super(driver);
    }
    public boolean isProfilePageDisplayed(){
        return  isElementDisplayed(byImgAvatar);
    }

    public boolean isAvatarDisplayed() {
        return isElementDisplayed(byImgAvatar);
    }

    public String getUserFullname(){
        return getText(byUserFullName);
    }

    public String getUserEmail(){
        return getText(byUserEmail);
    }

    public boolean isPersonalInfoDisplayed() {
        return isElementDisplayed(bySectionPersonalInfo);
    }

    public String getPhone() {
        return getText(byPhone);
    }

    public boolean isAddressDisplayed() {
        return driver.findElement(byAddress).isDisplayed();
    }

    public String getJoinDate() {
        return getText(byJoinDate);
    }

    public void openAppointmentTab() {
        click(byTabAppointment);
    }

    public void openWaitingListTab() {
        click(byTabWaitingList);
    }

    public void openPrescriptionTab() {
        click(byTabPrescription);
    }

    public void openInvoiceTab() {
        click(byTabInvoice);
    }


    public void clickChangePassword() {
        click(byBtnChangePassword);
    }

    public void enterCurrentPassword(String currenPassword){
        sendKeys(byTxtCurrentPassword,currenPassword);
    }

    public void enterNewPassword(String newPassword){
        sendKeys(byTxtNewPassword,newPassword);
    }

    public void enterConfirmPassword(String confirmPassword){
        sendKeys(byTxtConfirmPassword,confirmPassword);
    }

    public void clickUpdatePassword(){
        click(byBtnUpdatePassword);
    }
    public String getSuccessMessage(){
        return getText(bySuccessAlert);
    }

    public void changePassword(String currentPassword, String newPassword ){
        enterCurrentPassword(currentPassword);
        enterNewPassword(newPassword);
        enterConfirmPassword(newPassword);
        clickUpdatePassword();
    }

    public String getErrorMessage() {
        waitForVisibilityOfElementLocated(byErrorMegs, 5);
        return getText(byErrorMegs);
    }



    public void inputChangePassword(String current, String newPass, String confirm) {
        clearAndSendKeys(byTxtCurrentPassword, current);
        clearAndSendKeys(byTxtNewPassword, newPass);
        clearAndSendKeys(byTxtConfirmPassword, confirm);
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelector('input[name=\"currentPassword\"]')" +
                        ".closest('form').setAttribute('novalidate','true');"
        );
        clickUpdatePassword();
    }
}
