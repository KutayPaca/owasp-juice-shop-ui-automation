package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;

public class Hooks {

    @Before
    public void setUp() {
        // Test öncesi hazırlık
    }

    @After
    public void tearDown(Scenario scenario) {
        // Eğer test başarısız olduysa ekran görüntüsü al ve rapora ekle
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        // Test tamamlandıktan sonra tarayıcıyı kapat
        Driver.closeDriver();
    }
}