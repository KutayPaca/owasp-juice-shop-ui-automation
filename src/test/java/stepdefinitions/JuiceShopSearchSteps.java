package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;

import java.util.List;

public class JuiceShopSearchSteps {

    HomePage homePage = new HomePage();

    @When("kullanici arama kutusuna {string} yazar ve aratir")
    public void kullanici_arama_kutusuna_yazar_ve_aratir(String arananKelime) {
        homePage.urunAra(arananKelime);
    }

    @Then("listelenen tum urunlerin basliginda {string} kelimesi gectigini dogrular")
    public void listelenen_tum_urunlerin_basliginda_kelimesi_gectigini_dogrular(String beklenenKelime) {
        // 1. Ekrandaki tüm ürün adlarını liste olarak alıyoruz
        List<String> gelenUrunler = homePage.getAramaSonuclariBasliklari();

        // 2. En az bir ürün geldiğinden emin oluyoruz (Boş liste gelmesin)
        Assert.assertFalse(gelenUrunler.isEmpty(), "HATA: Arama sonucunda hiçbir ürün listelenmedi!");

        // 3. Her bir ürünün adında aranan kelimenin geçtiğini döngüyle kontrol ediyoruz
        for (String urunAdi : gelenUrunler) {
            Assert.assertTrue(urunAdi.contains(beklenenKelime.toLowerCase()),
                    "HATA: Ürün adında '" + beklenenKelime + "' kelimesi bulunamadı! Gelen ürün adı: " + urunAdi);
        }
    }

    @Then("arama sonucunda hicbir urunun listelenmedigini dogrular")
    public void arama_sonucunda_hicbir_urunun_listelenmedigini_dogrular() {
        boolean urunYok = homePage.hicUrunListelenmediMi();
        Assert.assertTrue(urunYok, "HATA: Gecersiz arama yapilmasina ragmen ekranda urunler listelendi!");
    }
}
