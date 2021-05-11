package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import DBcontrol.DBconnection;
import DTO.FavoriteDTO;

public class MyPageDAO {
    private PreparedStatement psmt;
    private Connection conn;
    private ResultSet rs;
    
    MyPageDAO(){
        psmt = null;
        conn = null;
        rs = null;
    }
       //  즐겨찾기 리스트 조회
       public ArrayList<FavoriteDTO> inquiryFavorite(String userId){
        String sql = "SELECT * FROM favorite WHERE user_id = ?";
        
        ArrayList<FavoriteDTO> list = new ArrayList<FavoriteDTO>();
        try{
            conn = DBconnection.getConnection();

            psmt = conn.prepareStatement(sql);
            psmt.setString(1, userId);
            rs = psmt.executeQuery();
            while(rs.next()){
                int no = rs.getInt("no");
                String user_id = rs.getString("user_id");
                String destination_code = rs.getString("destination_code");
                String destination_name = rs.getString("destination_name");
                Timestamp add_date = rs.getTimestamp("add_date");
                String sortation = rs.getString("sortation");
                FavoriteDTO favoriteDTO = new FavoriteDTO(no, user_id, destination_code, destination_name, add_date, sortation);
                list.add(favoriteDTO);
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
                if(rs != null){
                    rs.close();
                }
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

        return list;
    }
    //  즐겨찾기 삭제
    public void deleteFavorite(int no){
        String sql = "DELETE FROM favorite WHERE no = ?";
        try{
            conn = DBconnection.getConnection();

            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, no);

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
    //  회원 정보(userDTO) 불러오기
    
}