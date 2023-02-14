package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SqlTest {

    //테스트 클래스 및 함수는 public으로 만들어야됨
    //테스트 함수는 void(리턴값이 없다.)
    //Given When Then

    private static Connection connect = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

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
        connect.setAutoCommit(false);
        return connect;
    }

    public static void close() {
        try {
            rs.close();
            pstmt.close();
            connect.close();
        } catch (Exception e) {
            // 무시
        }
    }

    @Before
    public void doBefore() throws SQLException {
        open(); //DB오픈
    }

    @After
    public void doAfter() {
        close(); //DB닫기
    }

    @Test
    public void insertTest() throws SQLException {

        // given
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO HR.REGIONS");
        sb.append(" VALUES (5, 'Moon')");

        // when
        pstmt = connect.prepareStatement(sb.toString());
        int result = pstmt.executeUpdate();

        //connect.commit();//실제로 데이터가 들어감
        connect.rollback();//데이터가 테스트후 롤백됨

        // then
        assertEquals(result, 1);
    }
}
