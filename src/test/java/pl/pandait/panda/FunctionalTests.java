package pl.pandait.panda;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionalTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "D:/ANDRES/Escritorio/geckodriver.exe"); // Cambia esta ruta
        driver = new FirefoxDriver();
        driver.get("http://localhost:8081/index.html"); // URL de la aplicación
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        driver.quit(); // Cierra el navegador después de cada prueba
        Thread.sleep(5000);
    }

    @Test
    public void testCreateMessage() throws InterruptedException {
        WebElement input = driver.findElement(By.id("createMessage"));
        input.sendKeys("Hello, Selenium!");
        WebElement button = driver.findElement(By.xpath("//button[text()='Create Message']"));
        button.click();
        
        WebElement response = driver.findElement(By.id("responseOutput"));
        assertTrue(response.getText().contains("Message created with ID"), "Message creation failed!");
        Thread.sleep(5000);
    }

    @Test
    public void testGetMessage() throws InterruptedException{
        WebElement createInput = driver.findElement(By.id("createMessage"));
        createInput.sendKeys("Message to retrieve");
        WebElement createButton = driver.findElement(By.xpath("//button[text()='Create Message']"));
        createButton.click();
        
        WebElement response = driver.findElement(By.id("responseOutput"));
        String responseText = response.getText();
        String messageId = responseText.split(": ")[1].trim();

        WebElement getInput = driver.findElement(By.id("getMessageId"));
        getInput.sendKeys(messageId);
        WebElement getButton = driver.findElement(By.xpath("//button[text()='Get Message']"));
        getButton.click();
        
        assertTrue(response.getText().contains("Message to retrieve"), "Message retrieval failed!"); 
        Thread.sleep(5000);
    }

    @Test
    public void testUpdateMessage() throws InterruptedException{
        WebElement createInput = driver.findElement(By.id("createMessage"));
        createInput.sendKeys("Message to update");
        WebElement createButton = driver.findElement(By.xpath("//button[text()='Create Message']"));
        createButton.click();
        
        WebElement response = driver.findElement(By.id("responseOutput"));
        String responseText = response.getText();
        String messageId = responseText.split(": ")[1].trim();

        WebElement updateIdInput = driver.findElement(By.id("updateMessageId"));
        updateIdInput.sendKeys(messageId);
        WebElement updateMessageInput = driver.findElement(By.id("updateMessage"));
        updateMessageInput.sendKeys("Updated Message");
        WebElement updateButton = driver.findElement(By.xpath("//button[text()='Update Message']"));
        updateButton.click();
        
        assertTrue(response.getText().contains("Message updated for ID"), "Message update failed!");
        Thread.sleep(5000);
    }

    @Test
    public void testDeleteMessage() throws InterruptedException{
        WebElement createInput = driver.findElement(By.id("createMessage"));
        createInput.sendKeys("Message to delete");
        WebElement createButton = driver.findElement(By.xpath("//button[text()='Create Message']"));
        createButton.click();
        
        WebElement response = driver.findElement(By.id("responseOutput"));
        String responseText = response.getText();
        String messageId = responseText.split(": ")[1].trim();

        WebElement deleteInput = driver.findElement(By.id("deleteMessageId"));
        deleteInput.sendKeys(messageId);
        WebElement deleteButton = driver.findElement(By.xpath("//button[text()='Delete Message']"));
        deleteButton.click();
        
        assertTrue(response.getText().contains("Message deleted for ID"), "Message deletion failed!");
        Thread.sleep(5000);
    }
}
