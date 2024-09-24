import java.sql.*;

public class JDBC {


    /*

	NOT! : SIZDEN DATABASE TESTI YAPMANIZ ISTENDIGINDE
	DATABASE ADMINISTRATOR'DEN ACCESS INFORMATION'LARI
	ISTEMELISINIZ.

LoanTech Exper Database Access Information
type	jdbc:mysql
host/ip	45.87.83.5
port	3306
database_name	u168183796_qaloantec

username	u168183796_qaloantecuser
password	0&vG1A/MuWN

NOT!: GELEN ACCESS'LERDEN BIZ URL USERNAME VE PASSWORD OLUSTURMALIYIZ.

URL: "jdbc:mysql://45.87.83.5/u168183796_qaloantec";
USERNAME= "u168183796_qaloantecuser";
PASSWORD="0&vG1A/MuWN";
---------------------------------------

DB_DATABASE=u201212290_loantec
DB_USERNAME=u201212290_loantecuser
DB_PASSWORD=HPo?+7r$
hostname:  195.35.59.63
URL: "jdbc:mysql://195.35.59.63/u201212290_loantec";

	 */

    public static <Stamement> void main(String[] args) throws ClassNotFoundException, SQLException {

        //	1- Adim olarak: JDBC Sürücüsünü Yükle

        Class.forName("com.mysql.cj.jdbc.Driver");  // MySql surucusunu yukledik.


        // 2- Adim olarak:  Veritabanı Bağlantısıni Kur

        String URL="jdbc:mysql://195.35.59.63/u201212290_loantec";
        String USERNAME="u201212290_loantecuser";
        String PASSWORD="HPo?+7r$";

        Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

        // 3- Adim olarak: SQL Sorguları Oluştur

        String Query ="select * from u201212290_loantec.users"; //bosluksuz, tek satırda ve dogru yazılması onemli !!


        // 4. Adim olarak: SQL Sorguları Çalıştır

       Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery(Query);


        // 5.Adim olarak: Sonuçları İşle

        resultSet.next(); //1. satır

        System.out.println(resultSet.getString("firstname")); // Elf

        resultSet.next(); //2. satıra geldi suan

        System.out.println(resultSet.getString("lastname"));  // Test
        System.out.println(resultSet.getString("email"));  // test@gmail.com


        resultSet.absolute(9);  // istenilen satıra gitme
        System.out.println(resultSet.getString("firstname")); // clementine


        resultSet.first(); // 1. satıra tekrar geldik itereytır ile
        System.out.println(resultSet.getString("firstname")); // Elf


        resultSet.absolute(2);
        System.out.println(resultSet.getString("lastname")); //Test










    }


}
