import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class DB { 
//private으로 만들었기때문에 new 못함 static 가지고있어야 접근가능
    private DB() {
    };

    private static Connection connect = null;

    public static Connection open() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                String url = "jdbc:oracle:thin:@localhost:1521:XE";
                connect = DriverManager.getConnection(url, "system", "dita1234");
            } catch (SQLException e) {
                System.out.println("DB연결에러발생");
                e.printStackTrace();
            }
        }
        return connect;
    }

    public static void close(Connection connect, PreparedStatement pstmt) {
        try 
        {
            pstmt.close();
            connect.close();
        } 
        catch (Exception e) 
        {
        }
    }

    public static void close(Connection connect, PreparedStatement pstmt, ResultSet rs) {
        try 
        {
            rs.close();
            pstmt.close();
            connect.close();
        } 
        catch (Exception e) 
        {
        }
    }

}