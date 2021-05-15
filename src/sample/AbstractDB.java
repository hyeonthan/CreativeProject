package sample;

import java.sql.*;

public abstract class AbstractDB {
    Connection con;
    PreparedStatement pstmt;
    Statement stmt;
    CallableStatement cstmt;
    ResultSet rs;
    int result = 0;
    String server = "localhost"; // MySQL 서버 주소
    String database = "test"; // MySQL DATABASE 이름
    String user_name = "root"; //  MySQL 서버 아이디
    String password = "saera1203!"; // MySQL 서버 비밀번호

    public final void execute() {
        try {
            init();
            query();
            close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void init() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
    }

    public abstract void query() throws Exception;

    private void close() {
        if (rs != null)
            try {
                rs.close();
            } catch (Exception e) {
            }
        if (pstmt != null)
            try {
                rs.close();
            } catch (Exception e) {
            }
        if (con != null)
            try {
                rs.close();
            } catch (Exception e) {
            }
    }
}


//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class ConnectionUtil {
//
//    static {
//        try {
//            Class.forName("oracle.jdbc.OracleDriver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//
//            // 프로그램 강제 종료
//            System.exit(1);
//        }
//    }
//
//    public static Connection getConnection() throws SQLException {
//        String url = "jdbc:oracle:thin:@192.168.10.96:1521:xe";
//        String user = "hr";
//        String password = "zxcv1234";
//
//        Connection con = DriverManager.getConnection(url, user, password);
//        return con;
//    }
//}