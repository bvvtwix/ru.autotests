import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class firstTest {
    protected Logger logger = LogManager.getLogger(firstTest.class);
    protected ConfigServer cfg = ConfigFactory.create(ConfigServer.class);

    WebDriver driver;
    Actions action;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        action = new Actions(driver);
       // logger.info("driver up");
    }

    @After
    public void close(){
        if (driver != null){
            driver.quit();
        }
    }

    @Test
    public void testJS(){
        driver.get("https://ya.ru");
        ((JavascriptExecutor)driver).executeScript("$(\"#text\").hide();");
        saveFile(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE));
    }

    @Test
    public void testLog(){
        logger.warn("warn - warn level log");
        logger.info("info - info level log");
    }

    @Test
    public void openOtusCfg(){
        driver.get(cfg.url());
    }

    @Test
    public void FindYandex() throws InterruptedException {
        driver.get("https://yandex.ru");
        driver.findElement(By.id("text")).sendKeys("qweqwe" + Keys.ENTER);
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        saveFile(file);
    }

    public void auth() throws InterruptedException {
        WebElement regButton = driver.findElement(By.cssSelector("button[data-modal-id=\"new-log-reg\"]"));
        regButton.click();
        logger.info("Auth form opened");

        WebElement emailField = driver.findElement(By.cssSelector("input.new-input[name='email'][type='text']"));
        emailField.sendKeys(cfg.email());

        WebElement passField = driver.findElement(By.cssSelector("input.new-input[name='password'][type='password']"));
        passField.sendKeys(cfg.pass());

        WebElement enter = driver.findElement(By.cssSelector("button[type='submit'][class='new-button new-button_full new-button_blue new-button_md']"));
        enter.click();

    }

    public void enterToAboutMyself(){
        WebElement checkLogin = driver.findElement(By.cssSelector("p[class='header2-menu__item-text header2-menu__item-text__username']"));
        assert checkLogin.getText().trim().equals("Test");
        logger.info("login success");
        checkLogin.click();

        WebElement personalArea = driver.findElement(By.cssSelector("a[title='Личный кабинет']"));
        personalArea.click();
        logger.info("entered in to personal area");

        WebElement aboutSySelf = driver.findElement(By.xpath("//div[@class='nav nav_mobile-fix no-print js-overflow-scroll']//a[@title='О себе']"));
        aboutSySelf.click();
        logger.info("about myself selected");
    }

    public void openOtus(){
        driver.get("https://otus.ru/");
        logger.info("otus.ru opened");

    }

    @Test
    public void otusTaskAuthAndUserData() throws InterruptedException {

        final String NAME = "Тест";
        final String NAMELATIN = "Test";
        final String LNAME = "Тестовый";
        final String LNAMELATIN = "Testoviy";
        final String BLOGNAME = "TestUiAuto";
        final String BITHDATE = "19.05.1990";
        final String TELEGA = "@testtelega";
        final String COMPANY = "APPLE";
        final String POSITION = "Java QA Auto";

        // 1. Open Otus.ru
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        openOtus();
        // 2. auth
        auth();
        Thread.sleep(1000);

        // 3.
        enterToAboutMyself();
        Thread.sleep(1000);

        // 4. insert data
        // Имя
        WebElement fname = driver.findElement(By.cssSelector("input[name='fname']"));
        fname.clear();
        fname.sendKeys(NAME);
        logger.info("fname added");
        // Имя (латиницей)
        WebElement fnameLatin = driver.findElement(By.cssSelector("input[name='fname_latin']"));
        fnameLatin.clear();
        fnameLatin.sendKeys(NAMELATIN);
        logger.info("fnameLatin added");
        // Фамилия
        WebElement lname = driver.findElement(By.cssSelector("input[name='lname']"));
        lname.clear();
        lname.sendKeys(LNAME);
        logger.info("lname added");
        // Фамилия (латиницей)
        WebElement lnameLatin = driver.findElement(By.cssSelector("input[name='lname_latin']"));
        lnameLatin.clear();
        lnameLatin.sendKeys(LNAMELATIN);
        logger.info("lnameLatin added");
        // Имя (в блоге)
        WebElement blogName = driver.findElement(By.cssSelector("input[name='blog_name']"));
        blogName.clear();
        blogName.sendKeys(BLOGNAME);
        logger.info("blogName added");
        // Дата рождения
        WebElement birthDate = driver.findElement(By.cssSelector("input[name='date_of_birth']"));
        birthDate.clear();
        birthDate.sendKeys(BITHDATE);
        logger.info("birthDate added");
        // Страна
        WebElement country = driver.findElement(By.cssSelector("div[data-slave-selector='.js-lk-cv-dependent-slave-city']"));
        country.click();
        WebElement russia = driver.findElement(By.cssSelector("button[data-value='1'][title='Россия']"));
        russia.click();
        logger.info("russia added");
        // Город
        WebElement city = driver.findElement(By.xpath("//div[@class='select lk-cv-block__input lk-cv-block__input_full js-lk-cv-dependent-slave-city js-lk-cv-custom-select']"));
        Thread.sleep(1000);
        city.click();
        Thread.sleep(1000);
        logger.info("city clicked");
        Thread.sleep(1000);
        WebElement moscow = driver.findElement(By.cssSelector("button[title='Абакан']"));
        Thread.sleep(1000);
        moscow.click();
        logger.info("moscow added");
        // Уровень знания английского языка
        WebElement englishLevel = driver.findElement(By.cssSelector("div[class='select lk-cv-block__input lk-cv-block__input_full js-lk-cv-custom-select']"));
        englishLevel.click();
        WebElement intermediate = driver.findElement(By.cssSelector("button[data-value='4'][title='Средний (Intermediate)']"));
        intermediate.click();
        logger.info("intermediate added");
        // Готовность к переезду
        WebElement readyToRelocate = driver.findElement(By.xpath("//span[contains(text(), 'Да')]"));
        readyToRelocate.click();
        logger.info("readyToRelocate added");
        // Формат работы
        if (!driver.findElement(By.xpath("//input[@title='Удаленно']")).isSelected()) {
            WebElement workFormatRemote = driver.findElement(By.xpath("//span[contains(text(), 'Удаленно')]"));
            workFormatRemote.click();
            logger.info("workFormatRemote added");
        } else {
            logger.info("workFormatRemote already added");
        }
        // Connection type
        driver.findElement(By.xpath("//div[@class='input input_full lk-cv-block__input input_straight-bottom-right input_straight-top-right input_no-border-right lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation']")).click();
        driver.findElement(By.xpath("//button[@data-value='telegram']")).click();
        WebElement telega = driver.findElement(By.cssSelector("#id_contact-0-value"));
        telega.clear();
        telega.sendKeys(TELEGA);
        logger.info("telega added");
        // gender
        driver.findElement(By.id("id_gender")).click();
        driver.findElement(By.xpath("//option[@value='m']")).click();
        logger.info("gender added");
        // company
        WebElement company = driver.findElement(By.id("id_company"));
        company.clear();
        company.sendKeys(COMPANY);
        logger.info("company added");
        // position
        WebElement position = driver.findElement(By.id("id_work"));
        position.clear();
        position.sendKeys(POSITION);
        logger.info("position added");
        // 5. save and continue
        driver.findElement(By.xpath("//button[@title='Сохранить и продолжить']")).click();
        logger.info("save and continue");
        logger.info("all information added");

        // 6. open in new browser
        driver.quit();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        openOtus();
        // 7.
        auth();
        Thread.sleep(1000);
        // 8.
        enterToAboutMyself();
        Thread.sleep(1000);
        //9.
        Assert.assertEquals(NAME, driver.findElement(By.cssSelector("input[name='fname']")).getAttribute("value"));
        logger.info("name success checked");
        Assert.assertEquals(LNAME, driver.findElement(By.cssSelector("input[name='lname']")).getAttribute("value"));
        logger.info("lname success checked");
        Assert.assertEquals(LNAMELATIN, driver.findElement(By.cssSelector("input[name='lname_latin']")).getAttribute("value"));
        logger.info("lname_latin success checked");
        Assert.assertEquals(NAMELATIN, driver.findElement(By.cssSelector("input[name='fname_latin']")).getAttribute("value"));
        logger.info("fname_latin success checked");
        Assert.assertEquals(BLOGNAME, driver.findElement(By.cssSelector("input[name='blog_name']")).getAttribute("value"));
        logger.info("blog_name success checked");
        Assert.assertEquals(BITHDATE, driver.findElement(By.cssSelector("input[name='date_of_birth']")).getAttribute("value"));
        logger.info("date_of_birth success checked");
        Assert.assertEquals("Россия", driver.findElement(By.xpath("//div[contains(text(), 'Россия')]")).getText().trim());
        logger.info("Россия success checked");
        Assert.assertEquals("Абакан", driver.findElement(By.xpath("//div[contains(text(), 'Абакан')]")).getText().trim());
        logger.info("Абакан success checked");
        Assert.assertEquals("Средний (Intermediate)", driver.findElement(By.xpath("//div[contains(text(), 'Средний (Intermediate)')]")).getText().trim());
        logger.info("Средний (Intermediate) success checked");
        Assert.assertEquals(true, driver.findElement(By.id("id_ready_to_relocate_1")).isSelected());
        logger.info("is relocate success checked");
        Assert.assertEquals(true, driver.findElement(By.xpath("//input[@title='Удаленно']")).isSelected());
        logger.info("Удаленно success checked");
        Assert.assertEquals(true, driver.findElement(By.xpath("//input[@title='Полный день']")).isSelected());
        logger.info("Полный день success checked");
        Assert.assertEquals(false, driver.findElement(By.xpath("//input[@title='Гибкий график']")).isSelected());
        logger.info("Гибкий график success checked");
        Assert.assertEquals("Тelegram", driver.findElement(By.xpath("//div[//div[@class='input input_full lk-cv-block__input input_straight-bottom-right input_straight-top-right input_no-border-right lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation'] and contains(text(),  'Тelegram')]")).getText().trim());
        logger.info("Тelegram success checked");
        Assert.assertEquals(TELEGA, driver.findElement(By.xpath("//input[@name='contact-0-value']")).getAttribute("value"));
        logger.info("@testtelega success checked");
        Assert.assertEquals(true, driver.findElement(By.xpath("//option[@value='m']")).isSelected());
        logger.info("sex success checked");
        Assert.assertEquals(COMPANY, driver.findElement(By.id("id_company")).getAttribute("value"));
        logger.info("id_company success checked");
        Assert.assertEquals(POSITION, driver.findElement(By.id("id_work")).getAttribute("value"));
        logger.info("id_work success checked");
        logger.info("all field success checked");

    }

    private void saveFile(File data) {
        String fileName = "target/" + System.currentTimeMillis() + ".png";
        try {
            FileUtils.copyFile(data, new File(fileName));
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Test
    public void draw(){

        driver.get("http://www.htmlcanvasstudio.com");
        WebElement canvas = driver.findElement(By.cssSelector("#imageTemp"));

        Actions beforeBuild = action
                .clickAndHold(canvas)
                .moveByOffset(100, 100)
                .moveByOffset(-50, -10)
                .release();
        beforeBuild.perform();
        saveFile(canvas.getScreenshotAs(OutputType.FILE));
    }
}
