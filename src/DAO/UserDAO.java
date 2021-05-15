package DAO;

import DBcontrol.DBconnection;
import DTO.UserDTO;

import java.sql.*;

public class UserDAO {
    private PreparedStatement pstmt;
    private Connection conn;
    private ResultSet rs;
    private Savepoint sp;

    // 회원추가
    public boolean insertUser(UserDTO dto){
        boolean check = false;
        try{
            String query = "insert into User values(?,(select CONCAT('*', UPPER(SHA1(UNHEX(SHA1((?)))))) as password),?,?,?,?,?,?,?,?)";
            conn = DBconnection.getConnection();
            conn.setAutoCommit(false);
            sp = conn.setSavepoint("Savepoint1");

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,dto.getId());
            pstmt.setString(2,dto.getPassword());
            pstmt.setString(3,dto.getName());
            pstmt.setInt(4,dto.getAge());
            pstmt.setString(5, dto.getGender());
            pstmt.setString(6, dto.getDo());
            pstmt.setString(7, dto.getCity());
            pstmt.setString(8,dto.getAddress());
            pstmt.setTimestamp(9,dto.getCreation_date());
            pstmt.setTimestamp(10,dto.getModify_date());

            pstmt.executeUpdate();
            conn.commit();
            check = true;
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
        return check;
    }


    //아이디 중복 체크
    boolean duplicationId(String id){
        boolean check = false;
        try{
            String sql = "SELECT ID FROM USERS WHERE ID = ?";

            conn = DBconnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();
            rs.last();

            int num = rs.getRow();
            if(num != 0) 	// 중복 ID 없을 시 false
                check = true;
        } catch(SQLException sqle) {
            System.out.println(sqle);
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
        return check;
    }

    //아이디 비번 체크
    boolean checkUser(String id,String pwd){
        boolean check = false;
        try {
            String sql = "SELECT id, password FROM user WHERE id = ? AND password = (select CONCAT('*', UPPER(SHA1(UNHEX(SHA1((?)))))) as password) ";
            conn = DBconnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();
            rs.last();
            int num = rs.getRow();
            if(num == 1) 	// 맞는 ID, pwd 입력시 true 입력
                check = true;
        }
        catch(SQLException sqle) {
            System.out.println(sqle);
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return check;
    }
}
