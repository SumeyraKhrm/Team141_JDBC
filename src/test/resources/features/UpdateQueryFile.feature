Feature: Update query execution

  Background: Database baglantisi kurar
    * Database connection kurar.
      #bu herkes için ortak


  @updateQuery01
  Scenario:  "users" tablosunda sondan bir önceki harfi e olan "usernamelerin" "mobile" numarasını update ediniz

    * UpdateQuery01 hazirlanir ve calistirilir.
    * UpdateQuery01 sonuclari islenir.
    * Database connection kapatir.


