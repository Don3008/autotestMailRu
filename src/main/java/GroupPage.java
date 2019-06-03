import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GroupPage extends Page{

    public static final By GROUP_COUNT = By.xpath(".//*[contains(@hrefattrs, 'MyGroupsNav_All')]");
    public static final By MAIN_PAGE = By.xpath(".//div[@id='topPanelLeftCorner']");

    public GroupPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {
        WebDriverWait waiter = new WebDriverWait(driver, 10);

        //waiter.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GROUP_COUNT));
    }

    int getGroupCount() {
        return Integer.parseInt(driver.findElement(GROUP_COUNT).getText().replaceAll("\\D+", "").trim());
    }

    UserMainPage goToMainPage() {
        click(MAIN_PAGE);
        return new UserMainPage(driver);
    }
}
