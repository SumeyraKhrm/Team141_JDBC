Feature: Select Query Execution

  Background: Database baglantisi kurar
    * Database connection kurar

  @Query01
  Scenario:  Deposits tablosunda amount degerine gore id sorgulama testi.

    Given Query01 hazirlar
    When Query01 sonuclarini isler
    And Database baglantisini kapatir.


  @Query02
  Scenario: "cron_schedules" tablosundaki ilk 2 kaydın "name" bilgisini doğrulama testi.

    Given Query02 hazirlar
    When Query02 sonuclarini isler
    And Database baglantisini kapatir.


  @Query03

  Scenario: email adresinden kullanici ismi sorgulama testi.

    Given Database connection kurar.
    When Query03 hazirlar.
    Then Query03 sonuclarini isler.
    And Database connection kapatir.