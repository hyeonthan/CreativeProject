package DAO;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;

import DBcontrol.DBconnection;
import DTO.ReviewDTO;

public class ReviewDAO {
    private PreparedStatement pstmt;
    private Connection conn;
    private ResultSet rs;
    private Savepoint sp;

    // 리뷰 가져오기
    public ArrayList<ReviewDTO> inquireReview(String selDestination){
        ArrayList<ReviewDTO> dtos= new ArrayList<ReviewDTO>();
        try {
            String query = "select * from review where destination_code=?";

            conn= DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,selDestination);
            rs = pstmt.executeQuery();

            while(rs.next()){
                int no = rs.getInt("rs");
                String user_id= rs.getString("user_id");
                String content= rs.getString("content");
                int scope= rs.getInt("scope");
                Timestamp reporting_date = rs.getTimestamp("reporting_date");
                String destination_code =rs.getString("destination_code");
                String destination_name = rs.getString("destination_name");
                Timestamp modify_date = rs.getTimestamp("modify_date)");
                byte[] image = rs.getBytes("image");

                ReviewDTO dto = new ReviewDTO(no, user_id, content,scope, destination_code, destination_name,modify_date, reporting_date, image);
                dtos.add(dto);
            }
           
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return dtos;
    }

   
    
}
