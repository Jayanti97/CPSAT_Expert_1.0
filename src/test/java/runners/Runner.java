package runners;


//import com.wu.ReportingManager;
//import com.wu.YamlUtility;
import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;
import org.example.ReportingManager;
import org.example.YamlUtility;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import pages.BasePage;

import java.io.FileNotFoundException;
import java.io.IOException;




@RunWith(Cucumber.class)

@CucumberOptions(tags = "@godaddy",
        features = "src/test/resources/Features",

        glue = {"stepdefs"},
        plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "json:target/cucumber-reports/cucumberTestReport.json", "html:target/cucumber-reports/cucumber-pretty"})

public class Runner  {


    public void triggerSuite() throws FileNotFoundException {


    }

    @BeforeClass
    public static void check() throws IOException {
        new YamlUtility().ModifyReportConfigs();
        ReportingManager.startReport();

    }
    @AfterClass
    public static void flushReport()
    {
        new BasePage().CloseBrowser();
        ReportingManager.flushReport();
    }


}
