Feature: Update query execution

  Background: Database baglantisi kurar
    * Database connection kurar.
      #bu herkes için ortak


  @updateQuery01
  Scenario:  "users" tablosunda sondan bir önceki harfi e olan "usernamelerin" "mobile" numarasını update ediniz

    * UpdateQuery01 hazirlanir ve calistirilir.
    * UpdateQuery01 sonuclari islenir.
    * Database connection kapatir.


  @updatePreparedQuery01
  Scenario:  "users" tablosunda sondan bir önceki harfi e olan "usernamelerin" "mobile" numarasını update ediniz

    * UpdatePraperedQuery01 hazirlanir ve calistirilir.
    * UpdatePraperedQuery01 sonuclari islenir.
    * Database connection kapatir.


    #"admin_notifications" tablosunda "id=?" olan kullanıcının "is_read=0" olan bildirimlerini
    # '1' Olarak Update edip doğrulayınız.


  @updateQuery02
  Scenario: Verilen id numarasi ile is_read bilgisi update etme testi.

    * Updatequery02 hazirlanir ve calistirilir.
    * Updatequery02 sonuclari islenir.
    * Database connection kapatir.


  @updatePreparedQuery02
  Scenario:  Verilen id numarasi ile is_read bilgisi update etme testi.

    * preparedUpdatequery02 hazirlanir ve calistirilir.
    * preparedUpdatequery02 sonuclari islenir.
    * Database connection kapatir.


  @insertPreparedQuery01
  Scenario: admin_password_resets tablosuna yeni (id,email,token,status,created_at datalarla)
  veri girisi yapiniz.

    * insertPreparedQuery hazirlanir ve calistirilir.
    * insertPreparedQuery sonuclari dogrulanir.
    * Database connection kapatir.


  @insertPreparedQuery02
  Scenario: Database üzerinde "divice_tokens" tablosuna istenen veriyi tek sorguda ekleyiniz.

    * insertPreparedQuery02 hazirlanir ve calistirilir.
    * insertPreparedQuery02 sonuclari dogrulanir.
    * Database connection kapatir.


  @deletePreparedQuery01
  Scenario:  Update_logs tablosunda "id=?" değerine göre
  bir datayı siliniz ve silindiğini doğrulayınız.

    * deleteQuery01 hazirlanir ve calistirilir.
    * deleteQuery01 sonuclari dogrulanir.
    * Database connection kapatir.