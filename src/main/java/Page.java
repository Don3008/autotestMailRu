import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page implements Checkable {

    public WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
        check();
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitStalenessOfElement(final WebElement webElement) {
        //driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        new WebDriverWait(driver, 5).until(ExpectedConditions.stalenessOf(webElement));
    }

    protected void checkElement(String message, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        Assert.assertTrue(message, wait.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver driver) {
                return isElementPresent(locator);
            }
        }));
    }

}
