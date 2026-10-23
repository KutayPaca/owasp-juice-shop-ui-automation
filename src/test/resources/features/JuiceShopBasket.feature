Feature: Juice Shop Sepet Yonetimi Islemleri

  Background:
    Given kullanici juice shop uygulamasina girer
    And kullanici acilis popuplarini kapatir
    And kullanici Account menusunden Login sayfasina gider
    When kullanici gecerli kullanici bilgileri ile giris yapar

  @smoke @basket
  Scenario: Sepetteki urun adedi artirildiginda toplam fiyat dogru guncellenmeli
    When kullanici ilk urunu sepete ekler
    And kullanici sepet sayfasina gider
    And kullanici sepetteki urunun adedini 1 artirir
    Then urun adedinin "2" oldugunu dogrular
    And sepetteki toplam tutarin birim fiyata gore dogru hesaplandigini dogrular