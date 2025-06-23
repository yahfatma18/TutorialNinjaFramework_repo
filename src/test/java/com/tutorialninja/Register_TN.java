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

public class Register_TN {
	
	
	public WebDriver driver;

    @BeforeMethod
    public void launchApp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://tutorialsninja.com/demo");

        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.linkText("Register")).click();
    }

    @Test
    public void registerNewUserTest() {
        driver.findElement(By.id("input-firstname")).sendKeys("Fatma");
        driver.findElement(By.id("input-lastname")).sendKeys("Kara");

        
        String email = "fatma" + System.currentTimeMillis() + "@test.com";
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.id("input-password")).sendKeys("Password123");
        driver.findElement(By.id("input-confirm")).sendKeys("Password123");

        driver.findElement(By.name("agree")).click();
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

       
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content h1")));

        String successText = driver.findElement(By.cssSelector("#content h1")).getText();
        Assert.assertEquals(successText, "Your Account Has Been Created!", "Registration failed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
