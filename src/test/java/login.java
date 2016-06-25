import appiumDriver.AppiumServer;
import appiumDriver.DriverFactory;
import automationBase.ConfigManager;
import io.appium.java_client.AppiumDriver;
import org.apache.tools.ant.taskdefs.WaitFor;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.ScreenOrientation;

import java.io.IOException;

/**
 * Created by luizlohn on 25/06/2016.
 */
public class login {


    static AppiumDriver driver;

    /**
     * Before e BeforeClass
     **/
    @BeforeClass
    public static void setUpClass() throws Exception {
        AppiumServer.startAppiumServer();
        driver = DriverFagctory.getInstance();
        Thread.sleep(7000);

    }

    @Before
    public void rotation() throws IOException, InterruptedException {
        if(ConfigManager.getOS().equalsIgnoreCase("android")) {
            driver.rotate(ScreenOrientation.PORTRAIT);
        }

    }

    /**
     * After e AfterClass
     **/
    @AfterClass
    public static void setDownAppium() throws Exception {
        driver.quit();
        AppiumServer.stopAppiumServer();
    }
}
