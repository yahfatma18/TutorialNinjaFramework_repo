package com.tutorialninja;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(com.tutorialninja.TestListener.class)

public class AddProductCheckoutTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://tutorialsninja.com/demo");
    }

    @Test
    public void addProductAndCompleteCheckout() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

       
        driver.findElement(By.name("search")).sendKeys("MacBook");
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();

     
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("MacBook"))).click();

        
        WebElement quantityInput = driver.findElement(By.id("input-quantity"));
        quantityInput.clear();
        Thread.sleep(500);
        quantityInput.sendKeys("100");

        
        driver.findElement(By.id("button-cart")).click();

        
        try {
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-danger")));
            Assert.assertTrue(alert.getText().contains("Products marked with *** are not available"),
                "Stock warning message was not found.");
            System.out.println("Stock warning message detected. Stopping test.");
            return; 
        } catch (Exception e) {
            
        }

  
        List<WebElement> warnings = driver.findElements(By.cssSelector(".text-danger"));
        for (WebElement warning : warnings) {
            if (warning.getText().contains("Products marked with ***")) {
                Assert.fail("Quantity not available: " + warning.getText());
                return;
            }
        }

     
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}