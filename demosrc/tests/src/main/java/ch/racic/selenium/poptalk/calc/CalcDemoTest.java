/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 * This code has been developed as part of talk on selendroid by Michel Racic (http://rac.su/+) from GDG Zürich (http://www.gdgzh.ch).
 */

package ch.racic.selenium.poptalk.calc;

import ch.racic.selenium.poptalk.calc.utils.POConfig;
import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.simpleHTTPServer.SimpleHTTPServer;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class CalcDemoTest {
    private WebDriver driver;
    private POConfig testconfig;
    private ICalcPage calc;
    private SelendroidLauncher selendroidServer;
    private SimpleHTTPServer webServer;

    @BeforeSuite
    public void suiteSetUp() throws Exception {
        testconfig = new POConfig();

        // Initialize driver we want to use
        String browserToUse = testconfig.getProperty("browserToUse");

        // Initialize AUT
        if (browserToUse.equals("SELENDROID")) {
            if (selendroidServer != null) {
                selendroidServer.stopSelendroid();
            }
            SelendroidConfiguration config = new SelendroidConfiguration();
            config.addSupportedApp(CalcDemoTest.class.getResource("/DemoCalc.apk").getPath());
            selendroidServer = new SelendroidLauncher(config);
            selendroidServer.lauchSelendroid();
        } else {
            webServer = new SimpleHTTPServer(8000, new File("src/main/resources/htmlcalcdemo"));
            webServer.start();
            // load the calculator page
            driver.navigate().to(testconfig.getProperty("targetURL"));
            ((JavascriptExecutor) driver).executeScript("window.focus();");
        }

        if (browserToUse.equals("HTMLUNIT")) {
            driver = new HtmlUnitDriver(true);
        } else if (browserToUse.equals("FIREFOX")) {
            FirefoxProfile ffprofile = new FirefoxProfile();
            ffprofile.setPreference("intl.accept_languages", "en-us,en");
            driver = new FirefoxDriver(ffprofile);
        } else if (browserToUse.equals("CHROME")) {
            System.setProperty("webdriver.chrome.driver", CalcDemoTest.class.getResource("/chromedriver").getPath());
            driver = new ChromeDriver();
        } else if (browserToUse.equals("SAFARI")) {
            driver = new SafariDriver();
        } else if (browserToUse.equals("ANDROID")) {
            driver = new AndroidDriver();
        } else if (browserToUse.equals("SELENDROID")) {
            SelendroidCapabilities caps = new SelendroidCapabilities("ch.racic.selendroid.democalc:1.0");
            WebDriver driver = new SelendroidDriver(caps);
        }


        // initialize our page object, we do it here as its a single page application
        calc = getPageObject(ICalcPage.class);
    }

    @AfterSuite
    public void suiteTearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
        if (webServer != null) {
            webServer.stop();
        }

    }

    @Test(testName = "Invoke", groups = {"init"})
    public void targetServerReady() {
        Assert.assertTrue("Application initialized?", calc.verifyInitialized());
    }

    @Test(testName = "Reset", groups = {"init"}, dependsOnMethods = {"targetServerReady"})
    public void reset() {
        Assert.assertTrue("Starts with 0?", calc.displayed() == 0);
        Assert.assertTrue("Display changed to 5?", calc.n5().displayed() == 5);
        Assert.assertTrue("Display resets to 0?", calc.clear().displayed() == 0);
    }

    @Test(testName = "Add numbers", groups = {"Operations"}, dependsOnGroups = {"init"})
    public void additions() {
        Assert.assertTrue("1+1=2", calc.clear().n1().add().n1().equal() == 2);
        Assert.assertTrue("4+5=9", calc.clear().n4().add().n5().equal() == 9);
        Assert.assertTrue("20+3=23", calc.clear().n2().n0().add().n3().equal() == 23);
        Assert.assertTrue("501+345=846", calc.clear().n5().n0().n1().add().n3().n4().n5().equal() == 846);
    }

    @Test(testName = "Substract numbers", groups = {"Operations"}, dependsOnGroups = {"init"})
    public void substractions() {
        Assert.assertTrue("1-1=0", calc.clear().n1().substract().n1().equal() == 0);
        Assert.assertTrue("4-5=-1", calc.clear().n4().substract().n5().equal() == -1);
        Assert.assertTrue("20-3=17", calc.clear().n2().n0().substract().n3().equal() == 17);
        Assert.assertTrue("501-345=156", calc.clear().n5().n0().n1().substract().n3().n4().n5().equal() == 156);
    }

    @Test(testName = "Multiply numbers", groups = {"Operations"}, dependsOnGroups = {"init"})
    public void multiplications() {
        Assert.assertTrue("1*1=1", calc.clear().n1().multiply().n1().equal() == 1);
        Assert.assertTrue("4*5=20", calc.clear().n4().multiply().n5().equal() == 20);
        Assert.assertTrue("20*3=60", calc.clear().n2().n0().multiply().n3().equal() == 60);
        Assert.assertTrue("501*345=172845", calc.clear().n5().n0().n1().multiply().n3().n4().n5().equal() == 172845);
    }

    @Test(testName = "Divide numbers", groups = {"Operations"}, dependsOnGroups = {"init"})
    public void divisions() {
        Assert.assertTrue("1/1=1", calc.clear().n1().divide().n1().equal() == 1);
        Assert.assertTrue("4/5=0.8", calc.clear().n4().divide().n5().equal() == 0.8);
        Assert.assertTrue("20/3=6.666666666666667", calc.clear().n2().n0().divide().n3().equal() == 6.666666666666667);
        Assert.assertTrue("501/345=1.4521739130434783", calc.clear().n5().n0().n1().divide().n3().n4().n5().equal() == 1.4521739130434783);
    }

    /**
     * helper methods *
     */
    public <T> T getPageObject(Class<T> pageInterfaceToProxy) {
        T page = null;
        try {
            Class<T> pageClassToProxy = testconfig.getPageObjectImplementation(driver, pageInterfaceToProxy);
            page = PageFactory.initElements(driver, pageClassToProxy);
        } catch (Exception e) {
            if (e.getCause() != null && e.getCause() instanceof InvocationTargetException) {
                InvocationTargetException exc = (InvocationTargetException) e.getCause();
                if (exc.getTargetException() != null) {
                    Assert.fail(exc.getTargetException().getMessage());
                } else {
                    Assert.fail(exc.getMessage());
                }
            }
            Assert.fail(e.getMessage());
        }
        return page;
    }


}
