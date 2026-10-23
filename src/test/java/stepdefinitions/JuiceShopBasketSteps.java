package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.BasketPage;

public class JuiceShopBasketSteps {
    BasketPage basketPage = new BasketPage();

    @And("kullanici sepetteki urunun adedini 1 artirir")
    public void kullanici_sepetteki_urunun_adedini_1_artirir() {
        basketPage.urunAdediniArttir();
    }

    @Then("urun adedinin {string} oldugunu dogrular")
    public void urun_adedinin_oldugunu_dogrular(String beklenenAdet) {
        String gercekAdet = basketPage.getUrunAdedi();
        Assert.assertEquals(gercekAdet, beklenenAdet, "HATA: Sepetteki urun adedi beklenenle eslesmiyor!");
    }

    @And("sepetteki toplam tutarin birim fiyata gore dogru hesaplandigini dogrular")
    public void sepetteki_toplam_tutarin_birim_fiyata_gore_dogru_hesaplandigini_dogrular() {
        double birimFiyat = basketPage.getBirimFiyat();
        int adet = Integer.parseInt(basketPage.getUrunAdedi());
        double beklenenToplam = birimFiyat * adet;

        double gercekToplam = basketPage.getToplamTutar();

        // Küsürat yuvarlama farkları için delta (0.01) ile karşılaştırma
        Assert.assertEquals(gercekToplam, beklenenToplam, 0.01,
                "HATA: Toplam sepet tutari yanlis hesaplandi! Beklenen: " + beklenenToplam + " | Gelen: " + gercekToplam);

        // Sonraki testlerin etkilenmemesi için sepeti temiz bırak
        basketPage.urunuSepettenSil();
    }
}