package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import utilities.TestData;

public class JuiceShopLoginSteps {
    LoginPage loginPage =  new LoginPage();

    @When("kullanici gecerli kullanici bilgileri ile giris yapar")
    public void kullanici_gecerli_kullanici_bilgileri_ile_giris_yapar() {
        String email = TestData.getProperty("admin_email");
        String password = TestData.getProperty("admin_password");
        loginPage.bilgileriDoldurVeGirisYap(email, password);
    }

    @When("kullanici email alanina {string} ve sifre alanina {string} yazar")
    public void kullanici_email_alanina_ve_sifre_alanina_yazar(String email, String password) {
        loginPage.bilgileriDoldurVeGirisYap(email, password);
    }
    @Then("kullanici basariyla giris yapildigini dogrular")
    public void kullanici_basariyla_giris_yapildigini_dogrular() {
        // Account menüsünün açılıp logout butonunun görünür olup olmadığını kontrol ediyoruz
        boolean isLogged = loginPage.cikisButonuGorunurMu();
        Assert.assertTrue(isLogged, "HATA: Başarılı giriş yapılamadı, Log out butonu menüde görünmüyor!");
    }
    @Then("kullanici hata mesajinin {string} icerdigini dogrular")
    public void kullanici_hata_mesajinin_icerdigini_dogrular(String beklenenHata) {
        //ekranda beliren gerçek hata metnini çekiyoruz
        String gercekHata = loginPage.getHataMesajMetni();
        // ekranda beliren hata gerçek mesajın içinde geçiyor mu kıyaslıyoruz
        Assert.assertTrue(gercekHata.contains(beklenenHata),
                "HATA: Beklenen hata mesajı görüntülenemedi! Ekranda görünen: " + gercekHata);
    }
    //Neden Assert.assertEquals yerine contains kullandık
    //metin kutularının başında veya sonunda görünmeyen boşluklar bulunabilir.
    // Tam eşitlik arandığında görünmez bir boşluk testi patlatabilir.
    //containsle mesajın özü doğrulanmış olur
    @Then("kullanici login butonunun tiklanamaz oldugunu dogrular")
    public void kullanici_login_butonunun_tiklanamaz_oldugunu_dogrular() {
        boolean isEnabled = loginPage.isLoginButtonEnabled();
        Assert.assertFalse(isEnabled, "HATA: E-posta ve şifre boş bırakılmasına rağmen Login butonu hala aktif!");
    }

    @When("kullanici email alanini ve sifre alanini bos birakir")
    public void kullanici_email_alanini_ve_sifre_alanini_bos_birakir() {
        loginPage.sadeceBilgileriDoldur("", "");
    }

    @And("kullanici oturumu kapatir")
    public void kullanici_oturumu_kapatir() {
        loginPage.oturumuKapat();
    }

    @Then("kullanici basariyla cikis yapildigini dogrular")
    public void kullanici_basariyla_cikis_yapildigini_dogrular() {
        boolean cikisYapildi = loginPage.cikisBasariliMi();
        Assert.assertTrue(cikisYapildi, "HATA: Oturum kapatilamadi, Account menusunde Login butonu goruntulenemedi!");
    }

}
