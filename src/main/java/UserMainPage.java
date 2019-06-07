import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserMainPage extends Page {

    private static final By GROUP = By.xpath(".//a[contains(@data-l, 'userAltGroup')]");

    UserMainPage(WebDriver driver) {
        super(driver);
    }

    RecommendationsPage toRecommendationsPage() {
        Assert.assertTrue("Recommendation page icon is absent!!!", isElementPresent(RecommendationsPage.ICON_LOCATOR));

        click(RecommendationsPage.ICON_LOCATOR);
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
