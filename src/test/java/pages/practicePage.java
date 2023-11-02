package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static pages.BasePage.driver;
import static pages.BasePage.getChromeDriver;

public class practicePage {



//    private static WebDriver getChromeDriver() {
////        System.setProperty("webdriver.chrome.driver", DRIVER_DIR + "chromedriver.exe");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        WebDriverManager.chromedriver().setup();
//       driver = new ChromeDriver(chromeOptions);
//
//        return driver;
//    }


    public void LaunchURL() throws IOException {
        FileReader reader=new FileReader("src/main/resources/config.properties");
        Properties props=new Properties();
        props.load(reader);

//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Documents\\Selenium\\chromedriver.exe");


        getChromeDriver();
        driver.manage().window().maximize();

        driver.get(props.getProperty("AppUrl"));
//        Thread.sleep(2000);

        new TestUtils().log().info("App Launched");
    }

    public void clickHotel() {
        WebElement close=driver.findElement(By.xpath("//i[contains(@class,'close')]"));
        WebElement hotels=driver.findElement(By.xpath("//span[contains(@class,'header') and text()='Hotels']"));
//        close.click();
        hotels.click();
       new TestUtils().log();

    }
}
