import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class OrangeHRMSmokeTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get(System.getenv().getOrDefault(
            "ORANGEHRM_BASE_URL",
            "https://opensource-demo.orangehrmlive.com/"
        ));
    }

    @Test
    void loginAndNavigateToPim() {
        String username =
            System.getenv().getOrDefault("ORANGEHRM_USERNAME", "Admin");
        String password =
            System.getenv().getOrDefault("ORANGEHRM_PASSWORD", "admin123");

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.name("username")
            )
        ).sendKeys(username);

        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(
            By.cssSelector("button[type='submit']")
        ).click();

        wait.until(ExpectedConditions.urlContains("/dashboard"));

        Assertions.assertTrue(
            driver.getCurrentUrl().contains("/dashboard"),
            "Dashboard URL was not reached"
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='PIM']")
            )
        ).click();

        wait.until(ExpectedConditions.urlContains("/pim"));

        Assertions.assertTrue(
            driver.getCurrentUrl().contains("/pim"),
            "PIM URL was not reached"
        );
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
