package com.dbs.script;

import java.beans.Visibility;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;  


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty9.server.Authentication.User;

public class cardCompare {
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Revathy\\eclipse-workspace\\Interview\\drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		JavascriptExecutor js=(JavascriptExecutor)driver;

		driver.get("https://www.dbs.com.sg/personal/default.page");
		
		//click on cards
		driver.findElement(By.xpath("//*[@id=\"flpHeader\"]/header/div/div[2]/ul/li[2]/a")).click();
		//click on credit cards
		driver.findElement(By.xpath("//*[@id=\"bodywrapper\"]/div[1]/div[1]/div/ul/li[2]/a")).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//clicking "compare" button for first credit card and retrieving title
		driver.findElement(By.xpath("(//div[@class='option-alignment'])[1]")).click();
		WebElement eleActualCard1=driver.findElement(By.xpath("//*[@id=\"bodywrapper\"]/div[2]/div/div[1]/div/div/div[1]/div/div[1]/div[1]/div"));
		String actualCard1 = eleActualCard1.getText();
		System.out.println("card1 title is:" + actualCard1);
		
		//clicking "compare" button for second credit card and retrieving title
		WebElement clickCard2 = driver.findElement(By.xpath("//*[@id=\"bodywrapper\"]/div[2]/div/div[1]/div/div/div[3]/div/div[2]/div/label/div[1]/span"));
		js.executeScript("arguments[0].click();",clickCard2 );
		
		WebElement eleActualCard2=driver.findElement(By.xpath("//*[@id=\"bodywrapper\"]/div[2]/div/div[1]/div/div/div[3]/div/div[1]/div[1]/div"));
		String actualCard2 = eleActualCard2.getText();
		System.out.println("card2 title is:" + actualCard2);
		Thread.sleep(3000);
		
		//clicking compare button for selected cards
		driver.findElement(By.xpath("//*[@id=\"cardCompareBtn\"]")).click();
		Thread.sleep(3000);
		
		//fetching title of displayed credit cards
		WebElement eleCard1=driver.findElement(By.xpath("//*[@id=\"card0\"]/div[2]/div"));
		String expectedCard1 = eleCard1.getText();
		System.out.println(expectedCard1);
		
		WebElement eleCard2=driver.findElement(By.xpath("//*[@id=\"card2\"]/div[2]/div"));
		String expectedCard2 = eleCard2.getText();
		System.out.println(expectedCard2);
		
		//comparing title of displayed credit cards with previously selected credit cards
		try{  
			Assert.assertEquals(actualCard1, expectedCard1); 
			Assert.assertEquals(actualCard2, expectedCard2); 
			System.out.println("Test passed"); 
		}catch(AssertionError e) { 
			System.out.println("Test failed in asserting");
		}
		    
		 		

		
	}

}
