package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementHelper;

public class CheckoutPage {
    private final ElementHelper elementHelper;

    public CheckoutPage() {
        this.elementHelper = new ElementHelper();
    }

    //locator
    //ilk ürünün add to basket butonu
    private final By firstProductAddToBasketButton = By.cssSelector("button[aria-label='Add to Basket']");
    //sepet butonu
    private final By yourBasketButton = By.cssSelector("button[routerlink='/basket']");
    //sepet sayfası
    private final By checkoutButton = By.id("checkoutButton");
    //adres seçimi
    private final By firstAddressRadioButton = By.cssSelector("mat-table mat-row mat-radio-button, mat-table mat-row .mat-mdc-radio-button");
    private final By addressContinueButton = By.cssSelector("button[aria-label='Proceed to payment selection']");
    //teslimat yöntimi
    private final By standardDeliveryOption = By.cssSelector("mat-table mat-row:nth-child(2) mat-radio-button, mat-table mat-row:first-child mat-radio-button");
    private final By deliveryContinueButton = By.cssSelector("button[aria-label='Proceed to delivery method selection']");
    //ödeme yöntemi
    private final By firstPaymentMethodRadio = By.cssSelector("mat-table mat-row mat-radio-button");
    private final By paymentContinueButton = By.cssSelector("button[aria-label='Proceed to review']");
    //sipariş özeti
    private final By completeOrderButton = By.id("checkoutButton");
    //sipariş onayı
    private final By orderSuccessHeader = By.cssSelector("h1.confirmation");

    //senkronizasyon için eklenen locatorlar
    private final By snackBarNotification = By.cssSelector(".mat-mdc-snack-bar-container, .mat-simple-snack-bar-content");
    private final By basketProductRow = By.cssSelector("mat-row, mat-table mat-row");

    //sayfadaki ilk ürünün add to basket butonuna tıklar
    //scrollToElement ekranda görünür olmasını clickWithJs ise angular bannerları engeller
    public void ilkUrunuSepeteEkle(){
        elementHelper.scrollToElement(firstProductAddToBasketButton);
        elementHelper.clickWithJS(firstProductAddToBasketButton);
        elementHelper.isDisplayed(snackBarNotification);
    }
    //sepet sayfasına geçiş yapar
    public void sepeteGit(){
        elementHelper.scrollToElement(yourBasketButton);
        elementHelper.clickWithJS(yourBasketButton);
        elementHelper.isDisplayed(basketProductRow);
    }
    //onaylama ekranı
    public void sepetteCheckoutYap(){
        elementHelper.scrollToElement(checkoutButton);
        elementHelper.clickWithJS(checkoutButton);
        elementHelper.waitForUrlContains("address");
    }
    //kayıtlı adresler tablosundaki ilk adresi seçer, kargo adımına devam eder
    public void ilkAdresiSecVeDevamEt(){
        elementHelper.scrollToElement(firstAddressRadioButton);
        elementHelper.clickWithJS(firstAddressRadioButton);
        elementHelper.clickWithJS(addressContinueButton);
    }
    //kargo seçenekleri listesinden teslimat seçeneğini işaretler
    public void teslimatHiziniSecVeDevamEt(){
        elementHelper.scrollToElement(standardDeliveryOption);
        elementHelper.clickWithJS(standardDeliveryOption);
        elementHelper.clickWithJS(deliveryContinueButton);
    }
    //kayıtlı ödeme yöntemlerinden ilkini seçer ve devam eder
    public void odemeYonteminiSecVeDevamEt(){
        elementHelper.scrollToElement(firstPaymentMethodRadio);
        elementHelper.clickWithJS(firstPaymentMethodRadio);
        elementHelper.clickWithJS(paymentContinueButton);
    }
    //siparişi onaylar
    public void siparisiOnayla(){
        elementHelper.scrollToElement(completeOrderButton);
        elementHelper.clickWithJS(completeOrderButton);
    }
    //siparişin başarılı oluğ olmadığını headera göre kontrol eder
    public boolean siparisBasariliMi() {
        return elementHelper.isDisplayed(orderSuccessHeader);
    }
}