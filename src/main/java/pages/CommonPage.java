package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import pages.components.NavigationBar;

public class CommonPage extends BasePage {

    private NavigationBar navigationBar;

    public CommonPage(WebDriver driver) {
        super(driver);
        this.navigationBar = new NavigationBar(driver);
    }

    public NavigationBar getNavigationBar() {
        return this.navigationBar;
    }
}
