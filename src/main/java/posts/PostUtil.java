package posts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

public class PostUtil {


    private PostUtil() {
    }

    public static List<Post> transform(List<WebElement> postElements, WebDriver driver) {
        List<Post> result = new LinkedList<>();

        for (WebElement postElement : postElements) {
            result.add(new Post(driver, postElement));
        }

        return result;
    }
}
