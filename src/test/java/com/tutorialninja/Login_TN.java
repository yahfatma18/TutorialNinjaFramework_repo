package com.tutorialninja;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Test
public class Login_TN {
	public WebDriver driver;

    @BeforeMethod
    
    public void lanchApplication() {
       
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://tutorialsninja.com/demo");
        
        driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
    }
    
    @Test
    public void validLoginTest() throws InterruptedException {
       

        driver.findElement(By.id("input-email")).sendKeys("yahfatma1821@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("Yahia@1979");
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));

        
        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed(), "Logout link not displayed - Login may have failed");

        
    }
    @AfterMethod
    public void tearDown() {
      
            driver.quit();
        }
    }


