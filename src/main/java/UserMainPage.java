import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserMainPage extends Page {

    private static final By RECOMMENDATIONS = By.xpath(".//a[contains(text(), 'Рекомендации')]");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    public RecommendationsPage toRecommendationsPage() {
        WebElement element = driver.findElement(RECOMMENDATIONS);
        element.click();

        return new RecommendationsPage(driver);
    }

    @Override
    public void check() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(RECOMMENDATIONS));
    }
}
