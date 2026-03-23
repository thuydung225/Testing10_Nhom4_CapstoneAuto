package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopBarNavigation extends BasePage {

    private By byLnkRegister = By.xpath("//a[@href='/register']");
    private By byLnkLogin = By.xpath("//a[@href='/login']");
    private By byLnkBooking = By.xpath("//a[contains(text(),'Đặt lịch khám')]");
    private By bySelAvatar = By.xpath("//div[contains(@class,'user-dropdown')]//button");
    private By byMenuAccountInfo = By.xpath("//a[@href='/user-info']");
    private By byMenuAppointment = By.xpath("//a[@href='/user-info?tab=appointments']");
    private By byMenuPrescription = By.xpath("//a[@href='/user-info?tab=prescriptions']");
    private By byMenuInvoice = By.xpath("//a[@href='/user-info?tab=invoices']");
    private By byMenuLogout = By.xpath("//button[contains(@class,'logout-item')]");


    public TopBarNavigation(WebDriver driver) {
        super(driver);
    }

    public void navigateRegisterPage(){
        click(byLnkRegister);
    }

    public void navigateLoginPage() {
        click(byLnkLogin);
    }

    public void navigateBookingPage() {
        click(byLnkBooking);
    }

    public boolean isUserLoggedIn(){
        return isElementDisplayed(bySelAvatar);
    }

    public void openDropdownMenu(){
        click(bySelAvatar);
    }

    public void navigateAccountInfoPage(){
        click(byMenuAccountInfo,3000);
    }

    public void navigateAppointmentPage(){
        openDropdownMenu();
        click(byMenuAppointment,3000);
    }

    public void navigatePrescriptionPage (){
        openDropdownMenu();
        click(byMenuPrescription,3000);
    }

    public void navigateInvoicePage(){
        openDropdownMenu();
        click(byMenuInvoice,3000);
    }

    public boolean isLogoutLinkDisplayed() {
        return isElementDisplayed(byMenuLogout);
    }

    public void navigateLogout(){
        openDropdownMenu();
        click(byMenuLogout,3000);
    }
}
