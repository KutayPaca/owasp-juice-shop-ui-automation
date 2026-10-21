Feature: Juice Shop Login Islemleri
  Scenario: Gecerli kullanici bilgileri ile basariyla giris yapilabilmeli
    Given kullanici juice shop uygulamasina girer
    And kullanici acilis popuplarini kapatir
    And kullanici Account menusunden Login sayfasina gider
    When kullanici email alanina "admin@juice-sh.op" ve sifre alanina "admin123" yazar
    Then kullanici basariyla giris yapildigini dogrular