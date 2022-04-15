import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits extends firstTest{
    @Test
    public void testWait() throws InterruptedException {

        driver.get("https://ng-bootstrap.github.io/#/components/alert/examples");
        By button = By.xpath("//button[@class='btn btn-primary' and contains(text(), 'Change message')]");
        By message = By.xpath("//ngb-alert[contains(text(), 'Message successfully changed')]");

        JavascriptExecutor jsc = (JavascriptExecutor)driver;
        jsc.executeScript("arguments[0].scrollIntoView();", getElement(button));
        jsc.executeScript("arguments[0].click();", getElement(button));

//        getElement(button).click();
        String textBefore = getElement(message).getText();

        Thread.sleep(1500);

        getElement(button).click();
        String textAfter = getElement(message).getText();

        Assert.assertNotEquals(textBefore, textAfter);

    }

    public WebElement getElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement getElement(By locator, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

}
