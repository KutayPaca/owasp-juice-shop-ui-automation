package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.Driver;

public class Hooks {

    //Her bir senaryo başlamadan önce çalışır
    @Before
    public void setUp() {
        //şimdilik tarayıcı Driver.getDriver() çağırıldığında ayağa kalkıyo
    }

    @After
    public void tearDown(Scenario scenario) {
        //test bittiğinde tarayıcıyı kapatıyoruz
        Driver.closeDriver();
    }
}
