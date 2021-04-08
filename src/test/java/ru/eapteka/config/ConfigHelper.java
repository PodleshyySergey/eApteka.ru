package ru.eapteka.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigHelper {

    public static String getWebUrl() {
        return getWebConfig().webUrl();
    }

    public static String getWebBrowser() {
        return getWebConfig().webBrowser();
    }

    public static String getWebBrowserScreenResolution() {
        return getWebConfig().webBrowserScreenResolution();
    }


    public static String getWebRemoteDriver() {
        return "https://" + getWebConfig().webRemoteDriverUser() + ":" +
                getWebConfig().webRemoteDriverPassword() + "@" +
                getWebConfig().webRemoteDriverUrl() + "/wd/hub";
    }

    public static boolean isRemoteWebDriver() {
        return !getWebConfig().webRemoteDriverUrl().equals("");
    }

    public static String getWebVideoStorage() {
        return "https://" + getWebConfig().webVideoStorage() + "/video/";
    }

    private static WebConfig getWebConfig() {
        return ConfigFactory.newInstance().create(WebConfig.class, System.getProperties());
    }

    public static String getEaptekaMail() {
        return getAuthorizationConfig().eaptekaMail();
    }
    public static String getEaptekaPassword() {
        return getAuthorizationConfig().eaptekaPassword();
    }

    private static AuthorizationConfig getAuthorizationConfig() {
        return ConfigFactory.newInstance().create(AuthorizationConfig.class, System.getProperties());
    }
}
