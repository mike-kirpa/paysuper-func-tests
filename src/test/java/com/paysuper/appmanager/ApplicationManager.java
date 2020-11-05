package com.paysuper.appmanager;

import com.browserstack.local.Local;
import com.paysuper.appmanager.helpers.File;
import com.paysuper.appmanager.helpers.GetProperties;
import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.helpers.RestAPI;
import com.paysuper.tests.TestBase;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.paysuper.appmanager.helpers.File.getResource;

public class ApplicationManager {
    public WebDriver driver;
    private Local l;
    public GetProperties getProperties;
    public RestAPI restAPI;
    public Locators locators;
    private static String zone;

    public static String getZone(){
        return zone;
    }

    public void stop() throws InterruptedException {
        driver.quit();
        if (l != null) {
            l.stop();
        }
    }

    //Overload
    public void init(String config_file, String environment, String zone) throws Exception {
        init(config_file, environment, zone, true);
    }

    public void init(String config_file, String environment, String zone, boolean localrun) throws Exception {
        ApplicationManager.zone = zone;
        if (!localrun) {
            JSONParser parser = new JSONParser();
            JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/conf/" + config_file));
            JSONObject envs = (JSONObject) config.get("environments");

            DesiredCapabilities capabilities = new DesiredCapabilities();

            Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
            Iterator it = envCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }

            Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
            it = commonCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (capabilities.getCapability(pair.getKey().toString()) == null) {
                    capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
                }
            }

            String username = System.getenv("BROWSERSTACK_USERNAME");
            if (username == null) {
                username = (String) config.get("user");
            }

            String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
            if (accessKey == null) {
                accessKey = (String) config.get("key");
            }

            if (capabilities.getCapability("browserstack.local") != null
                    && capabilities.getCapability("browserstack.local") == "true") {
                l = new Local();
                Map<String, String> options = new HashMap<String, String>();
                options.put("key", accessKey);
                l.start(options);
            }
            capabilities.setCapability("resolution", "1920x1080");
            capabilities.setCapability("browserstack.geoLocation", "DE");
            driver = new RemoteWebDriver(
                    new URL("http://" + username + ":" + accessKey + "@" + config.get("server") + "/wd/hub"), capabilities);
        }

        if (localrun) {
            System.setProperty("webdriver.chrome.driver", getResource("/webdrivers/chromedriver.exe"));
            driver = new ChromeDriver();
        }

            driver.manage().window().maximize();
            getProperties = new GetProperties(zone);
            restAPI = new RestAPI();
            locators = new Locators(zone);


    }


}
