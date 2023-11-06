package stepdefs;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.ReportingManager;
import org.example.TestUtils;
import pages.goDaddySite;
import pages.practicePage;

import java.io.IOException;

public class MyStepdefs  extends practicePage{
    @Given("Launch url")
    public void launchUrl() throws IOException {
        new practicePage().LaunchURL();
        new TestUtils().log().info("URL Launched");
        ReportingManager.report("Navigated to the specified URL", Status.PASS);

    }

    @When("User clicks on the Hotels option")
    public void userClicksOnTheHotelsOption() {
        new practicePage().clickHotel();
        ReportingManager.report("Clicked on Hotel", Status.PASS);
    }

    @When("User should see the {string}")
    public void userShouldSeeThe(String URL) {
        new goDaddySite().validareURL(URL);
        ReportingManager.report("URL is validated", Status.PASS);
    }

    @Then("User should validate {string}")
    public void userShouldValidate(String title) {
        new goDaddySite().validatePageTitle(title);
        ReportingManager.report("Page title is validated", Status.PASS);
    }

    @Given("user is on homepage")
    public void userIsOnHomepage() {
        new goDaddySite().validatePage();
        ReportingManager.report("Page is validated", Status.INFO);
    }

    @When("user clicks on menu button")
    public void userClicksOnMenuButton() {
        new goDaddySite().clickMenuButton();
        ReportingManager.report("Menu is clicked", Status.INFO);
    }

    @Then("user clicks on each menu item and validate pages")
    public void userClicksOnEachMenuItemAndValidatePages() {
        new goDaddySite().clickEachMenuItem();
        ReportingManager.report("Each menu item is clicked and pages are validated", Status.PASS);
    }
}
