Feature: Juice shop ana sayfa kontrolleri
  Scenario: Kullanici ana sayfaya basariyla erisebilmeli
    Given kullanici juice shop uygulamasina girer
    Then sayfa basliginin "OWASP Juice Shop" icerdigini dogrular

    Scenario: Kullanici ana sayfadaki popuplari kapatip Login sayfasina gidebilmeli
      Given kullanici juice shop uygulamasina girer
      When kullanici acilis popuplarini kapatir
      And kullanici Account menusunden Login sayfasina gider
      Then kullanici Login sayfasinda oldugunu dogrular