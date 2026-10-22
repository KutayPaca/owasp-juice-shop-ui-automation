package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CheckoutPage;

public class JuiceShopCheckoutSteps {
    CheckoutPage checkoutPage  = new CheckoutPage();

    @When("kullanici ilk urunu sepete ekler")
    public void kullanici_ilk_urunu_sepete_ekler() {
        checkoutPage.ilkUrunuSepeteEkle();
    }
    @And("kullanici sepet sayfasina gider")
    public void kullanici_sepet_sayfasina_gider() {
        checkoutPage.sepeteGit();
    }
    @And("kullanici sepeti onaylayıp checkout adimina gecer")
    public void kullanici_sepeti_onaylayip_checkout_adimina_gecer() {
        checkoutPage.sepetteCheckoutYap();
    }
    @And("kullanici kayitli ilk adresi secer ve devam eder")
    public void kullanici_kayitli_ilk_adresi_secer_ve_devam_eder() {
        checkoutPage.ilkAdresiSecVeDevamEt();
    }
    @And("kullanici teslimat hizini secer ve devam eder")
    public void kullanici_teslimat_hizini_secer_ve_devam_eder() {
        checkoutPage.teslimatHiziniSecVeDevamEt();
    }
    @And("kullanici kayitli odeme yontemini secer ve devam eder")
    public void kullanici_kayitli_odeme_yontemini_secer_ve_devam_eder() {
        checkoutPage.odemeYonteminiSecVeDevamEt();
    }
    @And("kullanici siparisi onaylar")
    public void kullanici_siparisi_onaylar() {
        checkoutPage.siparisiOnayla();
    }
    @Then("kullanici siparisin basariyla tamamlandigini dogrular")
    public void kullanici_siparisin_basariyla_tamamlandigini_dogrular() {
        boolean siparisTamamlandi = checkoutPage.siparisBasariliMi();
        Assert.assertTrue(siparisTamamlandi, "HATA: Sipariş tamamlanma onay başlığı görüntülenemedi!");
    }
}
