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

public class SearchProduct_TN {
	public WebDriver driver;

    @BeforeMethod
    public void launchApp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://tutorialsninja.com/demo");
    }

    @Test
    public void searchProductTest() {
        driver.findElement(By.name("search")).sendKeys("iPhone");
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();

        // Wait for results
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-layout")));

        String productName = driver.findElement(By.cssSelector(".product-layout")).getText();
        Assert.assertTrue(productName.toLowerCase().contains("iphone"), "iPhone not found in search results.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
