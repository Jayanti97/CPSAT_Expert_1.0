package org.example;



        import org.junit.Test;
        import org.openqa.selenium.JavascriptExecutor;
        import org.openqa.selenium.WebElement;

        import java.io.IOException;

public class JSExecutor {


    public static ThreadLocal<JavascriptExecutor> js = new ThreadLocal<>();

    public JSExecutor() {
        try {
            if (new PropertyManager().getProps().getProperty("AppPlatform").equals("Web")){
                js.set((JavascriptExecutor) new DriverManager().getWebDriver());
            }
            else
            {
                js.set((JavascriptExecutor)new DriverManager().getDriver());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void jsExeClickAction(WebElement ele) {

        new TestUtils().log().info("javascript exe triggered");
        js.get().executeScript("arguments[0].click();", ele);

        new TestUtils().log().info("click action performed using javascript exe");

    }

    @Test
    public void cheeck()
    {
        String s="okeefe";
        s=s.replace("'","");
        new TestUtils().log().info(s);
    }

    public void jsActionRefreshBrowserWindow() {
        new TestUtils().log().info("javascript exe triggered");
        js.get().executeScript("history.go(0)");
        new TestUtils().log().info("window refresh action performed using javascript exe");
    }

    public void jsActionVerticalScroll(int verticalPxSize) {
        new TestUtils().log().info("javascript exe triggered");
        js.get().executeScript("window.scrollBy(0," + verticalPxSize + ")");
        new TestUtils().log().info("scroll to some px action performed using javascript exe");
    }

    public void jsActionVerticalScrollToEnd() {
        new TestUtils().log().info("javascript exe triggered");
        js.get().executeScript("window.scrollBy(0,document.body.scrollHeight)");
        new TestUtils().log().info("scroll till end of page action performed using javascript exe");
    }

    public void jsExeHighLightMe(WebElement ele) {

        new TestUtils().log().info("javascript exe triggered");
        js.get().executeScript("arguments[0].setAttribute('style','border:#00cccc; border-width:3px; border-style:solid;');", ele);
        new TestUtils().log().info("element highlight action performed using javascript exe");

    }





    public void jsScrollBy(String Direction) {

        switch (Direction) {
            case "down":

                //js.executeScript("window.scrollBy(0, 1348);");
                //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                //js.executeAsyncScript("window.scrollBy(0,document.body.scrollHeight)");
                break;
            case "up":
//...
                break;
            case "right":
//...
                break;
        }
    }


    public void scrollToElement(WebElement ele)
    {
        js.get().executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'center'});", ele);
    }

    public void scrollToElementMobile(WebElement ele)
    {
        js.get().executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'center'});", ele);
    }




    //js.executeScript("document.getElementById('id value here').click();");

}

