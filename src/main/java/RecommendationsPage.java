import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecommendationsPage extends Page {

    static final By ICON_LOCATOR = By.xpath(".//a[contains(text(), 'Рекомендации')]");
    static final By LIKE_COUNT = By.xpath(".//span[@data-module='ReactComponent']/span[contains(@class,'count')]");
    static final By X = By.xpath(".//a[@class='feed_close']");

    private static final By POST = By.xpath(".//div[@class='feed-w']");
    private static final By LIKE = By.xpath(".//div[contains(@class, '__wide-count')]");
    private static final By SUBSCRIPTION = By.xpath(".//*[contains(text(), 'Присоединиться')]");
    private static final By TITLE = By.xpath(".//a[contains(@hrefattrs, 'st.cmd')]");
    private static final By GROUP = By.xpath(".//a[contains(@data-l, 'userAltGroup')]");
    private static final By HIDE = By.xpath(".//input[@name='st.uo']");
    private static final By ACCEPT = By.xpath(".//button[contains(text(), 'Подтвердить')]");
    private static final By HERE = By.xpath(".//a[contains(text(), 'здесь')]");

    private static final By POST_LIST = By.className("feed-list");

    RecommendationsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        Assert.assertTrue("Recommendation icon is inactive!!!",
                wait.until(ExpectedConditions.attributeContains(ICON_LOCATOR, "class", "__active")));

        checkElement("Post list isn't visible", POST_LIST);
    }

    int getLikeCount() {
        return Integer.parseInt(driver.findElement(LIKE_COUNT).getText());
    }

    void clickLike() {
        new Actions(driver).moveToElement(driver.findElement(POST)).build().perform();
        Assert.assertTrue("Отсутствует кнопка \"Класс\"", isElementPresent(LIKE));
        click(LIKE);
    }

    void subscribe() {
        Assert.assertTrue("Отсутствует кнопка \"Присоединиться\"", isElementPresent(SUBSCRIPTION));
        driver.findElement(SUBSCRIPTION).click();
    }

    GroupPage goToGroups() {
        Assert.assertTrue("Отсутствует ссылка \"Группы\"", isElementPresent(GROUP));
        click(GROUP);
        return new GroupPage(driver);
    }

    String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    void clickHideAllEvents() {
        Assert.assertTrue("Отсутствует кнопка \"Скрыть события\"", isElementPresent(HIDE));
        click(HIDE);
    }

    void accept() {
        Assert.assertTrue("Отсутствует кнопка \"Подтвердить\"", isElementPresent(ACCEPT));
        click(ACCEPT);
    }

    BlackListPage clickToAddBlackList() {
        Assert.assertTrue("Отсутствует ссылка \"здесь\"", isElementPresent(HERE));
        click(HERE);
        return new BlackListPage(driver);
    }

    WebElement toSearchElement(By by) {
        Assert.assertTrue("Не найден \"Like\"", isElementPresent(by));
        return driver.findElement(by);
    }
}
