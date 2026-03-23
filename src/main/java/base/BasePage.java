package base;

import constants.WaitTimeOut;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    private By toastContainer  = By.xpath("//div[contains(@class,'toast')]");

    public WebElement waitForVisibilityOfElementLocated(By locator, long timeOutInSec){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator, long timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForPresenceOfElementLocated(By locator, long timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForInvisibilityOfElementLocated(By locator, long timeOutInSec){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * SendKeys action
     * @param locator - Need By locator
     * @param value
     * @param timeOutInSec - time out value in seconds
     */
    public void sendKeys(By locator, String value, long timeOutInSec) {
        WebElement element = waitForVisibilityOfElementLocated(locator, timeOutInSec);
        element.sendKeys(value);
    }

    public void sendKeys(By locator, String value) {
        sendKeys(locator, value, WaitTimeOut.DEFAULT_TIMEOUT);
    }

    public void click(By locator, long timeOutInSec){
        try{
            WebElement element = waitForClickable(locator, timeOutInSec);
            element.click();
        } catch (Exception e) {
            WebElement element = driver.findElement(locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }

    }

    public void pause(long millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * If isDisplayed is false, action will not wait for visibility
     * Else action will wait for element is presented in DOM before clicking
     * @param locator
     * @param isDisplayed
     * @param timeOutInSec
     */
    public void click(By locator, boolean isDisplayed, long timeOutInSec) {
        if(isDisplayed) {
            click(locator, timeOutInSec);
        } else {
            WebElement element = waitForPresenceOfElementLocated(locator, timeOutInSec);
            element.click();
        }
    }

    public void click(By locator){
        click(locator, WaitTimeOut.DEFAULT_TIMEOUT);
    }

    public String getText(By locator, long timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    public String getText(By locator) {
        return getText(locator, WaitTimeOut.DEFAULT_TIMEOUT);
    }

    public void selectDropdownByText(By locator, String text, long timeOutInSec){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
        wait.until(driver -> {
            WebElement element = driver.findElement(locator);
            Select select = new Select(element);
            return select.getOptions().stream()
                    .anyMatch(option -> option.getAttribute("value").equals(text));
        });

        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }

    public void selectDropdownByText(By locator, String value){
        selectDropdownByValue(locator, value,  WaitTimeOut.DEFAULT_TIMEOUT);
    }

    public void selectDropdownByValue(By locator, String value, long timeOutInSec){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
        wait.until(driver -> {
            WebElement element = driver.findElement(locator);
            Select select = new Select(element);
            return select.getOptions().stream()
                    .anyMatch(option -> option.getAttribute("value").equals(value));
        });

        Select select = new Select(driver.findElement(locator));
        select.selectByValue(value);
    }

    public void selectDropdownByValue(By locator, String value){
        selectDropdownByValue(locator, value,  WaitTimeOut.DEFAULT_TIMEOUT);
    }

    public void formatAndClick(String xPath, int number) {
        String finalXpath = String.format(xPath, number);
        click(By.xpath(finalXpath));
    }

    public void formatAndClick(String xPath, String text) {
        String finalXpath = String.format(xPath, text);
        click(By.xpath(finalXpath));
    }

    public void scrollToElement(By locator) {
        WebElement element = waitForPresenceOfElementLocated(locator, 10);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToView(By locator) {
        WebElement element = waitForPresenceOfElementLocated(locator, 10);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});",element);
    }

    public void waitForToastDisappear() {
        try {
            waitForInvisibilityOfElementLocated(toastContainer, 10);
        } catch (Exception e) {
            // ignore
        }
    }

    public void clearAndSendKeys(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }
    public void disableHtml5Validation() {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("document.querySelector('form').setAttribute('novalidate', 'true');");
    }

    public boolean isElementDisplayed(By locator, long timeOutInSec) {
        try {
            WebElement element = waitForVisibilityOfElementLocated(locator, timeOutInSec);
            return element.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isElementDisplayed(By locator) {
        return isElementDisplayed(locator, WaitTimeOut.DEFAULT_TIMEOUT);
    }

    public void waitForPageStable() {
        waitForToastDisappear();
    }

    public String getValidationMessage(By locator) {
        WebElement element =
                waitForVisibilityOfElementLocated(locator, 10);

        return element.getAttribute("validationMessage");
    }
}
