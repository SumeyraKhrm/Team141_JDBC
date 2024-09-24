Feature: Select Query Execution

  Background: Database baglantisi kurar
    * Database connection kurar

  @Query01
  Scenario:  Deposits tablosunda amount degerine gore id sorgulama testi.

    Given Query01 hazirlar
    When Query01 sonuclarini isler
    And Database baglantisini kapatir.



