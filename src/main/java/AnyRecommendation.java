import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AnyRecomendation extends Page {

    public static final By POST = By.xpath(".//div[@class='discovery-item_obj']");

    public AnyRecomendation(WebDriver driver) {
        super(driver);
    }

    public Post choosePost() {
        click(POST);
        return new Post(driver, POST);
    }

    @Override
    public void check() {
        
    }
}
