package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.ConfigReader;

import java.sql.*;

import static org.junit.Assert.assertEquals;

public class stepDefinition {


    Connection connection;
    Statement statement;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

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

}
