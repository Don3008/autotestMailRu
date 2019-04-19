import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ChatPage;
import pages.LoginPage;
import pages.Page;
import pages.UserMainPage;
import java.util.LinkedList;
import java.util.List;

public class Main {

    private static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();

        List<Page> pages = new LinkedList<Page>();

        driver.get("https://ok.ru");


        LoginPage lp = new LoginPage(driver);
        lp.execute();


        UserMainPage ump = new UserMainPage(driver);
        ump.execute();

        ChatPage cp = new ChatPage(driver);
        //System.out.println(driver.getCurrentUrl());
        cp.execute();


        Thread.sleep(10000);

        driver.quit();
    }

}
