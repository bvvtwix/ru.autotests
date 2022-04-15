import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.C;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class TestCookies extends firstTest {

//    WebDriver driver;
//    private Logger logger = LogManager.getLogger(firstTest.class);

    @Test
    public void cookies(){
        driver.get("https://otus.ru");

        driver.manage().addCookie(new Cookie("Otus1", "Value1"));
        driver.manage().addCookie(new Cookie("Otus2", "Value2"));
        Cookie otus3 = new Cookie("Otus3", "Value3");
        driver.manage().addCookie(otus3);
        driver.manage().addCookie(new Cookie("Otus4", "Value4"));
        logger.info(driver.manage().getCookies());

        Cookie otus1 = driver.manage().getCookieNamed("Otus1");
        logger.info(otus1);

        driver.manage().deleteCookieNamed("Otus2");
        driver.manage().deleteCookie(otus3);
        driver.manage().deleteAllCookies();

        Assert.assertEquals(0, driver.manage().getCookies().size());

        driver.manage().addCookie(new Cookie("sessionid", "0x5x1ri4blyp3edlq1gks73x9gxpj29b"));

        logger.info(driver.manage().getCookies());
    }

    @Test
    public void waitSample(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(0, TimeUnit.SECONDS);
        driver.get("https://otus.ru");
    }

    @Test
    public void window() throws InterruptedException {
        // запустить тест в полном окне (не киоск) получить его размер
        driver.manage().window().maximize();
        logger.info(driver.manage().window().getSize());
        Thread.sleep(3000);
        // run test with size 800x600 and get position
        driver.manage().window().setSize(new Dimension(800, 600));
        logger.info(driver.manage().window().getPosition());
        Thread.sleep(3000);
        // the same as a previous step + move browser
        driver.manage().window().setSize(new Dimension(800, 600));
        Point point = driver.manage().window().getPosition();

        point.x = point.x + 500;
        point.y = point.y;
        driver.manage().window().setPosition(point);
        Thread.sleep(1000);

        point.y += 500;
        driver.manage().window().setPosition(point);
        Thread.sleep(1000);

        point.x -= 500;
        driver.manage().window().setPosition(point);
        Thread.sleep(1000);

        point.y -= 500;
        driver.manage().window().setPosition(point);
        Thread.sleep(1000);

    }

    @Test
    public void headless(){
        driver.quit();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.get("https://otus.ru");
        driver.findElement(By.cssSelector("a.header2_subheader-link:nth-child(6)"));
        logger.info(driver.getTitle());
    }

}
