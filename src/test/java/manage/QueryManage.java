package manage;

public class QueryManage {

    private String query02 ="SELECT name FROM cron_schedules limit 2";
    private String query03 ="select * from users where email = 'test@gmail.com'";
    // private ı kullanabilmek için başka bir class da getter olusturuyoruz.
    private String updateQuery01="update users set mobile = 05763452555 where username like '%r_'";
    private String preparedQuery01 = "update users set mobile = ? where username like ?";
    //değişken olan yerlere ? işareti koyuyoruz.
    private String updateQuery02 = "update admin_notifications set is_read=1 where id=3";
    private String preparedQuery02 = "update admin_notifications set is_read= ? where id= ?";
    private String insertPreparedQuery01 = "insert into admin_password_resets (id, email,token, status, created_at) values (?,?,?,?,?)";
    private String insertPreparedQuery02 = "insert into device_tokens (id, user_id, is_app, token, created_at, updated_at) values (?, ?, ?, ?,?,?)";
    private String deletePreparedQuery01 = "delete from extensions where id=?";
    private String deleteControlQuery = "select * from extensions where id = ?";



    //******Getter***********

    public String getQuery02() {
        return query02;
    }


    public String getQuery03() {
        return query03;
    }


    public String getUpdateQuery01() {
        return updateQuery01;
    }


    public String getPreparedQuery01() {
        return preparedQuery01;
    }

    public String getUpdateQuery02() {
        return updateQuery02;
    }

    public String getPreparedQuery02() {
        return preparedQuery02;
    }

    public String getInsertPreparedQuery01() {
        return insertPreparedQuery01;
    }


    public String getInsertPreparedQuery02() {
        return insertPreparedQuery02;
    }


    public String getDeletePreparedQuery01() {
        return deletePreparedQuery01;
    }

    public String getDeleteControlQuery() {
        return deleteControlQuery;
    }
}
