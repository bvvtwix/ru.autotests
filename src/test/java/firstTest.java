import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class firstTest {
    private Logger logger = LogManager.getLogger(firstTest.class);
    private ConfigServer cfg = ConfigFactory.create(ConfigServer.class);

    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("driver up");
    }

    @After
    public void close(){
        if (driver != null){
            driver.close();
        }
    }

    @Test
    public void testLog(){
        logger.warn("warn - warn level log");
        logger.info("info - info level log");
    }

    @Test
    public void openOtus(){
        driver.get(cfg.url());
    }
}
