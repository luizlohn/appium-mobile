package appiumDriver;

import automationBase.ConfigManager;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

import java.io.IOException;

import static com.sun.jna.Platform.isMac;
import static com.sun.jna.Platform.isWindows;

/**
 * Created by Mobile Quality Engineer: luizlohn  on 5/25/16.
 */
public class AppiumServer {

    private static String OS = System.getProperty("os.name").toLowerCase();

    private static void startAppiumServerOnMac() throws IOException, InterruptedException {

        CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");
        command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/build/lib/main.js", false);
        command.addArgument("--address", false);
        command.addArgument(ConfigManager.getIp());
        command.addArgument("--port", false);
        command.addArgument(ConfigManager.getPort());
        command.addArgument("--session-override", true);
        command.addArgument("--full-reset", false);

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        executor.execute(command, resultHandler);

        Thread.sleep(10000);
        System.out.println("Serviço do Appium no MAC Iniciado");
    }

    private static void stopAppiumServerOnMac() throws IOException {
        String[] command = {"/usr/bin/killall", "-KILL", "node"};
        Runtime.getRuntime().exec(command);
        System.out.println("Appium server stop");
    }

    private static void startServerOnWindows() throws InterruptedException, IOException {
        CommandLine command = new CommandLine("cmd");
        command.addArgument("/c");
        command.addArgument("C:/Appium/node.exe");
        command.addArgument("C:/Appium/node_modules/appium/bin/appium.js");
        command.addArgument("--address");
        command.addArgument(ConfigManager.getIp());
        command.addArgument("--port");
        command.addArgument(ConfigManager.getPort());
        command.addArgument("--no-reset");
        command.addArgument("--log");
        command.addArgument("D:/appiumLogs.txt");

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);

        try {
            executor.execute(command, resultHandler);
            Thread.sleep(5000);
            System.out.println("Serviço do Appium no Windows Iniciado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void stopServerOnWindows() {

        CommandLine command = new CommandLine("cmd");
        command.addArgument("/c");
        command.addArgument("taskkill");
        command.addArgument("/F");
        command.addArgument("/IM");
        command.addArgument("node.exe");

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);

        try {
            executor.execute(command, resultHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startAppiumServer() throws Exception {

        System.out.println("---- Iniciando serviço do Appium ----");
        System.out.println(OS);

        if (isWindows()) {
            startServerOnWindows();
            System.out.println("Appium sendo iniciado no Windows");
            Thread.sleep(6000);
        } else if (isMac()) {
            startAppiumServerOnMac();
            System.out.println("Appium sendo iniciado no MAC");
            Thread.sleep(6000);
        } else {
            throw new IOException("Ocorreu algum problema, não foi possível reconhecer seu sistema operacional");
        }
    }

    public static void stopAppiumServer() throws IOException {

        if (isWindows()) {
            stopServerOnWindows();
            System.out.println("Finalizado Server Appium no Windows");
        } else if (isMac()) {
            stopAppiumServerOnMac();
            System.out.println("Finalizado Server Appium no Mac");
        } else {
            System.out.println("Não roda");
        }
    }
}





