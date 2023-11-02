package pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
//        import io.appium.java_client.remote.SupportsContextSwitching;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.example.DriverManager;
import org.example.JSExecutor;
import org.example.PropertyManager;
import org.example.TestUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
        import org.openqa.selenium.interactions.Actions;

        import org.openqa.selenium.remote.RemoteWebDriver;
        import org.openqa.selenium.support.Color;
        import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.Select;
        import org.openqa.selenium.support.ui.WebDriverWait;
//        import stepdef.AdvancedWebSessionHandler;

//import java.awt.*;
import java.io.IOException;
import java.util.*;
        import java.util.List;
        import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.offset.PointOption.point;

public class BasePage {

    public static String firstName;
    public static String nickName;
    public static String lastName;
    public static String middleName;
    public static String streetName;
    public static String city;
    public static String state;
    public static String postalCode;
//    public static RemoteWebDriver driver;
public static WebDriver driver;
    public static String last3Digits;
    static TestUtils utils = new TestUtils();
    public static int decider = 0;
    public static String region;


    static {
        try {
            region = new PropertyManager().getProps().getProperty("WUPlus_Country");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static WebDriver getChromeDriver() {
//        System.setProperty("webdriver.chrome.driver", DRIVER_DIR + "chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);

        return driver;
    }

    public void moveToElement(WebElement ele) {
        waitForVisibility(ele);
        Actions actions = new Actions(new DriverManager().getWebDriver());
        actions.moveToElement(ele).build().perform();

    }

    public void scrollToElementAndClick(WebElement ele)
    {
        waitForVisibility(ele);
        new JSExecutor().scrollToElementMobile(ele);
        clickOnVisibility(ele);
    }
//    public void scrollToElementAndClick1(String element) throws ParseException, IOException {
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//        waitForVisibility((WebElement) eleBy);
//        new JSExecutor().scrollToElementMobile((WebElement) eleBy);
//        clickOnVisibility((WebElement) eleBy);
//    }


    public void fillData(WebElement ele, String data) {
        WebDriverWait wait = getInstance();
        wait.until(ExpectedConditions.visibilityOf(ele));
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ele.sendKeys(data);
    }

    public void fillDataUsingActions(WebElement ele, String data) {
        WebDriverWait wait = getInstance();
        wait.until(ExpectedConditions.visibilityOf(ele));
        Actions actions = new Actions(new DriverManager().getWebDriver());
        letsWait(200);
        actions.moveToElement(ele).click().sendKeys(data).build().perform();
    }

    public void actionsClassTabHelper(String data) {
        Actions actions = new Actions(new DriverManager().getWebDriver());
        actions.sendKeys(Keys.TAB).sendKeys(data).build().perform();
    }

    public void fillDataWithActionsClass(WebElement ele, String data) {
        WebDriverWait wait = getInstance();
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        Actions actions = new Actions(new DriverManager().getWebDriver());
        actions.moveToElement(ele).click().sendKeys(data).build().perform();
    }

    public void selectDropDownValueByIndex(int index, WebElement ele) {
        Select select = new Select(ele);
        select.selectByIndex(index);
    }

    public void selectDropDownValueByVisibleText(String text, WebElement ele) {

        Select select = new Select(ele);
        select.selectByVisibleText(text);

    }

    public void selectDropDownValueByValue(String value, WebElement ele) {

        Select select = new Select(ele);
        select.selectByValue(value);
        letsWait(200);
    }


    public void letsWait(int wait) {
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


//    public void matchTextAndClick(String text, String element) throws ParseException, IOException {
//
//
//        if (!new PropertyManager().getProps().getProperty("AppPlatform").equals("Web"))
//            for (WebElement ele : getMatchedElements(element)) {
//                new TestUtils().log().info("searching....");
//                try {
//
//                    if (ele.getText().contains(text)) {
//                        new TestUtils().log().info("found....");
//                        try {
//                            Thread.sleep(200);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            clickOnVisibility(ele);
//                        } catch (ElementClickInterceptedException ec) {
//                            new TestUtils().log().info("in try catch -2 , unable to find on screen, waiting....");
//                            letsWait(500);
//                            try {
//                                new TestUtils().log().info("inside try block-2");
//                                clickOnVisibility(ele);
//                            } catch (ElementClickInterceptedException ec1) {
//                                new TestUtils().log().info("in try catch -2 , unable to find on screen, scrolling.....");
//                                powerScroll(ele);
//                            }
//                        }
//
//                        new TestUtils().log().info("clicked....");
//                        break;
//                    }
//                } catch (Exception ne) {
//                    List<WebElement> list1 = getMatchedElements(element);
//                    for (WebElement ele1 : list1) {
//                        new TestUtils().log().info("searching....");
//                        try {
//                            Thread.sleep(300);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        if (ele.getText().contains(text)) {
//                            new TestUtils().log().info("found....");
//                            try {
//                                Thread.sleep(200);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                clickOnVisibility(ele1);
//                            } catch (ElementClickInterceptedException ec) {
//                                new TestUtils().log().info("in try catch -2 , unable to find on screen, waiting....");
//                                letsWait(500);
//                                try {
//                                    new TestUtils().log().info("inside try block-2");
//                                    clickOnVisibility(ele1);
//                                } catch (ElementClickInterceptedException ec1) {
//                                    new TestUtils().log().info("in try catch -2 , unable to find on screen, scrolling.....");
//                                    powerScroll(ele1);
//                                }
//                            }
//
//                            new TestUtils().log().info("clicked....");
//                            break;
//                        }
//                    }
//                }
//
//
//            }
//
//        else {
//            new TestUtils().log().info("size found in dropdown - " + getMatchedWebElements(element)
//                    .size());
//            new TestUtils().log().info("click & match web triggered");
//            for (WebElement ele : getMatchedWebElements(element)) {
//                new TestUtils().log().info("dropdown value -" + ele.getText());
//                if (ele.getText().equals(text)) {
//                    new TestUtils().log().info("web hover action triggered");
//                    clickOnVisibility(ele);
//                    new TestUtils().log().info("web hover action & click completed");
//                    break;
//                }
//
//            }
//        }
//
//
//    }

    public void matchTextAndClickByListElement(String text, List<WebElement> element) throws ParseException, IOException {

        List<WebElement> list = element;
        new TestUtils().log().info(list.size());

        for (WebElement ele : list) {
            new TestUtils().log().info("searching...." + ele.getText());
            try {
                if (ele.getText().equals(text)) {
                    new TestUtils().log().info("found....");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        clickOnVisibility(ele);
                    } catch (ElementClickInterceptedException ec) {
                        new TestUtils().log().info("in try catch -2 , unable to find on screen, waiting....");
                        letsWait(500);
                        try {
                            new TestUtils().log().info("inside try block-2");
                            clickOnVisibility(ele);
                        } catch (StaleElementReferenceException se) {
                            letsWait(500);
                            clickOnVisibility(ele);
                        } catch (ElementClickInterceptedException ec1) {
                            new TestUtils().log().info("in try catch -2 , unable to find on screen, scrolling.....");
                            powerScroll(ele);
                        }
                    }

                    new TestUtils().log().info("clicked....");
                    break;
                }
            } catch (Exception ne) {
                List<WebElement> list1 = element;
                for (WebElement ele1 : list1) {
                    new TestUtils().log().info("searching....");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (ele.getText().equals(text)) {
                        new TestUtils().log().info("found....");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            clickOnVisibility(ele1);
                        } catch (ElementClickInterceptedException ec) {
                            new TestUtils().log().info("in try catch -2 , unable to find on screen, waiting....");
                            letsWait(500);
                            try {
                                new TestUtils().log().info("inside try block-2");
                                clickOnVisibility(ele1);
                            } catch (ElementClickInterceptedException ec1) {
                                new TestUtils().log().info("in try catch -2 , unable to find on screen, scrolling.....");
                                powerScroll(ele1);
                            }
                        }

                        new TestUtils().log().info("clicked....");
                        break;
                    }
                }
            }


        }
    }


    public void matchContainsTextAndClickByListElement(String text, List<WebElement> element) throws ParseException, IOException {
        List<WebElement> list = element;
        new TestUtils().log().info(list.size());

        for (WebElement ele : list) {
            new TestUtils().log().info("searching...." + ele.getText());
            try {
                if (ele.getText().contains(text)) {
                    new TestUtils().log().info("found....");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        click(ele);
                    } catch (ElementClickInterceptedException ec) {
                        new TestUtils().log().info("in try catch -2 , unable to find on screen, waiting....");
                        letsWait(500);
                        try {
                            new TestUtils().log().info("inside try block-2");
                            click(ele);
                        } catch (ElementClickInterceptedException ec1) {
                            new TestUtils().log().info("in try catch -2 , unable to find on screen, scrolling.....");
                            powerScroll(ele);
                        }
                    }

                    new TestUtils().log().info("clicked....");
                    break;
                }
            } catch (Exception ne) {
                List<WebElement> list1 = element;
                for (WebElement ele1 : list1) {
                    new TestUtils().log().info("searching....");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (ele.getText().contains(text)) {
                        new TestUtils().log().info("found....");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            click(ele1);
                        } catch (ElementClickInterceptedException ec) {
                            new TestUtils().log().info("in try catch -2 , unable to find on screen, waiting....");
                            letsWait(500);
                            try {
                                new TestUtils().log().info("inside try block-2");
                                click(ele1);
                            } catch (ElementClickInterceptedException ec1) {
                                new TestUtils().log().info("in try catch -2 , unable to find on screen, scrolling.....");
                                powerScroll(ele1);
                            }
                        }

                        new TestUtils().log().info("clicked....");
                        break;
                    }
                }
            }
        }
    }


//    public HashMap<String, String> fetchTextForElements(List<String> references, List<String> elements) throws ParseException, IOException {
//
//        new TestUtils().log().info("fetching text for " + references);
//        HashMap<String, String> elementAndTxt = new HashMap<>();
//        for (int i = 0; i < references.size(); i++) {
//            if (!new PropertyManager().getProps().getProperty("AppPlatform").equals("Web")) {
//                new TestUtils().log().info("text fetched -> " + new DriverManager().getDriver().
//                        findElement(new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), elements.get(i))
//                        ).getText());
//                elementAndTxt.put(references.get(i), new DriverManager().getDriver().
//                        findElement(new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), elements.get(i))
//                        ).getText());
//            } else {
//                new TestUtils().log().info("text fetched -> " + new DriverManager().getWebDriver().
//                        findElement(new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), elements.get(i))
//                        ).getText());
//                elementAndTxt.put(references.get(i), new DriverManager().getWebDriver().
//                        findElement(new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), elements.get(i))
//                        ).getText());
//            }
//
//        }

//        new TestUtils().log().info("in basePage -> " + elementAndTxt);
//
//        return elementAndTxt;
//
//    }



    public void enterText(WebElement ele,String value)
    {
        waitForVisibility(ele);
        ele.sendKeys(value);
    }








//    public void fetchAndMatchTextForElement(String element, String expectedTxt) throws ParseException, IOException {
//
//
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//
//        WebDriverWait wait = getInstance();
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//            Assert.assertEquals(expectedTxt, new DriverManager().getDriver().findElement(
//                    eleBy).getText());
//
//        } catch (StaleElementReferenceException w) {
//            try {
//                Thread.sleep(700);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//            wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//            Assert.assertEquals(expectedTxt, new DriverManager().getDriver().findElement(
//                    eleBy).getText());
//        }
//
//
//    }


//    public void lookForElement(String element) throws ParseException, IOException {
//
//
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//
//        WebDriverWait wait = getInstance();
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//
//        } catch (StaleElementReferenceException w) {
//            try {
//                Thread.sleep(700);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//            wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//        }
//
//
//    }
//    public void lookForElement1(String element) throws ParseException, IOException {
//
//
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//
//        WebDriverWait wait = new WebDriverWait(new DriverManager().getWebDriver(),Duration.ofSeconds(TestUtils.WAIT));
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//
//        } catch (StaleElementReferenceException w) {
//            try {
//                Thread.sleep(700);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//            wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//        }
//
//
//    }
//
//    public void ElementPresence(String element) throws ParseException, IOException, InterruptedException {
//
//        Thread.sleep(9000);
//
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//
//        WebDriverWait wait = getInstance();
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//            ReportingManager.report("The element not responded and no navigation done", Status.FAIL);
//
//        } catch (TimeoutException te) {
//
//        }
//
//
//    }


//    public void fetchTextForElementAndMatch(String matchType, String expectedText, String element) throws ParseException, IOException {
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//
//        if (matchType.equals("view")) {
//            Assert.assertEquals(true, new DriverManager().getDriver().findElement(
//                            eleBy).getText().replace(" ", "").
//                    equals(expectedText.replace(" ", "")));
//        } else {
//            Assert.assertEquals(true, new DriverManager().getDriver().findElement(
//                            eleBy).getText().replace(" ", "").
//                    contains(expectedText.replace(" ", "")));
//        }
//
//
//    }

//    public String fetchText(String element) throws ParseException, IOException {
//
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//        new TestUtils().log().info("fetching text for " + element + " in base page");
//        new TestUtils().log().info(eleBy);
//        return new DriverManager().getDriver().findElement(eleBy).getText();
//
//
//    }






    public synchronized static void powerScroll(WebElement ele) {

        waitForVisibility(ele);
        powerScrollMaster(ele);
    }

    public synchronized static void powerScrollMaster(WebElement ele) {

        new JSExecutor().scrollToElementMobile(ele);
        new TestUtils().log().info("scroll done as is");
        for (int i = 0; i < 10; i++) {
            try {
                new JSExecutor().scrollToElementMobile(ele);
                ele.click();
                break;
            } catch (ElementClickInterceptedException e) {
                new JSExecutor().scrollToElementMobile(ele);
            }


        }




    }

//    public void matchIndexAndClick(String element, int index) throws ParseException, IOException {
//
//        List<WebElement> list = getMatchedElements(element);
//        new TestUtils().log().info(list.size());
//
//        for (int i = 0; i < list.size(); i++) {
//            if (i == index) {
//                clickOnVisibility(list.get(i));
//                i = list.size();
//            }
//        }
//    }



//    public List<WebElement> getMatchedElements(String element) throws ParseException, IOException {
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
//        new TestUtils().log().info(eleBy);
//        List<WebElement> matchedElements;
//        matchedElements = new DriverManager().getDriver().findElements(
//                eleBy);
//
//        return matchedElements;
//    }

//    public List<WebElement> getMatchedWebElements(String element) throws ParseException, IOException {
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(TestUtils.WAIT));
//        new TestUtils().log().info(eleBy);
//        List<WebElement> matchedElements;
//        matchedElements = new DriverManager().getWebDriver().findElements(
//                eleBy);
//
//        return matchedElements;
//    }




    public void manageScreen(String txt) {
        Actions actions = new Actions(new DriverManager().getDriver());
        actions.sendKeys(txt).build().perform();
        // new DriverManager().getDriver().hideKeyboard(); -- CR
    }

    public void manageScreen1(String txt) {
        Actions actions = new Actions(new DriverManager().getDriver());
        actions.sendKeys(txt).build().perform();

    }

    public void manageScreenHideKeyboard(String txt) {
        Actions actions = new Actions(new DriverManager().getDriver());
        actions.sendKeys(txt).build().perform();
        new TestUtils().log().info("text entered using actions");
        try {
            // new DriverManager().getDriver().hideKeyboard(); -- CR
        } catch (WebDriverException we) {
            try {
                Thread.sleep(100);
                // new DriverManager().getDriver().hideKeyboard(); -- CR

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

//    public void clearData(String element) throws ParseException, IOException {
//        if (new AdvancedWebSessionHandler().getCurrentPlatform().equals("Mobile")) {
//            new DriverManager().getDriver().findElement(By.xpath(new TestUtils().getXpath(new PageHandler().getCurrentScreenName(), element)))
//                    .clear();
//        } else {
//            new DriverManager().getWebDriver().findElement(By.xpath(new TestUtils().getXpath(new PageHandler().getCurrentScreenName(), element)))
//                    .clear();
//        }
//    }

//    public void checkElementState(String element, String expState) throws ParseException, IOException {
//
//        boolean currentState = new DriverManager().getWebDriver().
//                findElement(By.xpath(new TestUtils().getXpath(new PageHandler().getCurrentScreenName(), element))).isEnabled();
//
//        switch (expState) {
//            case "enabled":
//                Assert.assertTrue(currentState);
//                break;
//            case "disabled":
//                Assert.assertFalse(currentState);
//                break;
//        }
//    }

//    public void checkElementStateByElementType(String element, String expState) throws ParseException, IOException {
//
//        boolean currentState;
//        System.out.println("Screen name: " + new PageHandler().getCurrentScreenName());
//        WebElement ele = new DriverManager().getWebDriver().findElement(By.xpath(new TestUtils().getXpath(new PageHandler().getCurrentScreenName(), element)));
//        System.out.println(ele);
//        System.out.println(ele.getAttribute("class"));
//        if (new DriverManager().getWebDriver().findElement(By.xpath(new TestUtils().getXpath(new PageHandler().getCurrentScreenName(), element)))
//                .getAttribute("class").contains("isable")) {
//            currentState = false;
//        } else {
//            currentState = true;
//        }
//
//        switch (expState) {
//            case "enabled":
//                Assert.assertTrue(currentState);
//                break;
//            case "disabled":
//                Assert.assertFalse(currentState);
//                break;
//        }
//    }


    public static void update_timeout(int sec) {
        new DriverManager().getDriver().manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

    public static void set_timeout() {
        new DriverManager().getDriver().manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
    }


//    public void setNativeContext() {
//
//        ((SupportsContextSwitching) new DriverManager().getDriver()).context("NATIVE_APP");
//
//    }


    public BasePage() {
//
//        try {
//            if (!new PropertyManager().getProps().getProperty("AppPlatform").equals("Web")) {
//                this.driver =  new DriverManager().getDriver();
//                PageFactory.initElements(new AppiumFieldDecorator(new DriverManager().getDriver()), this);
//            } else {
//                this.webDriver = new DriverManager().getWebDriver();
//                PageFactory.initElements(new DriverManager().getWebDriver(), this);
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }

    }


    public void navigateBack() {
        new DriverManager().getWebDriver().navigate().back();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void clickOnVisibility(WebElement ele) {
        waitForVisibility(ele);
        try {
            ele.click();
        } catch (ElementClickInterceptedException ec) {
//            new SetupPassCodePage().dummyWait(3);
            try {
                ele.click();
            } catch (ElementClickInterceptedException ec1) {
//                new SetupPassCodePage().dummyWait(2);
                ele.click();
            }

        }

    }

//    public void mouseHoverAction(String ele) throws ParseException, IOException {
//        WebDriverWait wait = getInstance();
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), ele);
//        WebElement element = new DriverManager().getWebDriver().findElement(eleBy);
//        new JSExecutor().scrollToElementMobile(element);
//
//
//    }

    public void mouseHoverActionWebElement(WebElement ele) throws ParseException, IOException {
        Actions actions = new Actions(new DriverManager().getWebDriver());
        actions.moveToElement(ele).build().perform();

    }

    public void mouseHoverActionAndClickWebElement(WebElement ele) throws ParseException, IOException {
        Actions actions = new Actions(new DriverManager().getWebDriver());
        actions.moveToElement(ele).click().build().perform();

    }


    public void mouseHoverActionAndClickAndHoldWebElement(WebElement ele) throws ParseException, IOException {
        Actions actions = new Actions(new DriverManager().getWebDriver());
        actions.moveToElement(ele).click().build().perform();

    }

    public static char RandomAlphabet() {

        return (char) ('a' + new Random().nextInt(26));

    }


    public int RandomAmount() {
        int k = new Random().nextInt(100) + new Random().nextInt(100);
        new TestUtils().log().info("amount - " + k);
        return k;
    }











//    public void copy(String s) {
//        String myString = s;
//        StringSelection stringSelection = new StringSelection(myString);
//        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//        clipboard.setContents(stringSelection, null);
//    }

    public static void scrollUp() {



    }






    public static void scrollDwn() {


//        Dimension dm = new DriverManager().getDriver().manage().window().getSize();
//        int startX = (int) (dm.width * 0.5);
//        int startY = (int) (dm.height * 0.8);
//        int endX = (int) (dm.width * 0.3);
//        int endY = (int) (dm.height * 0.3);
//        TouchAction touchAction = new TouchAction(new DriverManager().getDriver());
//        touchAction.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(0))).moveTo(PointOption.point(endX, endY)).release().perform();


    }



    public void powerScrollElement(WebElement ele)
    {
        new JSExecutor().scrollToElement(ele);
    }
//    public synchronized static void powerScroll(String element) throws ParseException, IOException {
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//        WebElement ele;
//        if (new PropertyManager().getProps().getProperty("AppPlatform").equals("Web")){
//            ele= new DriverManager().getWebDriver().findElement
//                    (eleBy);}
//        else
//        {
//            ele= new DriverManager().getDriver().findElement
//                    (eleBy);
//        }
//
//
//
//        for (int i = 0; i < 10; i++) {
//            try {
//                new JSExecutor().scrollToElement(ele);
//                ele.click();
//                break;
//            } catch (ElementClickInterceptedException e) {
//                new JSExecutor().scrollToElement(ele);
//                new TestUtils().log().info("Track i value - " + i);
//
//            } catch (StaleElementReferenceException e1) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                ele.click();
//                break;
//            }
//
//
//        }
//    }


//    public static void waitForVisibilityCustomized(WebElement e) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
//
//        wait.until(ExpectedConditions.visibilityOf(e));
//
//
//    }

    public void clearDataUsingActions() {
        Actions actions = new Actions(new DriverManager().getDriver());
        actions.doubleClick();
        actions.sendKeys(Keys.CONTROL, "a").build().perform();;
        //actions.sendKeys(Keys.CONTROL).sendKeys("C").build().perform();
        actions.sendKeys(Keys.BACK_SPACE).build().perform();;

    }

//    public void clearDataInField(String element) throws ParseException, IOException {
//        WebDriverWait wait;
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//        if (new AdvancedWebSessionHandler().getCurrentPlatform().contains("web")) {
//            wait = getInstance();
//            try {
//                wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//                new DriverManager().getWebDriver().findElement(
//                        eleBy).clear();
//
//            } catch (StaleElementReferenceException w) {
//                wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//                new DriverManager().getWebDriver().findElement(
//                        eleBy).clear();
//
//            }
//        } else {
//            wait = getInstance();
//            try {
//                wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//                new DriverManager().getDriver().findElement(eleBy).click();
//                clearDataUsingActions();
//
//            } catch (StaleElementReferenceException | ElementClickInterceptedException w) {
//                try {
//                    Thread.sleep(700);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//                wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//                new DriverManager().getDriver().findElement(eleBy).click();
//                clearDataUsingActions();
//            }
//        }
//
//
//    }

    public void clickOnVisibilityBy(By eleBy) {

        WebDriverWait wait = new WebDriverWait(new DriverManager().getDriver(),2);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
            new DriverManager().getDriver().findElement(
                    eleBy).click();

        } catch (StaleElementReferenceException w) {
            try {
                Thread.sleep(700);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
            new DriverManager().getDriver().findElement(
                    eleBy).click();
        } catch (ElementClickInterceptedException e) {
            try {
                Thread.sleep(700);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
            new DriverManager().getDriver().findElement(
                    eleBy).click();
        }

    }

    public boolean letsPutIterativeWaitForElementToBeDisplayed(int iterations, int timeInterval, By el) {
        boolean decider = false;
        for (int i = 0; i < iterations; i++) {
            if (new DriverManager().getWebDriver().findElement(
                    el).isDisplayed()) {
                decider = true;
                new TestUtils().log().info("element now displayed & breaking the loop");
                break;
            } else {
                new TestUtils().log().info("element not displayed ");
//                new LoginPage().dummyWait(timeInterval);
            }
        }
        return decider;

    }

    public boolean letsPutIterativeWaitForElementToBeDisplayed(int iterations, int timeInterval, WebElement el) {
        boolean decider = false;
        for (int i = 0; i < iterations; i++) {
            if (el.isDisplayed()) {
                decider = true;
                new TestUtils().log().info("element now displayed & breaking the loop");
                break;
            } else {
                new TestUtils().log().info("element not displayed ");
//                new LoginPage().dummyWait(timeInterval);
            }
        }
        return decider;

    }

//    public void passSendKeys(String element, String data) throws ParseException, IOException {
//        WebDriverWait wait;
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//        wait = getInstance();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//        wait.until(ExpectedConditions.elementToBeClickable(eleBy));
//        new DriverManager().getDriver().findElement(eleBy).sendKeys(data);
//    }

//    public void clickOnVisibility(String element) throws ParseException, IOException {
//
//        WebDriverWait wait;
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//        if (new AdvancedWebSessionHandler().getCurrentPlatform().contains("Web")) {
//            wait = new WebDriverWait(new DriverManager().getWebDriver(), Duration.ofSeconds(1));
//            try {
//                wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//                wait.until(ExpectedConditions.elementToBeClickable(eleBy));
//                new JSExecutor().jsExeHighLightMe(new DriverManager().getWebDriver().findElement(eleBy));
//
//                if (letsPutIterativeWaitForElementToBeDisplayed(5, 2, eleBy))
//                    new DriverManager().getWebDriver().findElement(
//                            eleBy).click();
//                else {
//                    // to be added
//                }
//
//            } catch (StaleElementReferenceException w) {
//                wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//                new DriverManager().getWebDriver().findElement(
//                        eleBy).click();
//
//            } catch (ElementClickInterceptedException ec1) {
//                WebElement element1 = new DriverManager().getWebDriver().findElement(eleBy);
//                new JSExecutor().jsExeHighLightMe(element1);
//                Actions actions = new Actions(new DriverManager().getWebDriver());
//                actions.moveToElement(element1).click().build().perform();
//            }
//        } else {
//            wait = getInstance();
//            try {
//                wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//                new DriverManager().getDriver().findElement(
//                        eleBy).click();
//
//            } catch (StaleElementReferenceException w) {
//                try {
//                    Thread.sleep(700);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//                wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//                new DriverManager().getDriver().findElement(
//                        eleBy).click();
//            } catch (ElementClickInterceptedException e) {
//                try {
//                    Thread.sleep(700);
//                    powerScroll(element);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//
//                }
////                wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
////                new DriverManager().getDriver().findElement(
////                        eleBy).click();
//            }
//        }
//
//
//    }

//    public void clearTheIPFld(String element) throws ParseException, IOException {
//
//
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//        if (new PropertyManager().getProps().getProperty("AppPlatform").equals("Web")) {
//            new DriverManager().getWebDriver().findElement(
//                    eleBy).clear();
//
//        } else {
//            new DriverManager().getDriver().findElement(
//                    eleBy).clear();
//
//
//        }




//    public void clearTheIPFldAndInputData(String element, String value) throws ParseException, IOException {
//
//
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//        if (new PropertyManager().getProps().getProperty("AppPlatform").equals("Web")) {
//            new DriverManager().getWebDriver().findElement(
//                    eleBy).click();
//            letsWait(300);
//        } else {
//
//            new DriverManager().getDriver().findElement(eleBy).click();
//            letsWait(300);
//            new Actions(new DriverManager().getDriver()).doubleClick(new DriverManager().getDriver().findElement(eleBy));
//            new Actions(new DriverManager().getDriver()).sendKeys(Keys.chord(Keys.CONTROL, "a")).build().perform();
//            new Actions(new DriverManager().getDriver()).sendKeys(Keys.DELETE).build().perform();
////            new BasePage().setNativeContext();
////            new BasePage().letsWait(500);
////            AndroidDriver driver2 = (AndroidDriver) new DriverManager().getDriver();
////            driver2.pressKey(new KeyEvent(AndroidKey.DEL));
////            new BasePage().set_webviewContext();
//            manageScreen(value);
//            letsWait(300);
//        }
//
//
//    }
//
//    public void fillDataUsingSendKey(String element, String data) throws ParseException, IOException {
//        WebDriverWait wait;
//        if (new AdvancedWebSessionHandler().getCurrentPlatform().contains("Web")) {
//            wait = new WebDriverWait(new DriverManager().getWebDriver(), Duration.ofSeconds(1));
//            By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//
//
//            try {
//                wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//                wait.until(ExpectedConditions.elementToBeClickable(eleBy));
//                Actions actions = new Actions(new DriverManager().getWebDriver());
//                actions.moveToElement(new DriverManager().getWebDriver().findElement(
//                        eleBy)).click().sendKeys(data).build().perform();
//
//            } catch (StaleElementReferenceException w) {
//                wait.until(ExpectedConditions.visibilityOfElementLocated(eleBy));
//                new DriverManager().getWebDriver().findElement(
//                        eleBy).click();
//
//            } catch (ElementClickInterceptedException ec1) {
//                WebElement element1 = new DriverManager().getWebDriver().findElement(eleBy);
//                new JSExecutor().jsExeHighLightMe(element1);
//                Actions actions = new Actions(new DriverManager().getWebDriver());
//                actions.moveToElement(element1).click().build().perform();
//            }
//        }
//
//
//    }
//
//    public static String iOSWebContext;
//
//    public void set_webviewContext() {
//        new TestUtils().log().info("context setting up.......");
//        try {
//            if (!new GlobalParams().getPlatformName().contains("iOS"))
//                ((SupportsContextSwitching) new DriverManager().getDriver()).context(new PropertyManager().getProps().getProperty("WEBVIEW"));
//            else
//                ((SupportsContextSwitching) new DriverManager().getDriver()).context(iOSWebContext);
//            new TestUtils().log().info("context switched to web-view.......");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void changeViewContext() {
//        new TestUtils().log().info("context setting up.......");
//        Set<String> contexts = ((SupportsContextSwitching) new DriverManager().getDriver()).getContextHandles();
//        for (String s : contexts)
//            System.out.println(s);
//
////        NATIVE_APP
////        WEBVIEW_com.outsystemsenterprise.wutst4.Wallet
//        try {
//            ((SupportsContextSwitching) new DriverManager().getDriver()).getContext();
//            ((SupportsContextSwitching) new DriverManager().getDriver()).context(new PropertyManager().getProps().getProperty("WEBVIEW"));
//            new TestUtils().log().info("context switched to web-view.......");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void waitForVisibilityByLocatorValue(By ele) {
//        WebDriverWait wait;
//        if (!new AdvancedWebSessionHandler().getCurrentPlatform().equals("Mobile")) {
//            new TestUtils().log().info("let me in");
//            wait = getInstance();
//        } else {
//            wait = getInstance();
//        }
//
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
//        } catch (StaleElementReferenceException w) {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
//        } catch (WebDriverException we) {
//            try {
//                Thread.sleep(150);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
//        }
//
//    }



    public static void waitForVisibility(WebElement ele) {

        WebDriverWait wait = getInstance();

        try {
            wait.until(ExpectedConditions.visibilityOf(ele));
        } catch (StaleElementReferenceException w) {
            wait.until(ExpectedConditions.visibilityOf(ele));
        }

    }


//    public void updateImplicitWait(long sec) {
//        new DriverManager().getWebDriver().manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
//    }


    @SneakyThrows
    public static WebDriverWait getInstance()  {
        WebDriverWait wait = null;
        switch (new PropertyManager().getProps().getProperty("AppPlatform")) {
            case "Mobile":
                wait = new WebDriverWait(new DriverManager().getDriver(), TestUtils.WAIT);
                break;
            case "Web":
                wait = new WebDriverWait(new DriverManager().getWebDriver(), TestUtils.WAIT);
                break;
        }
        return wait;
    }

    public WebDriverWait getInstance(int sec) throws IOException {
        WebDriverWait wait = null;
        switch (new PropertyManager().getProps().getProperty("AppPlatform")) {
            case "Mobile":
                wait = new WebDriverWait(new DriverManager().getDriver(), sec);
                break;
            case "Web":
                wait = new WebDriverWait(new DriverManager().getWebDriver(),(sec));
                break;
        }
        return wait;
    }

    public void waitForVisibility(WebElement e,int sec)  {

        WebDriverWait wait = null;
        try {
            wait = getInstance(sec);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(e));
        } catch (StaleElementReferenceException s) {
            wait.until(ExpectedConditions.visibilityOf(e));
        }

    }




    public static void lessWaitForVisibility(WebElement e) {

        WebDriverWait wait = getInstance();
        try {
            wait.until(ExpectedConditions.visibilityOf(e));
        } catch (StaleElementReferenceException s) {
            wait.until(ExpectedConditions.visibilityOf(e));
        }

    }

    public static void waitFortobCllickble(WebElement e) {
        WebDriverWait wait = getInstance();
        wait.until(ExpectedConditions.visibilityOf(e));
    }








    public void clear(WebElement e) {
        waitForVisibility(e);
        e.clear();
    }

    public void click(WebElement e) {
        waitForVisibility(e);
        e.click();
    }

    public static List<String> eleTxtValues;

    public List<String> LoadActualTextByTag(String tagName) throws IOException {
        List<WebElement> ele;
        if (!new PropertyManager().getProps().getProperty("AppPlatform").equals("Web"))
            ele = new DriverManager().getDriver().findElements(By.xpath("//" + tagName + "[contains(text(),\" \")]"));
        else
            ele = new DriverManager().getWebDriver().findElements(By.xpath("//" + tagName + "[contains(text(),\" \")]"));
        new TestUtils().log().info(ele.size() + " - found text elements which has tag - " + tagName + " tag");
        List<String> eleTxtValues = new ArrayList<>();
        for (WebElement e : ele) {
            try {
                eleTxtValues.add(e.getText());
            } catch (StaleElementReferenceException se) {
                letsWait(2000);
                eleTxtValues.add(e.getText());

            }

        }
        new TestUtils().log().info(eleTxtValues.size() + " - fetching text is completed for - " + tagName + " tag");
        return eleTxtValues;
    }


    public void ClickAndDrag() throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement element1 = driver.findElement(By.xpath("//div[@class=\"draggable-content__dragger bounce2 transparentbackground\"]"));
        WebElement element2 = driver.findElement(By.xpath("//div[@class=\"main-content\"]"));

        Action clickAndDrag = action.clickAndHold(element1)
                .moveToElement(element2)
                .release(element1)
                .build();
        clickAndDrag.perform();
        Thread.sleep(3000);


    }

//    public static void dragAction(String element) throws ParseException, IOException, InterruptedException {
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), element);
//        WebElement ele = new DriverManager().getDriver().findElement(eleBy);
//        Actions move = new Actions(driver);
//        move.moveToElement(ele).clickAndHold().moveByOffset(0, 200).release().build().perform();
//        Thread.sleep(3500);
//        System.out.println("drag achieved");
//
//        //       WebElement element2= driver.findElement(By.xpath("//div[@class=\"main-content\"]"));
////        Point point = element2.getLocation();
////        System.out.println("xcord = "+point.getX());
////        System.out.println("ycord = "+point.getY());
////        WebElement element1= new DriverManager().getDriver().findElement(By.xpath("//div[@class=\"main-content\"]"));
////        new Actions(new DriverManager().getDriver()).dragAndDrop(ele,element1);
//        // WebElement e = driver.findElement(By.className("Class name of Dragger"));
//    }


//    public void storeDynamicValueFromPage(String page) throws IOException, ParseException {
//        switch (page) {
//            case "Who are you paying":
//                String BillerNameValue = new DriverManager().getDriver().findElement(By.xpath(new TestUtils().getXpath(new PageHandler().getCurrentScreenName(), "Biller name value"))).getText();
//                new PageHandler().memory("Biller name value", BillerNameValue);
//                String AccountNunmberValue = "";
//                new PageHandler().memory("Account number value", AccountNunmberValue);
//                String AmountValue = "";
//                new PageHandler().memory("Amount value", AmountValue);
//                break;
//            case "Choose payment and delivery service":
//                String PaymentMethodValue = new DriverManager().getDriver().findElement(By.xpath(new TestUtils().getXpath(new PageHandler().getCurrentScreenName(), "Payment method value"))).getText();
//                ;
//                new PageHandler().memory("Payment method value", PaymentMethodValue);
//                String DeliveryServiceValue = new DriverManager().getDriver().findElement(By.xpath(new TestUtils().getXpath(new PageHandler().getCurrentScreenName(), "Delivery service value"))).getText();
//                ;
//                new PageHandler().memory("Delivery service value", DeliveryServiceValue);
//                String TotalValue = new DriverManager().getDriver().findElement(By.xpath(new TestUtils().getXpath(new PageHandler().getCurrentScreenName(), "Total value"))).getText();
//                ;
//                new PageHandler().memory("Total value", TotalValue);
//                String DeliveryTimeValue = new DriverManager().getDriver().findElement(By.xpath(new TestUtils().getXpath(new PageHandler().getCurrentScreenName(), "Delivery time value"))).getText();
//                ;
//                new PageHandler().memory("Delivery time value", DeliveryTimeValue);
//                String TransferAmountValue = new DriverManager().getDriver().findElement(By.xpath(new TestUtils().getXpath(new PageHandler().getCurrentScreenName(), "Transfer amount value"))).getText();
//                ;
//                new PageHandler().memory("Transfer amount value", TransferAmountValue);
//                String TransferFeeValue = new DriverManager().getDriver().findElement(By.xpath(new TestUtils().getXpath(new PageHandler().getCurrentScreenName(), "Transfer fee value"))).getText();
//                ;
//                new PageHandler().memory("Transfer fee value", TransferFeeValue);
//                break;
//        }
//    }

    public void scrollUPWeb() {
        Actions actions = new Actions(new DriverManager().getWebDriver());
        actions.sendKeys(Keys.PAGE_UP).build().perform();
    }

    public void scrollDownWeb() {
        Actions actions = new Actions(new DriverManager().getWebDriver());
        actions.sendKeys(Keys.PAGE_DOWN).build().perform();
    }

    public void scrollUPWebHandledClickIE(By ele) {
        for (int i = 0; i < 5; i++) {
            try {
                new DriverManager().getWebDriver().findElement(
                        ele).click();
            } catch (ElementClickInterceptedException ec) {
                Actions actions = new Actions(new DriverManager().getWebDriver());
                actions.sendKeys(Keys.PAGE_UP).build().perform();
            }
        }
    }

    public void scrollUPWebHandledClickIE(WebElement ele) {
        for (int i = 0; i < 5; i++) {
            try {
                ele.click();
            } catch (ElementClickInterceptedException ec) {
                Actions actions = new Actions(new DriverManager().getWebDriver());
                actions.sendKeys(Keys.PAGE_UP).build().perform();
            }
        }
    }

    public void openNewTab(String url) {
        new TestUtils().log().info("javascript exe triggered");
        JavascriptExecutor js = (JavascriptExecutor) new DriverManager().getWebDriver();
        js.executeScript("window.open" + "('" + url + "'" + ",'_blank');");
        List<String> openTabs = (List<String>) new DriverManager().getWebDriver().getWindowHandles();
        new DriverManager().getWebDriver().switchTo().window(openTabs.get(openTabs.size() - 1));

    }

    public void fillUserJSE(WebElement ele, String data) {

        new DriverManager().getDriver().executeScript("arguments[0].setAttribute('value', '" + data + "')", ele);
    }

    public void openNewTab1(String url) {

    }

    public void reloadUrl(String webAppUrl) {
        new TestUtils().log().info("Landing on dashboard");
        new DriverManager().getWebDriver().navigate().to(webAppUrl);
    }

    public static void staticWait(int sec) throws InterruptedException {

        Thread.sleep(sec * 1000);
    }




    public void matchTextAndClickByListElementWeb(String text, List<WebElement> elements) throws ParseException, IOException {

        Actions actions = new Actions(new DriverManager().getWebDriver());

        for (WebElement ele : elements) {
            if (ele.getText().equals(text)) {
                actions.moveToElement(ele).click().build().perform();
                new TestUtils().log().info("match found and clicked");
                break;
            }
        }
    }

    public void isButtonSelected(String button) throws ParseException, IOException {
//        String buttontext=button;
//        System.out.println(buttontext+":button text");
////            By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), button);
//        WebElement ele = new DriverManager().getDriver().findElement(By.xpath(buttontext));
////            driver.findElement(By.xpath(button)).isSelected();
//        if(ele.isSelected())
//        {System.out.println("1D preselected");}

        WebElement ele = new DriverManager().getDriver().findElement(By.xpath("//span[text()='Past Day']"));
        if (ele.isDisplayed()) {
            System.out.println("1D preselected");
        }

    }

    public void validateColour(String price_movement) throws ParseException, IOException {
//        By eleBy = new TestUtils().decideLocator(new PageHandler().getCurrentScreenName(), price_movement);

        String price_movementSign = new DriverManager().getDriver().findElement(By.xpath("(//div[@class='margin-top-m padding-top-s flex-direction-column text-align-left']//child::span)[2]")).getText();
        String color = new DriverManager().getDriver().findElement(By.xpath("(//div[@class='margin-top-m padding-top-s flex-direction-column text-align-left']//child::span)[2]")).getCssValue("color");
        String c = Color.fromString(color).asHex();
        if (price_movementSign.contains("+")) {
            org.testng.Assert.assertEquals("#04624c", c);
        } else if (price_movementSign.contains("-")) {
            org.testng.Assert.assertEquals(c, "#a52603");
        } else {
            org.testng.Assert.assertEquals("#3b7535", c);
        }

        System.out.println("color matched");


    }

    public void CloseBrowser() {
        driver.close();
    }
}


