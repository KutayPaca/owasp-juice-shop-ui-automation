package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        //feature dosyalarının nerede olduğunu gösteriyoruz
        features = "src/test/resources/features" ,
        //bu featureların java kodlarının hangi pakette olduklarını belirtiyoruz
        glue = "stepdefinitions",
        //feature dosyasındaki adımların java kodları yazımışmı diye bakar
        //eksik adım varsa hazır java metotlarını konsolda üretir
        dryRun = false
)

public class TestRunner extends AbstractTestNGCucumberTests {
    //sınıfın içi boş çünkü yukarıdakileri testng ye yönlendirir
}
