import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FirstTest {

    private RecommendationsPage reccomendationsPage;
    private WebDriver driver;

    @Before
    public void prepare() {
        driver = new ChromeDriver();
        driver.get("https://ok.ru/");
        LoginPage loginPage = new LoginPage(driver);
        UserMainPage userMainPage = loginPage.login();
        reccomendationsPage = userMainPage.toRecommendationsPage();
    }

    @Test
    public void testPostsVisible() {
        for (int i = 0; i < 10; i++) {
            reccomendationsPage.getPost(i);
        }
    }

    @Test
    public void testInfiniteScroll() {
        int count = 0;
        while (true) {
            runWithInfinityScroll(count, integer -> {
                reccomendationsPage.getPost(integer);
                return true;
            });
            count++;

            if (count == 60) {
                Assert.assertTrue(true);
                return;
            }
        }
    }

    @Test
    public void testCommentCounter() {
        int i = 0;

        while (true) {
            boolean increment = runWithInfinityScroll(i, integer -> {
                Post post = reccomendationsPage.getPost(integer);
                if (post.hasWidgets()) {
                    int prevCounter = post.getRepostCounter();

                    post.share(driver);

                    int postCounter = post.getRepostCounter();

                    Assert.assertEquals(1, postCounter - prevCounter);

                    return true;
                } else {
                    return false;
                }
            });

            if (!increment) {
                i++;
            } else {
                return;
            }
        }
    }

    private boolean runWithInfinityScroll(int index, Executor executor) {
        try {
            return executor.execute(index);
        } catch (NoSuchElementException e) {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            return false;
        }
    }

    @After
    public void closeDriver() {
        driver.quit();
    }

    public interface Executor {
        boolean execute(int i);
    }

}
