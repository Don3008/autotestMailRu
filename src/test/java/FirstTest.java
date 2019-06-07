import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import posts.Post;
import posts.PostUtil;
import posts.PostWithJoinButton;
import posts.PostWithWidgetList;

import java.util.List;

public class FirstTest extends TestBase {

    @Test
    public void testPostsVisible() {
        int num = 5;
        int size = PostUtil.countPosts(driver);

        Assert.assertTrue("List is less than " + num, size >= 5);
    }

    @Test
    public void testInfiniteScroll() {

        waitUntil(180, new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver driver) {
                if (PostUtil.countPosts(driver) >= 60) {
                    return Boolean.TRUE;
                } else {
                    JavaScriptHelper js = new JavaScriptHelper(driver);
                    js.scroll();

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
    public void testHasJoinResult() {
        List<Post> posts = PostUtil.transform(driver.findElements(Post.POST_LOCATOR), driver);

        for (Post post : posts) {
            if (post.hasJoinButton()) {
                PostWithJoinButton postWithJoinButton = post.transformToPostWithJoinButton();

                postWithJoinButton.join();

                Assert.assertTrue("Join result is invisible!!!", postWithJoinButton.hasJoinResult());
                return;
            }
        }
    }

}
