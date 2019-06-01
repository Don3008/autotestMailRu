import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import posts.Post;
import posts.PostUtil;
import posts.PostWithJoinButton;
import posts.PostWithWidgetList;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class FirstTest extends TestBase {

    @Test
    public void testPostsVisible() {
        List<WebElement> elements = driver.findElements(Post.POST_LOCATOR);

        int num = 5;

        Assert.assertNotNull("List with elements is empty!!!", elements);
        Assert.assertTrue("List is less than " + num, elements.size() >= 5);
    }

    @Test
    public void testInfiniteScroll() {

        WebDriverWait wait = new WebDriverWait(driver, 180);

        wait.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver driver) {
                if (driver.findElements(Post.POST_LOCATOR).size() >= 60) {
                    return Boolean.TRUE;
                } else {
                    scroll();
                    return null;
                }
            }
        });

    }

    @Test
    public void testRepostCounter() {
        List<Post> posts = PostUtil.transform(driver.findElements(Post.POST_LOCATOR), driver);

        for (Post post : posts) {
            if (post.hasWidgetList()) {
                PostWithWidgetList postWithWidgetList = post.transformToPostWithWidgetList();

                int prevCount = postWithWidgetList.getWidgets().getRepostCount();

                postWithWidgetList.share();

                int postCount = postWithWidgetList.getWidgets().getRepostCount();

                Assert.assertTrue("postCount is equal to prevCount!", postCount - prevCount >= 1);

                return;
            }
        }

    }

    @Test
    public void testVisibilityOfJoin() {
        List<Post> posts = PostUtil.transform(driver.findElements(Post.POST_LOCATOR), driver);

        for (Post post : posts) {
            if (post.hasJoinButton()) {
                PostWithJoinButton postWithJoinButton = post.transformToPostWithJoinButton();
                final String prevId = postWithJoinButton.getId();

                postWithJoinButton.join();

                driver.navigate().refresh();

                recommendationsPage = userMainPage.toRecommendationsPage();

                WebDriverWait wait = new WebDriverWait(driver, 120);
                wait.until(new ExpectedCondition<Boolean>() {
                    @NullableDecl
                    @Override
                    public Boolean apply(@NullableDecl WebDriver driver) {
                        List<Post> newPosts = PostUtil.transform(driver.findElements(Post.POST_LOCATOR), driver);

                        try {
                            newPosts.stream()
                                    .findFirst()
                                    .filter(post1 -> post1.getId().equals(prevId))
                                    .get();

                            return Boolean.TRUE;
                        } catch (NoSuchElementException e) {
                            scroll();
                            return null;
                        }
                    }
                });

                return;
            }
        }
    }

    private void scroll() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

}
