package manage;

public class QueryManage {

    private String query02 ="SELECT name FROM cron_schedules limit 2";

    private String query03 ="select * from users where email = 'test@gmail.com'";

    // private ı kullanabilmek için başka bir class da getter olusturuyoruz.


    //******Getter***********

    public String getQuery02() {
        return query02;
    }


    public String getQuery03() {
        return query03;
    }



}
