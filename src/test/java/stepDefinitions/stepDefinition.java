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

public class stepDefinition {


    Connection connection;
    Statement statement;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    String Query;
    QueryManage queryManage = new QueryManage();

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
        //tek satır halinde

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





}
