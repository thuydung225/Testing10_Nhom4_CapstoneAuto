package pages.components;

import base.BasePage;
import constants.WaitTimeOut;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TimeoutException;

public class NavigationBar extends BasePage {

    private By byLnkLogin = By.xpath("//a[@href='/login']");
    private By byLnkBooking = By.xpath("//a[contains(text(),'Đặt lịch khám')]");

    public NavigationBar(WebDriver driver) {
        super(driver);
    }

    public void navigateLoginPage() {
        click(byLnkLogin);
    }

    public void navigateBookingPage() {
        click(byLnkBooking);
    }
}
