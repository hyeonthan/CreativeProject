package DBcontrol;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/teamproject1";
        String user = "root";
        String pass = "saera1203!";

        Connection conn = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("드라이버 검색 성공");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("접속 성공 " + conn);
        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 검색 실패!");
            e.printStackTrace();
        }
        catch(SQLException e){
            System.out.println("접속 실패");
            e.printStackTrace();
        }
        return conn;
    }
}
