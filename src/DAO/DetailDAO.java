package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBcontrol.DBconnection;
import DTO.BeachDTO;
import DTO.ForestLodgeDTO;
import DTO.TouristSpotDTO;

public class DetailDAO {
    private PreparedStatement pstmt;
    private Connection conn;
    private ResultSet rs;

    //해수욕장 상세정보 가져오기
    public BeachDTO detailBeach(String beachCode) {
        try {
            String query = "select * from beach where code=?";

            conn = DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, beachCode);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String introduction = rs.getString("introduction");
                String Do = rs.getString("do");
                String city = rs.getString("city");
                String address = rs.getString("address");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                String phone_num = rs.getString("phone_num");
                String home_page = rs.getString("home_page");
                String opening_period = rs.getString("opening_period");
                String swim_available_time = rs.getString("swim_available_time");
                String amenities = rs.getString("amenities");

                BeachDTO dto = new BeachDTO(code, name, introduction, Do, city, address, latitude, longitude, phone_num, home_page, opening_period, swim_available_time, amenities);
                return dto;
            }
        } catch (SQLException sqle) {
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
        return null;
    }

    // 휴양림 상세정보 가져오기
    public ForestLodgeDTO detailForestLodge(String forestCode){
        try {
            String query = "select * from forest_lodge where code=?";

            conn= DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,forestCode);
            rs = pstmt.executeQuery();

            while(rs.next()){
                String code = rs.getString("code");
                String name = rs.getString("name");
                String Do = rs.getString("do");
                String city = rs.getString("city");
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
                return dto;
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
        return null;
    }

    // 관광지 상세정보 가져오기
    public TouristSpotDTO detailTouristSpot(String touristCode){
        try {
            String query = "select * from tourist_spot where code=?";

            conn= DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,touristCode);
            rs = pstmt.executeQuery();

            while(rs.next()){
                String code = rs.getString("code");
                String name = rs.getString("name");
                String introduction = rs.getString("introduction");
                String phone_num = rs.getString("phone_num");
                String Do = rs.getString("do");
                String city = rs.getString("city");
                String address = rs.getString("address");
                double latitude= rs.getDouble("latitude");
                double longitude= rs.getDouble("longitude");
                String amenities = rs.getString("amenities");
                int possible_parking = rs.getInt("possible_parking");
                String management_agency = rs.getString("management_agency");

                TouristSpotDTO dto =new TouristSpotDTO(code,name,introduction,phone_num,Do,city,address,latitude,longitude,amenities,possible_parking,management_agency);
                return dto;
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
        return null;
    }

}
