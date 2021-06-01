package DAO;

import DBcontrol.DBconnection;
import DTO.*;
import FxController.ShowAlert;

import java.sql.*;
import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

public class InquireByRegionDAO {
    private PreparedStatement pstmt;
    private Connection conn;
    private ResultSet rs;

    //구분 선택없이 지역으로만 가져오기
    public ArrayList<DestinationDTO> inquireDestinationByRegion(String sortation, String Do, String city){
        ArrayList<DestinationDTO> dtos= new ArrayList<DestinationDTO>();
        try {
            String query;
            conn= DBconnection.getConnection();
            
            //  구분 없이 특별시, 광역시 선택
            if(sortation.equals(" ")  && city.equals(" ")){
                query = "select * from destination where do = ?"; 
                pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                pstmt.setString(1,Do);
            }
            //  구분 없이 도, 시/군 선택
            else if(sortation.equals("")){
                query = "select * from destination where do = ? and city = ?"; 
                pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                pstmt.setString(1,Do);
                pstmt.setString(2,city);

            }
            //  구분 선택 and 특별시, 광역시 선택
            else if(city.equals("")){
                query = "select * from destination where sortation = ? AND do = ?"; 
                pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                pstmt.setString(1,sortation);
                pstmt.setString(2,Do);
            }
            //  구분, 도, 시/군 선택
            else {
                query = "select * from destination where sortation = ? AND do = ? AND city = ?"; 
                pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                pstmt.setString(1,sortation);
                pstmt.setString(2,Do);
                pstmt.setString(3,city);
            }
            rs = pstmt.executeQuery();
            //  검색 결과 없을 시 null 반환
            rs.last();
            int checkRow = rs.getRow();
            if(checkRow == 0){
                return null;
            }
            rs.beforeFirst();
            while(rs.next()){
                sortation = rs.getString("sortation");
                city = rs.getString("city");
                String code = rs.getString("code");
                String forest_lodge_code = rs.getString("forest_lodge_code");
                String beach_code = rs.getString("beach_code");
                String tourist_code = rs.getString("tourist_spot_code");
                String name = rs.getString("name");
                String address = rs.getString("address");
                double scope = rs.getDouble("scope");
                int views = rs.getInt("views");
                DestinationDTO dto = new DestinationDTO(code,sortation,forest_lodge_code,beach_code,tourist_code,name,Do,city,address,scope,views);
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
