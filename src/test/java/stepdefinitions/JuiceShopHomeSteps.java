package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import utilities.ConfigurationReader;
import utilities.Driver;

public class JuiceShopHomeSteps {

    HomePage homePage =  new HomePage();

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

    @When("kullanici acilis popuplarini kapatir")
    public void kullanici_acilis_popuplarini_kapatir() {
        //bütün mantığı HomePage içerisine kuruldu buraya teker teker yazılmadı
        homePage.ilkAcilisEngelleriniKapat();
    }

    @And("kullanici Account menusunden Login sayfasina gider")
    public void kullanici_account_menusunden_login_sayfasina_gider(){
        homePage.loginSayfasinaGit();
    }

    @Then("kullanici Login sayfasinda oldugunu dogrular")
    public void kullanici_login_sayfasinda_oldugunu_dogrular(){
        // tarayıcının o anki url sini gönderiyoruz
        String currentUrl = Driver.getDriver().getCurrentUrl();
        // url içerisinde login kelimesi geçiyor mu logine yönlendirildik mi diye doğruluyoruz
        Assert.assertTrue(currentUrl.contains("login"),"Login sayfasına gidilemedi! Mevcut URL: " + currentUrl);
    }
}
