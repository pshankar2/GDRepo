package com.cw.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
	public static WebDriver driver;

	public static void main(String[] args) throws Exception {
		
		System.setProperty("webdriver.chrome.driver","C:\\Automation\\chromedriver_win32_v75\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://www.carwale.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		WebElement ele = driver.findElement(By.xpath("//span[text()='New Cars' and @class='margin-right5']"));
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
		driver.findElement(By.xpath("//div/ul/li/a[text()='Find New Cars']")).click();
		
		driver.findElement(By.xpath("//h3[text()='Body Type']")).click();
		driver.findElement(By.xpath("//span[text()='SUV/MUV']")).click();
		driver.findElement(By.xpath("//span[@id='budget_exp_col']")).click();
		driver.findElement(By.xpath("//a[text()='8-12 lakh']")).click();
		
		File src = new File("C:\\Automation\\CarwaleNew\\NewCarsRecord.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet Sheet1 = wb.getSheetAt(0);
		Sheet1.getRow(0).getCell(0).setCellValue("MODEL");
		Sheet1.getRow(0).createCell(1).setCellValue("VERSIONS");
		Sheet1.getRow(0).createCell(2).setCellValue("PRICE");
		
		
		//List<WebElement> lists = driver.findElements(By.xpath("//tr[@class='model-row version-row']"));
		List<WebElement> lists = driver.findElements(By.xpath("//span[@title='Click to expand']"));
		for(int i=0;i<lists.size();i++)
		{
			lists.get(i).click();
			//System.out.println(str);
			//Sheet1.getRow(i).createCell(3).setCellValue(str);
			//driver.findElement(By.xpath("//span[@title='Click to expand']")).click(); 
			
		}
		
		/*List<WebElement> list1 = driver.findElements(By.xpath("//a[contains(@title,'Mahindra NuvoSport N')]"));
		for(int i=0;i<list1.size();i++)
		{
			String str= list1.get(i).getText();
			System.out.println(str);
		}
		//driver.findElement(By.xpath("//a[@id='1192']/span[@title='Click to expand']")).click();
		String elt = driver.findElement(By.xpath("//a[text()='N4 Plus']")).getText();
		System.out.println(elt);
		/*List<WebElement> list1 = driver.findElements(By.xpath("//tr[contains(@class,'hide cls')]/td[2][@valign='top']"));
		for(int i=0;i<list1.size();i++)
		{
			//String str= list1.get(i).getText();
			System.out.println(list1.get(i).getText());
			//Sheet1.getRow(i).createCell(3).setCellValue(str);
		}*/
		FileOutputStream fout = new FileOutputStream(src);
		wb.write(fout);
		wb.close();

	}

}
