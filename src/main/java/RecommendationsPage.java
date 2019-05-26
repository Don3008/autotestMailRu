import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecommendationsPage extends Page {

    public RecommendationsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        for (int i = 0; i < 20; i++) {
            By by = Post.getXpathByIndex(i);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Post.getXpathByIndex(i)));
            System.out.println(by.toString());
        }
    }

}
