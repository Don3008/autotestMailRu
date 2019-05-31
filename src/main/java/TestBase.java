import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class TestBase {

    private String baseUrl;
    protected WebDriver driver;
    private StringBuffer veificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception{
        init();
    }

    @After
    public void afterTest() throws Exception{
        stop();
    }

    public void init() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1500, 1100));
        baseUrl = "https://ok.ru/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl + "/");
    }

    public void stop() {
        driver.quit();
        String verificationErrorsString = veificationErrors.toString();
        if (!"".equals(verificationErrorsString)) {
            fail(verificationErrorsString);
        }
    }
}
