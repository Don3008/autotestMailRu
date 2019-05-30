import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecommendationsPage extends Page {

    public static final By ICON_LOCATOR = By.xpath(".//a[contains(text(), 'Рекомендации')]");

    public RecommendationsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.attributeContains(ICON_LOCATOR, "class", "__active"));

        for (int i = 0; i < 20; i++) {
            By by = Post.getXpathByIndex(i);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            //System.out.println(by.toString());
        }
    }

    public Post getPost(int position) {
        return new Post(driver, position);
    }

}
