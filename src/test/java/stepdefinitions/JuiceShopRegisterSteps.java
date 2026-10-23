package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import pages.RegisterPage;
import utilities.Driver;
import utilities.ElementHelper;

public class JuiceShopRegisterSteps {
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();

    @When("kullanici kayit ol baglantisina tiklar")
    public void kullanici_kayit_ol_bagltisina_tiklar() {
        loginPage.kayitOlSayfasinaGit();
    }

    @And("kullanici gecerli kayit bilgilerini doldurup onaylar")
    public void kullanici_gecerli_kayit_bilgilerini_doldurup_onaylar() {
        // Her çalıştığında farklı olması için milisaniye ile dinamik e-posta üretiyoruz
        String dinamikEmail = "otomasyon_" + System.currentTimeMillis() + "@test.com";
        String sifre = "GucluSifre.123";
        String guvenlikCevabi = "Ankara";

        registerPage.kayitFormunuDoldur(dinamikEmail, sifre, guvenlikCevabi);
    }

    @When("kullanici email alanina {string} sifreye {string} ve sifre tekrarina {string} yazar")
    public void kullanici_email_alanina_sifreye_ve_sifre_tekrarina_yazar(String email, String sifre, String repeatSifre) {
        registerPage.sifreleriUyusmayanBilgileriDoldur(email, sifre, repeatSifre);
    }

    @Then("kullanici kayit ekraninda {string} hata mesajini gorur")
    public void kullanici_kayit_ekraninda_hata_mesajini_gorur(String beklenenHata) {
        String gercekHata = registerPage.getPasswordMismatchErrorMessage();
        Assert.assertTrue(gercekHata.contains(beklenenHata),
                "HATA: Beklenen hata mesajı görüntülenemedi! Görünen: " + gercekHata);
    }

    @Then("kullanici kaydin basariyla tamamlandigini dogrular")
    public void kullanici_kaydin_basariyla_tamamlandigini_dogrular() {
        //Önce başarılı kayıt bildiriminin ekranda belirmesini bekle
        boolean bildirimGorundu = registerPage.basariBildirimiGorunduMu();
        Assert.assertTrue(bildirimGorundu, "HATA: Başarılı kayıt bildirim mesajı görüntülenemedi!");

        // 2. URL login sayfasına dönmesini bekle
        ElementHelper elementHelper = new ElementHelper();
        boolean urlDegisti = elementHelper.waitForUrlContains("login");

        String currentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(urlDegisti,
                "HATA: Kayıt sonrası Login sayfasına yönlendirilmedi! Mevcut URL: " + currentUrl);
    }
}
