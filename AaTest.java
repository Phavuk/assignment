import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;
public class AaTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    public String waitForWindow(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> whNow = driver.getWindowHandles();
        Set<String> whThen = (Set<String>) vars.get("window_handles");
        if (whNow.size() > whThen.size()) {
            whNow.removeAll(whThen);
        }
        return whNow.iterator().next();
    }
    @Test
    public void aa() {
        driver.get("https://www.azet.sk/");
        driver.findElements(By.className("fc-button-label"));
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[2]/button[1]/p")).click();
        driver.manage().window().setSize(new Dimension(974, 640));
        vars.put("window_handles", driver.getWindowHandles());
        driver.findElement(By.cssSelector(".azet-account")).click();
        vars.put("win2612", waitForWindow(2000));
        vars.put("root", driver.getWindowHandle());
        driver.switchTo().window(vars.get("win2612").toString());
        driver.findElement(By.xpath("//input[@name=\'form[username]\']")).sendKeys("nessTest");
        driver.findElement(By.name("form[password]")).click();
        driver.findElement(By.name("form[password]")).sendKeys("nessTest951");
        driver.findElement(By.cssSelector(".input-button--login")).click();
        driver.close();
        driver.switchTo().window(vars.get("root").toString());
    }
}
