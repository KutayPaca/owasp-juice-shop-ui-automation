Feature: Juice Shop Uctan Uca Siparis Tamamlama (Checkout E2E)
  Scenario: Kayitli kullanici sepete urun ekleyip siparisi basariyla tamamlayabilmeli
    Given kullanici juice shop uygulamasina girer
    And kullanici acilis popuplarini kapatir
    And kullanici Account menusunden Login sayfasina gider
    When kullanici email alanina "admin@juice-sh.op" ve sifre alanina "admin123" yazar
    And kullanici ilk urunu sepete ekler
    And kullanici sepet sayfasina gider
    And kullanici sepeti onaylayıp checkout adimina gecer
    And kullanici kayitli ilk adresi secer ve devam eder
    And kullanici teslimat hizini secer ve devam eder
    And kullanici kayitli odeme yontemini secer ve devam eder
    And kullanici siparisi onaylar
    Then kullanici siparisin basariyla tamamlandigini dogrular