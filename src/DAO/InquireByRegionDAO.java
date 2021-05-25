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
            if(sortation.equals("")  && city.equals("")){
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
    
    // 해수욕장 지역으로 검색
    public ArrayList<BeachDTO> inquireBeachByRegion(String Do, String city){
        ArrayList<BeachDTO> dtos= new ArrayList<BeachDTO>();
        try {
            String query = "select * from beach where do = ? and city = ?";

            conn= DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,Do);
            pstmt.setString(2,city);
            rs = pstmt.executeQuery();

            while(rs.next()){
                String code = rs.getString("code");
                String name = rs.getString("name");
                String  introduction= rs.getString("introduction");
                String address = rs.getString("address");
                double latitude= rs.getDouble("latitude");
                double longitude= rs.getDouble("longitude");
                String phone_num = rs.getString("phone_num");
                String home_page= rs.getString("home_page");
                String opening_period = rs.getString("opening_period");
                String swim_available_time= rs.getString("swim_available_time");
                String amenities = rs.getString("amenities");

                BeachDTO dto =new BeachDTO(code,name,introduction,Do,city,address,latitude,longitude,phone_num,home_page,opening_period,swim_available_time,amenities);
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

    // 휴양림 지역으로 검색
    public ArrayList<ForestLodgeDTO> inquireForestLodgeByRegion(String Do, String city){
        ArrayList<ForestLodgeDTO> dtos= new ArrayList<ForestLodgeDTO>();
        try {
            String query = "select * from forest_lodge where do = ? and city = ?";

            conn= DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,Do);
            pstmt.setString(2,city);
            rs = pstmt.executeQuery();

            while(rs.next()){
                String code = rs.getString("code");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone_num = rs.getString("phone_num");
                double latitude= rs.getDouble("latitude");
                double longitude= rs.getDouble("longitude");
                String amenities = rs.getString("amenities");
                String capacity_people = rs.getString("capacity_people");
                String enter_fee = rs.getString("enter_fee");
                String accommodation = rs.getString("accommodation");
                String home_page = rs.getString("home_page");

                ForestLodgeDTO dto =new ForestLodgeDTO(code,name,Do,city,address,phone_num,latitude,longitude,amenities,capacity_people,enter_fee,accommodation,home_page);
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

    // 관광지 지역으로 검색
    public ArrayList<TouristSpotDTO> inquireTouristSpotByRegion(String Do, String city){
        ArrayList<TouristSpotDTO> dtos= new ArrayList<TouristSpotDTO>();
        try {
            String query = "select * from tourist_spot where do = ? and city = ?";

            conn= DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,Do);
            pstmt.setString(2,city);
            rs = pstmt.executeQuery();

            while(rs.next()){
                String code = rs.getString("code");
                String name = rs.getString("name");
                String introduction = rs.getString("introduction");
                String phone_num = rs.getString("phone_num");
                String address = rs.getString("address");
                double latitude= rs.getDouble("latitude");
                double longitude= rs.getDouble("longitude");
                String amenities = rs.getString("amenities");
                int possible_parking = rs.getInt("possible_parking");
                String management_agency = rs.getString("management_agency");

                TouristSpotDTO dto =new TouristSpotDTO(code,name,introduction,phone_num,Do,city,address,latitude,longitude,amenities,possible_parking,management_agency);
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
