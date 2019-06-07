import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BlackListPage extends Page {

    public static final By TITLE = By.xpath(".//a[contains(@hrefattrs, 'st.cmd')]");

    public BlackListPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {
        WebDriverWait waiter = new WebDriverWait(driver, 10);

        //waiter.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TITLE));
    }

    String getTitle() {
        return driver.findElement(TITLE).getText();
    }
}
