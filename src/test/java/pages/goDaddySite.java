package pages;

import org.example.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static pages.BasePage.driver;


public class goDaddySite {
  @FindBy(xpath="//button[@aria-label='Open Menu']")
  public WebElement MenuButton;
    public String Menu="//button[@aria-label='Open Menu']";
    public String MenuItems="//li[@data-cy='primary-nav-tray-menu-item']";
    public String backButton="(//*[@class='uxicon-svg-container'])[1]";

    @FindBy(xpath="//button[@aria-label='Search for a domain']")
    public WebElement SearchButton;




    public void validareURL(String url) {

        String CurrentURL = driver.getCurrentUrl();;

        if(CurrentURL.contains(url)) {
           new TestUtils().log().info("URL is validated");
            System.out.println(CurrentURL+" URL is validated");
        }
        else {
            System.out.println("URL does not match");
        }

    }

    public void validatePageTitle(String title) {
        String CurrentTItle = driver.getTitle();;

        if(CurrentTItle.contains(title)) {
            new TestUtils().log().info("Page title is validated");
            System.out.println(CurrentTItle+" Page title is validated");
        }
        else {
            System.out.println("Page title does not match");
        }
    }

    public void validatePage() {

        ExpectedConditions.visibilityOf(SearchButton);
    }

    public void clickMenuButton() {
        driver.findElement(By.xpath(Menu)).click();

    }



    public void clickEachMenuItem() {
        List<WebElement> MenuIT=driver.findElements(By.xpath(MenuItems));
        for(int i=0;i<=MenuIT.size();i++){
            MenuIT.get(i).click();
            driver.findElement(By.xpath(backButton));


        }
    }
}
