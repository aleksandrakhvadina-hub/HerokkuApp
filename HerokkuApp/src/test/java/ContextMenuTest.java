import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.*;

public class ContextMenuTest {

    @Test
public void checkDynamicControls() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    options.addArguments("--incognito");
    options.addArguments("--disable-notification");
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // открываем страницу
    driver.get("https://the-internet.herokuapp.com/context_menu");
    // находим элемент и кликаем правой кнопкой
    WebElement hotSpot = driver.findElement(By.id("hot-spot"));
    Actions actions = new Actions(driver);
    actions.contextClick(hotSpot).perform();
    // явное ожидание появления алерта
    wait.until(ExpectedConditions.alertIsPresent());
    // переключаемся на алерт и получаем его текст
    Alert alert = driver.switchTo().alert();
    String alertText = alert.getText();
    // проверяем, что текст алерта совпадает с ожидаемым
    assertEquals(alertText, "You selected a context menu");
    // закрываем алерт нажатием OK
    alert.accept();

    driver.quit();
    }
}

