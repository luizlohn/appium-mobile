package appiumDriver;

import automationBase.ConfigManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


/**
 * Created by Mobile Quality Engineer: luizlohn  on 6/3/16.
 */
public class DriverFactory {
    private static AppiumDriver instance;

    private static void initializeAndroid() {

        try {
            File app = new File(ConfigManager.getApkName());

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, ConfigManager.getAutomationName());
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.VERSION, ConfigManager.getAndroidVersion());
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigManager.getDeviceNameAndroid());
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            instance = new AndroidDriver(new URL(ConfigManager.getUrlFull()), capabilities);
            instance.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initializeIos() {

        try {

            File app = new File(ConfigManager.getAppName());

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, ConfigManager.getAutomationName());
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ios");
            capabilities.setCapability(MobileCapabilityType.VERSION, ConfigManager.getIosVersion());
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigManager.getDeviceNameIos());
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            instance = new IOSDriver(new URL(ConfigManager.getUrlFull()), capabilities);
            instance.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initialize() throws IOException {
        if (ConfigManager.getOS().equalsIgnoreCase("Android")) {
            initializeAndroid();
        }
        if (ConfigManager.getOS().equalsIgnoreCase("ios")) {
            initializeIos();
        }
    }

    public static AppiumDriver getInstance() throws IOException {
        if (instance == null) {
            initialize();
        }
        return instance;

    }
}
