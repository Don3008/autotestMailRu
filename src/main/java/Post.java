import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Post {
    private static final String XPATH_BY_INDEX = ".//*[@class = 'feed-w'][%s]";
    private static final By COMMENT = By.xpath(".//a[@data-module = 'CommentWidgets']");
    private static final By REPOST = By.xpath(".//button[@data-type='REPOST']");
    private final WebElement rootElement;
    private By ROOT_LOCATOR;

    public Post(WebDriver driver, int index) {

        ROOT_LOCATOR = getXpathByIndex(index);
        rootElement = driver.findElement(ROOT_LOCATOR);
        checkElements(driver);
    }

    public static By getXpathByIndex(int index) {
        return By.xpath(String.format(XPATH_BY_INDEX, String.valueOf(index + 1)));
    }

    private ExpectedCondition<WebElement> visibilityOfPostElement(By locator) {
        if (rootElement == null) {
            throw new NullPointerException("rootElement is null!");
        }

        return new ExpectedCondition<WebElement>() {
            @NullableDecl
            @Override
            public WebElement apply(@NullableDecl WebDriver driver) {
                try {
                    return rootElement.findElement(locator);
                } catch (Exception e) {
                    return null;
                }
            }
        };
    }

    public void checkElements(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);


        wait.until(visibilityOfPostElement(COMMENT));
        wait.until(visibilityOfPostElement(REPOST));
    }

}
