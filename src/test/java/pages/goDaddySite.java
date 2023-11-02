package pages;

import org.example.TestUtils;

import static pages.BasePage.driver;


public class goDaddySite {
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

    }
}
