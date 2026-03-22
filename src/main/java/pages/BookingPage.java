package pages;

import constants.WaitTimeOut;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class BookingPage extends CommonPage{

    private By bySelBranch = By.xpath("//label[text()='Chi nhánh']/following-sibling::select");
    private By bySelDoctor = By.xpath("//label[text()='Bác sĩ']/following-sibling::select");
    private String dateXpath = "//button[contains(@class, 'react-calendar__tile') and not(@disabled)]//abbr[text()='%d']/..";
    private By byTxtNote = By.xpath("//textarea[contains(@placeholder,'triệu chứng')]");
    private String packageXpath = "//div[contains(@class,'service-package-card')]" +
            "[.//h6[text()='%s']]//button[text()='Chọn gói này']";
    private By byBtnSubmit = By.xpath("//button[contains(text(),'Đặt lịch khám')]");


    public BookingPage(WebDriver driver) {
        super(driver);
    }

    public void selectBranch(String valueBranch) {
        selectDropdownByValue(bySelBranch, valueBranch);
    }

    public void selectDoctor(String valueDoctor) {
        selectDropdownByValue(bySelDoctor, valueDoctor);
    }

    public void selectDate(int dayNumber) {
        formatAndClick(dateXpath, dayNumber);
    }

    public void selectPackage(String packageName) {
        formatAndClick(packageXpath, packageName);
    }

    public void enterNote(String note) {
        sendKeys(byTxtNote, note);
    }

    public void clickSubmit() {
        click(byBtnSubmit);
    }

    public boolean isBookingButtonEnabled() {
        try{
            return waitForVisibilityOfElementLocated(byBtnSubmit, WaitTimeOut.MEDIUM_TIMEOUT).isEnabled();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isBookingPageDisplayed() {
        try {
            return waitForVisibilityOfElementLocated(bySelBranch, WaitTimeOut.MEDIUM_TIMEOUT).isDisplayed();

        } catch (TimeoutException e) {
            return false;
        }
    }
}
