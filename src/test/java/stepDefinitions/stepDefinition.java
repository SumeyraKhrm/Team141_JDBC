package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manage.QueryManage;
import org.junit.Assert;
import utilities.ConfigReader;
import utilities.JDBCReusableMethods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class stepDefinition {


    Connection connection;
    Statement statement;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    String Query;
    QueryManage queryManage = new QueryManage();

    int intResult;
    int silinecekID;


    @Given("Database connection kurar")
    public void database_connection_kurar() throws SQLException {
       connection =DriverManager.getConnection(ConfigReader.getProperty("URL"),
               ConfigReader.getProperty("USERNAME"),ConfigReader.getProperty("PASSWORD"));
    }
    @Given("Query01 hazirlar")
    public void query01_hazirlar() throws SQLException {
        // Database içindeki "deposits" toblosunda
        // "amount" değeri 100$ ile 500$ arasında olan user_id’leri doğrulayınız

        String Query = "SELECT user_id FROM u201212290_loantec.deposits WHERE amount BETWEEN 100 AND 500";
        //tek satır halinde yazılmalı

        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet =  statement.executeQuery(Query);

    }
    @When("Query01 sonuclarini isler")
    public void query01_sonuclarini_isler() throws SQLException {

        resultSet.next();

        int expectedID=1;
        int actualID=resultSet.getInt("user_id");

        assertEquals(expectedID,actualID);

       // assertEquals(1,resultSet.getInt("user_id")); aynı yukarıdakiyle

        System.out.println("ResultSet'ten gelen user_id:    "+resultSet.getInt("user_id"));


    }
    @When("Database baglantisini kapatir.")
    public void database_baglantisini_kapatir() throws SQLException {

        resultSet.close();
        statement.close();
        connection.close();

    }

    // --- Query02 ---
    @Given("Query02 hazirlar")
    public void query02_hazirlar() {

        Query = queryManage.getQuery02();

    }
    @When("Query02 sonuclarini isler")
    public void query02_sonuclarini_isler() throws SQLException {

        /*
        5 Minutes
        10 Minutes
         */

        statement =connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery(Query);

        List<String> expectedResultList = new ArrayList<>();
        expectedResultList.add("5 Minutes");
        expectedResultList.add("10 Minutes");

        List<String> actualResultList = new ArrayList<>();

        while(resultSet.next()){
            String name = resultSet.getString("name");
            actualResultList.add(name);

        }

        for (int i = 0; i < actualResultList.size(); i++) {
            assertEquals(expectedResultList.get(i),actualResultList.get(i));
            System.out.println(i+" . index dogrulandi");
        }

    }


    @Given("Database connection kurar.")
    public void database_connection_kurar_() {
        JDBCReusableMethods.getConnection();

    }
    @When("Query03 hazirlar.")
    public void query03_hazirlar() {
        Query = queryManage.getQuery03();

    }
    @Then("Query03 sonuclarini isler.")
    public void query03_sonuclarini_isler() throws SQLException {
        String expectedFirstname = "Test";

        resultSet = JDBCReusableMethods.executeQuery(Query);

        resultSet.next();
        String actualFirstname = resultSet.getString("firstname");

        assertEquals(expectedFirstname,actualFirstname);

    }

    @Then("Database connection kapatir.")
    public void database_connection_kapatir() {
        JDBCReusableMethods.closeConnection();

    }

    //   *************************  UPDATE QUERY ****************************

    //UpdateQuery01

    @Given("UpdateQuery01 hazirlanir ve calistirilir.")
    public void update_query01_hazirlanir_ve_calistirilir() {

       Query = queryManage.getUpdateQuery01();

       intResult = JDBCReusableMethods.updateQuery(Query);

    }
    @Given("UpdateQuery01 sonuclari islenir.")
    public void update_query01_sonuclari_islenir() {

        assertEquals(1,intResult);

    }


    //UpdateQuery02

    @Given("Updatequery02 hazirlanir ve calistirilir.")
    public void updatequery02_hazirlanir_ve_calistirilir() {

    Query = queryManage.getUpdateQuery02();

    intResult = JDBCReusableMethods.updateQuery(Query);

    }
    @Given("Updatequery02 sonuclari islenir.")
    public void updatequery02_sonuclari_islenir() {

        Assert.assertEquals(1,intResult);

    }




    // ***** Prapered Statement Query Executions ********************
    // @updatePraperedQuery01


    @Given("UpdatePraperedQuery01 hazirlanir ve calistirilir.")
    public void update_prapered_query01_hazirlanir_ve_calistirilir() throws SQLException {

        Query = queryManage.getPreparedQuery01();

        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(Query);

        // "users" tablosunda sondan bir önceki harfi r olan
        // "usernamelerin" "mobile" numarasını update ediniz

        // update users set mobile = ? where username like ?

        preparedStatement.setString(1,"85462");
        preparedStatement.setString(2,"%r_");

        intResult =preparedStatement.executeUpdate();
    }
    @Given("UpdatePraperedQuery01 sonuclari islenir.")
    public void update_prapered_query01_sonuclari_islenir() {

        assertEquals(1,intResult);

    }

    //@updatePreparedQuery02

    @Given("preparedUpdatequery02 hazirlanir ve calistirilir.")
    public void prepared_updatequery02_hazirlanir_ve_calistirilir() throws SQLException {

        Query = queryManage.getPreparedQuery02();

        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(Query);


        //update admin_notifications set is_read = ? where id = ?
        preparedStatement.setInt(1,1);
        preparedStatement.setInt(2,4);

        intResult =preparedStatement.executeUpdate();

    }
    @Given("preparedUpdatequery02 sonuclari islenir.")
    public void prepared_updatequery02_sonuclari_islenir() {

        Assert.assertEquals(1,intResult);
    }



    // insertPreparedQuery01

    @Given("insertPreparedQuery hazirlanir ve calistirilir.")
    public void insert_prepared_query_hazirlanir_ve_calistirilir() throws SQLException {

        Query = queryManage.getInsertPreparedQuery01();

        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(Query);

        //insert into admin_password_resets (id, email,token, status, created_at) values
        // 2210,'info@loantechexper.com','234267',1,'2024-02-10-00:00:00'


        preparedStatement.setInt(1, 2211);
        preparedStatement.setString(2,"test141@gmail.com");
        preparedStatement.setString(3,"234267");
        preparedStatement.setInt(4,1);
        preparedStatement.setString(5,"2024-02-10-00:00:00");

        intResult =preparedStatement.executeUpdate();

    }
    @Given("insertPreparedQuery sonuclari dogrulanir.")
    public void insert_prepared_query_sonuclari_dogrulanir() {

        assertEquals(1,intResult);
    }



    // insertPreparedQuery02

    @Given("insertPreparedQuery02 hazirlanir ve calistirilir.")
    public void insert_prepared_query02_hazirlanir_ve_calistirilir() throws SQLException {

        Query = queryManage.getInsertPreparedQuery02();

        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(Query);

        //insert into device_tokens (id, user_id, is_app, token, created_at, updated_at ) values
        //    (1, 0,3,'token141','2024-02-10-00:00:00','2024-02-10-00:00:00');

        preparedStatement.setInt(1, 2);
        preparedStatement.setInt(2,0);
        preparedStatement.setInt(3,3);
        preparedStatement.setString(4,"token141");
        preparedStatement.setString(5,"2024-02-10-00:00:00");
        preparedStatement.setString(6,"2024-02-10-00:00:00");


        intResult =preparedStatement.executeUpdate();


    }
    @Given("insertPreparedQuery02 sonuclari dogrulanir.")
    public void insert_prepared_query02_sonuclari_dogrulanir() {

        assertEquals(1,intResult);
    }



    //deletePreparedQuery01

    @Given("deleteQuery01 hazirlanir ve calistirilir.")
    public void delete_query01_hazirlanir_ve_calistirilir() throws SQLException {

        Query = queryManage.getDeletePreparedQuery01();
        silinecekID = 3;
        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(Query);


        preparedStatement.setInt(1,silinecekID);


        intResult =preparedStatement.executeUpdate();

    }
    @Given("deleteQuery01 sonuclari dogrulanir.")
    public void delete_query01_sonuclari_dogrulanir() throws SQLException {

        assertEquals(1, intResult);  // standart dogrulama

        String controlQuery = queryManage.getDeleteControlQuery();

        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(controlQuery);
        preparedStatement.setInt(1,silinecekID);

        resultSet = preparedStatement.executeQuery();

        assertFalse(resultSet.next());  // control dogrulamasi



    }



}
