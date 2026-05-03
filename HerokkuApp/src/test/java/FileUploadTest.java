import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

import static org.testng.Assert.*;

public class FileUploadTest {

    @Test
    public void checkFileUpload() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/upload");

        // формируем относительный путь к файлу
        String fileName = "test_upload.txt";
        String filePath = System.getProperty("user.dir") + File.separator + fileName;
        // находим инпут для загрузки и передаём путь
        WebElement fileInput = driver.findElement(By.id("file-upload"));
        fileInput.sendKeys(filePath);
        // нажимаем кнопку загрузки
        driver.findElement(By.id("file-submit")).click();
        // явное ожидание появления элемента с именем загруженного файла
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploaded-files")));
        // проверяем, что имя файла на странице совпадает с нашим
        String uploadedName = driver.findElement(By.id("uploaded-files")).getText();
        assertEquals(uploadedName, fileName, "Имя загруженного файла не совпадает");

        driver.quit();
    }
}