import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserMainPage extends Page {

    public static final By GROUP = By.xpath(".//a[contains(@data-l, 'userAltGroup')]");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    public RecommendationsPage toRecommendationsPage() {
        WebElement element = driver.findElement(RecommendationsPage.ICON_LOCATOR);
        element.click();
        return new RecommendationsPage(driver);
    }

    @Override
    public void check() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(RecommendationsPage.ICON_LOCATOR));
    }

    GroupPage goToGroups() {
        Assert.assertTrue("Отсутствует ссылка \"Группы\"", isElementPresent(GROUP));
        click(GROUP);
        return new GroupPage(driver);
    }
}
