package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;
import utilities.ElementHelper;

import java.time.Duration;

public class CheckoutPage {
    private final ElementHelper elementHelper;
    private final WebDriverWait wait;

    public CheckoutPage() {
        this.elementHelper = new ElementHelper();
        // wait nesnesi burada tanımlanıp başlatılıyo
        this.wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
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
    // Senkronizasyon locator'ları
    private final By snackBarCloseButton = By.cssSelector(".mat-simple-snack-bar-content + div button, .mat-mdc-snack-bar-actions button");
    private final By basketCounterBadge = By.cssSelector(".fa-layers-counter");

    //sayfadaki ilk ürünün add to basket butonuna tıklar
    //scrollToElement ekranda görünür olmasını clickWithJs ise angular bannerları engeller

    public void ilkUrunuSepeteEkle() {
        //login sonrası ana sayfa ürün listesinin yüklenmesini garantiye al
        wait.until(ExpectedConditions.urlContains("search"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddToBasketButton));

        //Ürünü sepete ekle
        elementHelper.scrollToElement(firstProductAddToBasketButton);
        elementHelper.clickWithJS(firstProductAddToBasketButton);

        //Sepet ikonu üzerindeki sayacın 0dan farklı olmasını bekle
        wait.until(driver -> {
            try {
                String countText = driver.findElement(basketCounterBadge).getText().trim();
                return !countText.isEmpty() && !countText.equals("0");
            } catch (Exception e) {
                return false;
            }
        });

        //Açılan Placed into basket bildirimini kapat
        try {
            if (!Driver.getDriver().findElements(snackBarCloseButton).isEmpty()) {
                elementHelper.clickWithJS(snackBarCloseButton);
            }
        } catch (Exception ignored) {}
    }

    //sepet sayfasına geçiş yapar
    public void sepeteGit(){
        // JS click yerine normal click kullanıyoruz ki Angular Router state'i düzgün taşısın
        elementHelper.click(yourBasketButton);

        // Eğer Angular asenkron gecikmeden dolayı sepeti boş (Total Price: 0) yüklerse
        // 3 saniye içinde satır gelmediğinde sayfayı 1 kez yenile (Refresh)
        try {
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(basketProductRow));
        } catch (Exception e) {
            Driver.getDriver().navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(basketProductRow));
        }
    }
    //onaylama ekranı
    public void sepetteCheckoutYap(){
        // Buton gerçekten tıklanabilir olana kadar bekle
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        // clickWithJS yerine normal click dene olmazsa JS kullan
        try {
            elementHelper.click(checkoutButton);
        } catch (Exception e) {
            elementHelper.clickWithJS(checkoutButton);
        }
        // Adres URL geçildiğine bakmadan sonraki adıma geçme
        wait.until(ExpectedConditions.urlContains("address"));
    }
    public void ilkAdresiSecVeDevamEt() {
        wait.until(ExpectedConditions.elementToBeClickable(firstAddressRadioButton));
        elementHelper.clickWithJS(firstAddressRadioButton);
        wait.until(ExpectedConditions.elementToBeClickable(addressContinueButton));
        elementHelper.clickWithJS(addressContinueButton);
    }

    public void teslimatHiziniSecVeDevamEt() {
        wait.until(ExpectedConditions.elementToBeClickable(standardDeliveryOption));
        elementHelper.clickWithJS(standardDeliveryOption);
        wait.until(ExpectedConditions.elementToBeClickable(deliveryContinueButton));
        elementHelper.clickWithJS(deliveryContinueButton);
    }

    public void odemeYonteminiSecVeDevamEt() {
        wait.until(ExpectedConditions.elementToBeClickable(firstPaymentMethodRadio));
        elementHelper.clickWithJS(firstPaymentMethodRadio);
        wait.until(ExpectedConditions.elementToBeClickable(paymentContinueButton));
        elementHelper.clickWithJS(paymentContinueButton);
    }

    public void siparisiOnayla() {
        wait.until(ExpectedConditions.elementToBeClickable(completeOrderButton));
        elementHelper.clickWithJS(completeOrderButton);
    }

    public boolean siparisBasariliMi() {
        return elementHelper.isDisplayed(orderSuccessHeader);
    }
}