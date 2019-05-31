import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

public class RecommendationsPage extends Page {

    public static final By ICON_LOCATOR = By.xpath(".//a[contains(text(), 'Рекомендации')]");
    public static final By LIKE_COUNT = By.xpath(".//span[@data-module='ReactComponent']/span[contains(@class,'count')]");
    public static final By POST = By.xpath(".//div[@class='feed-w']");
    public static final By LIKE = By.xpath(".//div[contains(@class, '__wide-count')]");
    public static final By SUBSCRIPTION = By.xpath(".//*[contains(text(), 'Присоединиться')]");
    public static final By GROUP = By.xpath(".//a[contains(@data-l, 'userAltGroup')]");
    public static final By GROUP_COUNT = By.xpath(".//*[contains(@hrefattrs, 'MyGroupsNav_All')]");
    public static final By MAIN_PAGE = By.xpath(".//div[@id='topPanelLeftCorner']");
    public static final By TITLE = By.xpath(".//a[contains(@hrefattrs, 'st.cmd')]");
    public static final By X = By.xpath(".//a[@class='feed_close']");
    public static final By HIDE = By.xpath(".//input[@name='st.uo']");
    public static final By ACCEPT = By.xpath(".//button[contains(text(), 'Подтвердить')]");
    public static final By HERE = By.xpath(".//a[contains(text(), 'здесь')]");


    public RecommendationsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        //wait.until(ExpectedConditions.attributeContains(ICON_LOCATOR, "class", "__active"));

        for (int i = 0; i < 20; i++) {
            By by = Post.getXpathByIndex(i);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            //System.out.println(by.toString());
        }
    }

    public Post getPost(int position) {
        return new Post(driver, position);
    }

    public int getLikeCount() {
        return Integer.parseInt(driver.findElement(LIKE_COUNT).getText());
    }

    public void clickLike() {
        new Actions(driver).moveToElement(driver.findElement(POST)).build().perform();
        Assert.assertTrue("Отсутствует кнопка \"Класс\"", isElementPresent(LIKE));
        click(LIKE);
    }

    public int getGroupCount() {
        return Integer.parseInt(driver.findElement(GROUP_COUNT).getText().replaceAll("\\D+","").trim());
    }

    public void subscribe() {
        //new Actions(driver).moveToElement(driver.findElement(POST)).build().perform();
        //Assert.assertTrue("Отсутствует кнопка \"Присоединиться\"", isElementPresent(SUBSCRIPTION));
        driver.findElement(SUBSCRIPTION).click();
    }

    public void groups() {
        click(GROUP);
    }

    public void mainPage() {
        click(MAIN_PAGE);
    }

    public void recommendation() {
        click(ICON_LOCATOR);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void clickX() {
        //new Actions(driver).moveToElement(driver.findElement(POST)).build().perform();
        Assert.assertTrue("Отсутствует кнопка \"X\"", isElementPresent(X));
        click(X);
    }

    public void clickHide() {
        //new Actions(driver).moveToElement(driver.findElement(HIDE), 1, -1).click().build().perform();
        Assert.assertTrue("Отсутствует кнопка \"Скрыть события\"", isElementPresent(HIDE));
        click(HIDE);
    }

    public void accept() {
        Assert.assertTrue("Отсутствует кнопка \"Подтвердить\"", isElementPresent(ACCEPT));
        click(ACCEPT);
    }

    public void clickHere() {
        Assert.assertTrue("Отсутствует ссылка \"здесь\"", isElementPresent(HERE));
        click(HERE);
    }

    public void clickOnInvisibleElement(WebElement element) {

        String script = "var object = arguments[0];"
            + "var theEvent = document.createEvent(\"MouseEvent\");"
            + "theEvent.initMouseEvent(\"click\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
            + "object.dispatchEvent(theEvent);"
            ;

        ((JavascriptExecutor)driver).executeScript(script, element);
    }
}
