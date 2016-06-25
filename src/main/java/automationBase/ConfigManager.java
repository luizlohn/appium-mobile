package automationBase;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Mobile Quality Engineer: luizlohn  on 5/25/16.
 */
public class ConfigManager {

    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        InputStream file = ConfigManager.class.getResourceAsStream("/config.properties");
        props.load(file);
        return props;
    }

    public static String getAutomationName() throws IOException {
        return getProp().getProperty("AUTOMATIONNAME");
    }


    public static String getUsuario1() throws IOException {
        return getProp().getProperty("USUARIO1");
    }

    public static String getUsuario2() throws IOException {
        return getProp().getProperty("USUARIO2");
    }

    public static String getSenha() throws IOException {
        return getProp().getProperty("SENHA");
    }

    public static String getQa() throws IOException {
        return getProp().getProperty("QA");
    }

    public static String getOS() throws IOException {
        return getProp().getProperty("OS");
    }

    public static String getIp() throws IOException {
        return getProp().getProperty("IP");
    }

    public static String getPort() throws IOException {
        return getProp().getProperty("PORT");
    }

    public static String getDeviceNameAndroid() throws IOException {
        return getProp().getProperty("DEVICENAMEANDROID");
    }

    public static String getDeviceNameIos() throws IOException {
        return getProp().getProperty("DEVICENAMEIOS");
    }

    public static String getUrlFull() throws IOException {
        return "http://" + getIp() + ":" + getPort() + "/wd/hub";

    }

    public static String getApkName() throws IOException {
        return getProp().getProperty("APKNAME");
    }

    public static String getIosVersion() throws IOException {
        return getProp().getProperty("IOSVERSION");
    }
    public static String getAndroidVersion() throws IOException {
        return getProp().getProperty("ANDROIDVERSION");
    }

    public static String getTextoTeste() throws IOException {
        return getProp().getProperty("TEXTOENVIO");
    }

    public static String getAppName() throws IOException {
        return getProp().getProperty("APPNAME");
    }

}
