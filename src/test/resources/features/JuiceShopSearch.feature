Feature: Juice Shop Urun Arama Islemleri
  Scenario: Kullanici arama yaptiginda ilgili urunleri gorebilmeli
    Given kullanici juice shop uygulamasina girer
    And kullanici acilis popuplarini kapatir
    When kullanici arama kutusuna "apple" yazar ve aratir
    Then listelenen tum urunlerin basliginda "apple" kelimesi gectigini dogrular

  @negative @search
  Scenario: Sistemde olmayan bir urun aratildiginda hicbir urun listelenmemeli
    Given kullanici juice shop uygulamasina girer
    And kullanici acilis popuplarini kapatir
    When kullanici arama kutusuna "xyznotfound99" yazar ve aratir
    Then arama sonucunda hicbir urunun listelenmedigini dogrular