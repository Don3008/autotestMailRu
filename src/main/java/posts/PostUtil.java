package posts;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

public final class PostUtil {

    private PostUtil() {
    }

    public static int countPosts(WebDriver driver) {
        List<WebElement> elements = driver.findElements(Post.POST_LOCATOR);

        Assert.assertNotNull("List with elements is empty!!!", elements);

        return elements.size();
    }

    public static List<Post> transform(List<WebElement> postElements, WebDriver driver) {
        List<Post> result = new LinkedList<>();

        for (WebElement postElement : postElements) {
            result.add(new Post(driver, postElement));
        }

        return result;
    }
}
