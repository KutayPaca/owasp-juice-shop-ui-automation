# 🧃 OWASP Juice Shop - UI Test Automation Framework

![Java](https://img.shields.io/badge/Java-21%20LTS-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.x-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![Cucumber](https://img.shields.io/badge/Cucumber-7.20.1-23D96C?style=for-the-badge&logo=cucumber&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-Framework-FF7F00?style=for-the-badge)
![Status](https://img.shields.io/badge/Tests-14%20Passed%20(100%25)-success?style=for-the-badge)

Bu proje, modern ve Angular tabanlı **OWASP Juice Shop** e-ticaret uygulaması için geliştirilmiş **BDD UI Test Otomasyon** projesidir. 

Proje, **Page Object Model (POM)** ve **Singleton Design Pattern** mimarisiyle tasarlanmıştır.

---

## Kullanılan Teknolojiler

| Kategori | Teknoloji / Kütüphane | 
| :--- | :--- | 
| **Programlama Dili** | Java 21 (OpenJDK LTS) |
| **Otomasyon Aracı** | Selenium WebDriver |
| **BDD Çatısı** | Cucumber (v7.20.1) | 
| **Test Koşucu & Doğrulama** | TestNG |
| **Tasarım Desenleri** | Page Object Model (POM) & Singleton |
| **Raporlama** | Cucumber HTML & JSON Reports |

---

## Proje Klasör Yapısı

```text
owasp-juice-shop/
├── src/
│   └── test/
│       ├── java/
│       │   ├── pages/               # POM sınıfları, Locator'lar ve sayfaya özgü aksiyon metotları
│       │   │   ├── BasketPage.java
│       │   │   ├── CheckoutPage.java
│       │   │   ├── HomePage.java
│       │   │   ├── LoginPage.java
│       │   │   └── RegisterPage.java
│       │   ├── runners/             # Cucumber ve TestNG koşum ayarları
│       │   │   └── TestRunner.java
│       │   ├── stepdefinitions/     # Gherkin adımlarının Java karşılıkları ve Hooks yapısı
│       │   │   ├── Hooks.java
│       │   │   ├── JuiceShopBasketSteps.java
│       │   │   ├── JuiceShopCheckoutSteps.java
│       │   │   ├── JuiceShopHomeSteps.java
│       │   │   ├── JuiceShopLoginSteps.java
│       │   │   ├── JuiceShopRegisterSteps.java
│       │   │   └── JuiceShopSearchSteps.java
│       │   └── utilities/           # Yardımcı sınıflar ve merkezi yapılandırma okuyucular
│       │       ├── ConfigurationReader.java
│       │       ├── Driver.java
│       │       ├── ElementHelper.java
│       │       └── TestData.java
│       └── resources/
│           └── features/            # Gherkin formatında yazılmış BDD senaryoları (.feature)
│               ├── JuiceShopBasket.feature
│               ├── JuiceShopCheckout.feature
│               ├── JuiceShopHome.feature
│               ├── JuiceShopLogin.feature
│               ├── JuiceShopRegister.feature
│               └── JuiceShopSearch.feature
├── configuration.properties         # Ortam URL ve tarayıcı yapılandırması
├── testdata.properties              # Test kullanıcı bilgileri ve veri yönetimi
└── pom.xml                          # Maven bağımlılıkları ve derleme ayarları
```


## Test Kapsamı ve Senaryolar

En son test koşumu **46 saniye** içerisinde **14/14 (%100 başarı)** oranıyla tamamlanmıştır.

| Feature Dosyası | Senaryo / Kapsam | Etiketler (Tags) | Test Tipi |
| :--- | :--- | :--- | :--- |
| **`JuiceShopHome.feature`** | 1. Ana sayfaya erişim ve sayfa başlığı (`OWASP Juice Shop`) doğrulaması<br>2. Açılış pop-up'larını (Welcome Banner & Çerezler) kapatıp Login sayfasına yönlenme | - | Pozitif / Smoke |
| **`JuiceShopLogin.feature`** | 3. Geçerli kullanıcı bilgileri ile başarılı giriş ve `Log out` butonu kontrolü<br>4. Giriş yapmış kullanıcının güvenli çıkış (`Logout`) yapması<br>5-7. **Data-Driven (Scenario Outline):** 3 farklı geçersiz/kayıtsız veri seti ile hatalı giriş denemesi ve `"Invalid email or password."` mesajı kontrolü<br>8. E-posta ve şifre alanları boş bırakıldığında `Login` butonunun pasif (`disabled`) kalması | `@smoke`<br>`@positive`<br>`@logout`<br>`@negative`<br>`@datadriven` | Pozitif, Negatif & Data-Driven |
| **`JuiceShopRegister.feature`** | 9. Dinamik e-posta ve güvenlik sorusu seçimi ile yeni kullanıcı kaydı ve Login sayfasına yönlendirme kontrolü<br>10. Şifre ve şifre tekrarı uyuşmadığında `"Passwords do not match"` hata mesajının doğrulanması | `@negative` | Pozitif & Negatif |
| **`JuiceShopSearch.feature`** | 11. Arama kutusuna `"apple"` yazıldığında listelenen tüm ürünlerin başlığında ilgili kelimenin geçtiğinin doğrulanması<br>12. Sistemde olmayan bir ürün (`"xyznotfound99"`) aratıldığında hiçbir ürün kartının listelenmediğinin (`invisibilityOfElementLocated`) doğrulanması | `@negative`<br>`@search` | Pozitif & Negatif |
| **`JuiceShopBasket.feature`** | 13. Sepetteki ürün adedi `1` artırıldığında yeni adedin `"2"` olduğunun ve toplam sepet tutarının birim fiyata göre (`birimFiyat * adet`) matematiksel olarak doğru hesaplandığının doğrulanması | `@smoke`<br>`@basket` | Fonksiyonel & Hesaplama |
| **`JuiceShopCheckout.feature`** | 14. **Uçtan Uca Sipariş (E2E):** Giriş yapma $\rightarrow$ Sepete ürün ekleme $\rightarrow$ Sepeti onaylama $\rightarrow$ Kayıtlı adres seçimi $\rightarrow$ Teslimat hızı seçimi $\rightarrow$ Ödeme yöntemi seçimi $\rightarrow$ Siparişi tamamlama ve onay mesajının doğrulanması | - | End-to-End (E2E) |

---

## Kurulum ve Çalıştırma

### Ön Koşullar
* **Java JDK 21** veya üzeri
* **Apache Maven**
* **Google Chrome**, **Firefox** veya **Microsoft Edge** tarayıcısı
* Yerelde çalışan veya erişilebilir bir **OWASP Juice Shop** örneği (Varsayılan: `http://localhost:3000` veya Heroku/Docker adresi)

### 1. Projeyi Klonlayın
```bash
git clone https://github.com/KutayPaca/owasp-juice-shop-automation.git
cd owasp-juice-shop-automation
```

### 2. Yapılandırma Dosyalarını Ayarlayın
Proje ana dizininde bulunan `configuration.properties` ve `testdata.properties` dosyalarının içeriğini kendi ortamınıza göre kontrol edin:

**`configuration.properties`**
```properties
browser=chrome
url=http://localhost:3000
```

**`testdata.properties`**
```properties
admin_email=admin@juice-sh.op
admin_password=admin123
```

### 3. Testleri Çalıştırın

Tüm test senaryolarını çalıştırmak için:
```bash
mvn clean test
```

Sadece belirli bir etikete (`@smoke`, `@negative`, `@basket`, `@search` vb.) sahip senaryoları çalıştırmak için:
```bash
mvn clean test -Dcucumber.filter.tags="@smoke"
```

```bash
mvn clean test -Dcucumber.filter.tags="@negative"
```

---

## Test Raporu Görüntüleme

Test koşumu tamamlandıktan sonra otomatik olarak oluşturulan detaylı Cucumber HTML raporuna aşağıdaki dizinden ulaşabilirsiniz:

```text
target/cucumber-reports/cucumber.html
```

> 💡 **:** Raporu tarayıcıda açarak her bir Gherkin adımının çalışma süresini, kullanılan etiketleri, ortam bilgilerini (Windows 11, OpenJDK 21, Cucumber v7.20.1) ve varsa hata ekran görüntülerini inceleyebilirsiniz.