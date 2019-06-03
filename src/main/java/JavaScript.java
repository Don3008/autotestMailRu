import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScript extends Page{

     //WebDriver driver;

    JavaScript(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {

    }

    void clickOnInvisibleElement(WebElement element) {

        String script = "var object = arguments[0];"
            + "var theEvent = document.createEvent(\"MouseEvent\");"
            + "theEvent.initMouseEvent(\"click\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
            + "object.dispatchEvent(theEvent);";

        ((JavascriptExecutor) driver).executeScript(script, element);
    }
}
