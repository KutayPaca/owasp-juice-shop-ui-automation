package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import utilities.ConfigurationReader;
import utilities.Driver;

public class JuiceShopHomeSteps {

    @Given("kullanici juice shop uygulamasina girer")
    public void kullanici_juice_shop_uygulamasina_girer() {
        //ConfigirationReader sınıfının getProperty metoduna url anahtarını veriyoruz
        //bunuda bir string değişkene atıyoruz
        String url = ConfigurationReader.getProperty("url");
        //Driver.getDriver(): Singleton yapısı sayesinde tarayıcı açılmadıysa Chrome açar
        //.get(url): Seleniuma gidip verdiğimiz adresi yazıp Entera basar
        Driver.getDriver().get(url);
    }

    @Then("sayfa basliginin {string} icerdigini dogrular")
    public void sayfa_basliginin_icerdigini_dogrular(String beklenenKelime) {
        //Driver.getDriver(): ilk adımda açılmış olan Chrome penceresini referans alır.
        //.getTitle(): o an açık olan tarayıcı sekmesinde yazan sayfa başlığını okur ve gercekBaslik değişkenine atar.
        String gercekBaslik = Driver.getDriver().getTitle();

        //Ekranda okunan başlığın içinde feature dosyasından gelen metin geçiyor mu kontrolünü yapar.
        Assert.assertTrue(gercekBaslik.contains(beklenenKelime),
                "HATA: Sayfa başlığı beklenen metni içermiyor! Görünen başlık: " + gercekBaslik);
    }
}
