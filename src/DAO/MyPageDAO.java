package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import DBcontrol.DBconnection;
import DTO.FavoriteDTO;
import DTO.ReviewDTO;
import DTO.UserDTO;

public class MyPageDAO {
    private PreparedStatement psmt;
    private Connection conn;
    private ResultSet rs;
    private Savepoint sp;
    public MyPageDAO(){
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
            conn.setAutoCommit(false);
            sp = conn.setSavepoint("SavePoint1");

            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, no);

            psmt.executeUpdate();
            conn.commit();
        }catch(SQLException sqle1){
            try{
                System.out.println("rollback 실행");
                conn.rollback(sp);
                sqle1.printStackTrace();
            } catch(SQLException sqle2){
                sqle2.printStackTrace();
            }
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
    public UserDTO roadUser(String userId){
        String sql = "SELECT * FROM user WHERE id = ?";

        UserDTO userDTO = null;
        try{
            conn = DBconnection.getConnection();

            psmt = conn.prepareStatement(sql);
            psmt.setString(1, userId);
            rs = psmt.executeQuery();
            rs.next();

            String password = rs.getString("password");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String gender = rs.getString("gender");
            String Do = rs.getString("Do");
            String city = rs.getString("city");
            String address = rs.getString("address");
            Timestamp creation_date = rs.getTimestamp("creation_date");
            Timestamp modify_date = rs.getTimestamp("modify_date");
            userDTO = new UserDTO(userId, password, name, age, gender, Do, city, address, creation_date, modify_date);
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
        return userDTO;
    }
    //  회원 정보 수정하기
    public void reservationUser(String userId, String name, String age, String gender, String Do, String city, String address){
        String sql = "UPDATE user SET name=?, age=?, gender=?, Do=?, city=?, address=?, modify_date=? WHERE id=?";
        try{
            conn = DBconnection.getConnection();
            conn.setAutoCommit(false);
            sp = conn.setSavepoint("SavePoint1");
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, name);
            psmt.setString(2, age);
            psmt.setString(3, gender);
            psmt.setString(4, Do);
            psmt.setString(5, city);
            psmt.setString(6, address);
            psmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            psmt.setString(8, userId);

            psmt.executeUpdate();
            conn.commit();
        } catch (SQLException sqlException) {
            try {
                System.out.println("rollback 실행");
                conn.rollback(sp);
                sqlException.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
                if (psmt != null) {
                    psmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
    }
    //  최근 조회 리스트 불러오기
   

     // 내가 쓴 리뷰 가져오기
     public ArrayList<ReviewDTO> inquireMyReview(String id){
        ArrayList<ReviewDTO> dtos= new ArrayList<ReviewDTO>();
        try {
            String query = "select * from review where user_id=?";

            conn= DBconnection.getConnection();
            psmt = conn.prepareStatement(query);
            psmt.setString(1,id);
            rs = psmt.executeQuery();

            while(rs.next()){
                String user_id= rs.getString("user_id");
                String content= rs.getString("content");
                int scope= rs.getInt("scope");
                Timestamp reporting_date = rs.getTimestamp("reporting_date");
                String destination_code =rs.getString("destination_code");
                String destination_name = rs.getString("destination_name");
                Timestamp modify_date = rs.getTimestamp("modify_date)");
                byte[] image =rs.getBytes("image");

                ReviewDTO dto =new ReviewDTO(user_id, content,scope, destination_code, destination_name,modify_date, reporting_date, image);
                dtos.add(dto);
            }
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
                if (psmt != null) {
                    psmt.close();
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

    // 내가 쓴 리뷰 삭제하기
    public boolean deleteReview (int selNo){
        boolean check =false;
        try {
            String query = "delete from review where no =?";

            conn= DBconnection.getConnection();
            conn.setAutoCommit(false);
            sp = conn.setSavepoint("Savepoint1");

            psmt = conn.prepareStatement(query);
            psmt.setInt(1,selNo);

            psmt.executeUpdate();
            conn.commit();
            check = true;
        }
        catch (SQLException sqlException) {
            try {
                System.out.println("rollback 실행");
                conn.rollback(sp);
                sqlException.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
                if (psmt != null) {
                    psmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return check;
    }
}

