import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertAndOther extends firstTest{

    @Test
    public void alertTest() throws InterruptedException {
        driver.get("https://javascript.info/alert-prompt-confirm");
        driver.findElement(By.xpath("//div[@id='sytfvmvpzr']//a[@title='run']")).click();

        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String alertsText = alert.getText();
        alert.accept();

        Assert.assertEquals("Hello", alertsText);

    }

}
