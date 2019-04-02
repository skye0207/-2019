package com.skye.lab2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class AutoCheckStudentInfo {
	
	public static void main(String[] args) {
		AutoCheckStudentInfo obj = new AutoCheckStudentInfo();
		File file = new File("软件测试名单.xls");
		Map<String, String> temp = obj.getInfoFromExcel(file);
		boolean result= obj.checkStudentInfo(temp);
		System.out.print(result);
	}
	
	public Map<String, String> getInfoFromExcel(File file) {
		Map<String, String> infoMap = new HashMap<String, String>();
		try {
			//创建输入流读取Excel
			InputStream is = new FileInputStream(file.getAbsolutePath());
			//jxl提供的Workbook类
			Workbook wb = Workbook.getWorkbook(is);
			//Excel的标签数量
			int sheet_size = wb.getNumberOfSheets();
			for(int index = 0; index < sheet_size; index++) {
				//每个页签创建一个Sheet对象
				Sheet sheet = wb.getSheet(index);
				//sheet.getRows()返回该页总行数
				for(int i = 0; i < sheet.getRows(); i++) {		
					String num = sheet.getCell(1, i).getContents();
					String github = sheet.getCell(3, i).getContents();
					infoMap.put(num, github);
				}
			}
		} catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (BiffException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
		
		return infoMap;
	}
	
	public boolean checkStudentInfo(Map<String, String> temp) {
		WebDriver driver;
		String baseUrl;
		
		driver = new FirefoxDriver();
	    baseUrl = "https://www.katalon.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		for(String key :  temp.keySet()) {
		    driver.get("http://121.193.130.195:8800/login");
		    driver.findElement(By.name("id")).click();
		    driver.findElement(By.name("id")).clear();
		    driver.findElement(By.name("id")).sendKeys(key);
			driver.findElement(By.name("password")).clear();
			driver.findElement(By.name("password")).sendKeys(key.substring(4, 10));
			driver.findElement(By.id("btn_login")).click();
			
			if(driver.findElement(By.id("student-id")).getText().equals(key.substring(0, 10))) {
				if(driver.findElement(By.id("student-name")).getText().equals(key.substring(10, key.length()))){
					if(driver.findElement(By.id("student-git")).getText().equals(temp.get(key))) {
						System.out.println("信息完全匹配");
					}else {
						System.out.println("git地址匹配不上");
					}
				}else {
					System.out.println("姓名匹配不上");
				}
			}else {
				System.out.println("学号匹配不上");
				driver.quit();
				return false;
			}
			
		}
		return true;
	}
	
	

}
