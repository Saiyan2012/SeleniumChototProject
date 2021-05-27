package ChototUiPOM.resource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class baseTest {

    /* ======================= Set Up Test ======================= */
    protected WebDriver driver;
    public baseTest (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @BeforeMethod
    protected void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.chotot.org/");
    }

    @AfterTest
    protected void tearDown() {
        driver.close();
    }
}
