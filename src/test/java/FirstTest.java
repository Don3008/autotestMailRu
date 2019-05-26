import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    private RecommendationsPage reccomendationsPage;


    @Test
    public void prepare() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://ok.ru/");
        LoginPage loginPage = new LoginPage(driver);
        UserMainPage userMainPage = loginPage.login();
        reccomendationsPage = userMainPage.toRecommendationsPage();
    }

//    @Test
//    public void testRecommendationsMainPage() {
//
//    }

}
