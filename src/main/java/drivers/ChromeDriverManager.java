package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ChromeDriverManager extends DriverManager {

    @Override
    public void createDriver() {
        ChromeOptions options = new ChromeOptions();

        //Chạy incognito (tránh popup password leak)
        options.addArguments("--incognito");

        //disable automation bar
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        //Disable các popup Chrome
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");

        //Tắt password leak + password manager (CỰC QUAN TRỌNG)
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding,AutofillServerCommunication");

        // Disable password save dialog
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        //Khởi tạo driver
        WebDriver driver = new ChromeDriver(options);

        //Setup browser
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        this.driver = driver;
    }
}
