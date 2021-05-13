package DAO;

import DBcontrol.DBconnection;

import java.sql.*;
import java.time.LocalDateTime;

public class FavoriteDAO {
    private PreparedStatement psmt;
    private Connection conn;
    private ResultSet rs;
    
    FavoriteDAO(){
        psmt = null;
        conn = null;
        rs = null;
    }
 
    //  즐겨찾기 추가
    public void addFavorite(String userId, String destinationCode){
        String sql1 = "SELECT destination_name FROM destination WHERE destination_code = ?";
        String sql2 = "INSERT INTO favorite(user_id, destination_code, destination_name, add_date, sortation) VALUES(?,?,?,?,?)";
        try{
            //  ResultSet으로 destination_name get
            conn = DBconnection.getConnection();
            
            psmt = conn.prepareStatement(sql1);
            psmt.setString(1, destinationCode);
            rs = psmt.executeQuery();
            rs.next();
            String sortation = rs.getString("sortation");
            String destination_name = rs.getString("destination_name");
            psmt.close();
            conn.close();

            conn = DBconnection.getConnection();

            psmt = conn.prepareStatement(sql2);
            psmt.setString(1, userId);
            psmt.setString(2, destinationCode);
            psmt.setString(3, destination_name);
            psmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            psmt.setString(5, sortation);

            psmt.executeUpdate();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
                if(psmt != null){
                    psmt.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    
}
