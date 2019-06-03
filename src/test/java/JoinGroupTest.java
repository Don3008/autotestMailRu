import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class JoinGroupTest  extends TestBase{

    private RecommendationsPage recommendationsPage;

    @Override
    @Before
    public void init() {
        driver = new ChromeDriver();
        driver.get("https://ok.ru/");
        LoginPage loginPage = new LoginPage(driver);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        userMainPage = loginPage.login();
    }

    @Test
    public void subscribeGroup() {
        GroupPage groupPage = userMainPage.goToGroups();
        int groupsBefore = groupPage.getGroupCount();
        userMainPage = groupPage.goToMainPage();
        recommendationsPage = userMainPage.toRecommendationsPage();
        recommendationsPage.subscribe();
        groupPage = recommendationsPage.goToGroups();
        int groupsAfter = groupPage.getGroupCount();
        Assert.assertEquals("Количество групп не совпадает", groupsAfter, groupsBefore + 1);
    }
}
