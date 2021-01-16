package br.com.automacao.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static WebDriver driver;

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (Propriedades.BROWSER) {
                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;

                case CHROME:
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("disable-infobars");
                    options.addArguments("--disable-print-preview");
                    options.addArguments("--lang=pt-br");

                    // ChromeDriver 87.0.4280.88 (Supports Chrome version 87)
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");

                    // Funciona a partir da versão 60 do chrome
                    if (Propriedades.CHROME_HEADLESS) {
                        options.addArguments("--headless");
                    }

                    driver = new ChromeDriver(options);
                    break;

                default:
                    System.out.println("Driver nao encontrado!");
                    break;
            }
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver killDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        return driver;
    }
}