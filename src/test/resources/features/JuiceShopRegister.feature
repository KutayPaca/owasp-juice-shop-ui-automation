Feature: Juice Shop Kullanici Kayit Islemleri
  Scenario: Yeni bir kullanici basariyla kayit olabilmeli
    Given kullanici juice shop uygulamasina girer
    And kullanici acilis popuplarini kapatir
    And kullanici Account menusunden Login sayfasina gider
    When kullanici kayit ol baglantisina tiklar
    And kullanici gecerli kayit bilgilerini doldurup onaylar
    Then kullanici kaydin basariyla tamamlandigini dogrular