package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;

public class JuiceShopLoginSteps {
    LoginPage loginPage =  new LoginPage();

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
}
