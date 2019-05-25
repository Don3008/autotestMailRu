import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    WebDriver driver;

    @Before
    public void prepare() {
        driver = new ChromeDriver();

    }

}
