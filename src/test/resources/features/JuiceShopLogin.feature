Feature: Juice Shop Login Islemleri

  Background:
    Given kullanici juice shop uygulamasina girer
    And kullanici acilis popuplarini kapatir
    And kullanici Account menusunden Login sayfasina gider

  @smoke @positive
  Scenario: Gecerli kullanici bilgileri ile basariyla giris yapilabilmeli
    When kullanici gecerli kullanici bilgileri ile giris yapar
    Then kullanici basariyla giris yapildigini dogrular

  @smoke @logout
  Scenario: Giris yapmis kullanici basariyla cikis yapabilmeli
    When kullanici gecerli kullanici bilgileri ile giris yapar
    And kullanici oturumu kapatir
    Then kullanici basariyla cikis yapildigini dogrular

  @negative @datadriven
  Scenario Outline: Gecersiz veya kayitli olmayan bilgilerle giris denendiginde hata alinmali
    When kullanici email alanina "<email>" ve sifre alanina "<sifre>" yazar
    Then kullanici hata mesajinin "<beklenenHata>" icerdigini dogrular

    Examples:
      | email                   | sifre          | beklenenHata               |
      | gecersiz_hesap@test.com | yanlisSifre123 | Invalid email or password. |
      | kayitliolmayan@test.com | YanlisSifre.1  | Invalid email or password. |
      | admin@juice-sh.op       | 99999999       | Invalid email or password. |

  @negative
  Scenario: Bos alan birakildiginda login butonu pasif kalmali
    When kullanici email alanini ve sifre alanini bos birakir
    Then kullanici login butonunun tiklanamaz oldugunu dogrular