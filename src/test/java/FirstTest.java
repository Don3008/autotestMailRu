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

    private RecommendationsPage recommendationsPage;
    private WebDriver driver;
    private UserMainPage userMainPage;

    @Before
    public void prepare() {
        driver = new ChromeDriver();
        driver.get("https://ok.ru/");
        LoginPage loginPage = new LoginPage(driver);
        userMainPage = loginPage.login();
        recommendationsPage = userMainPage.toRecommendationsPage();
    }

    @Test
    public void testPostsVisible() {
        for (int i = 0; i < 10; i++) {
            recommendationsPage.getPost(i);
        }
    }

    @Test
    public void testInfiniteScroll() {
        int count = 0;
        //без while
        while (true) {
            //explicit
            //condition
            runWithInfinityScroll(count, integer -> {
                recommendationsPage.getPost(integer);
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
                Post post = recommendationsPage.getPost(integer);
                if (post.hasWidgets()) {
                    int prevCounter = post.getRepostCounter();

                    post.share(driver);

                    int postCounter = post.getRepostCounter();

                    Assert.assertTrue(postCounter - prevCounter >= 1);

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

    @Test
    public void testVisibilityOfJoin() {
        int i = 0;
        while (true) {
            boolean increment = runWithInfinityScroll(i, integer -> {
                Post post = recommendationsPage.getPost(integer);
                if (post.isJoinExists()) {
                    post.join();
                    String prevId = post.getId();

                    driver.navigate().refresh();
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                    recommendationsPage = userMainPage.toRecommendationsPage();

                    post = recommendationsPage.getPost(prevId);

                    Assert.assertFalse(post.isJoinExists());

                    return true;
                } else return false;
            });

            if (increment) {
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

    @FunctionalInterface
    public interface Executor {
        boolean execute(int i);
    }

}
