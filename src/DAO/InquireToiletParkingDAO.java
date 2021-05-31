package DAO;

import DBcontrol.DBconnection;
import DTO.ParkingLotsDTO;
import DTO.ToiletDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InquireToiletParkingDAO {
    private static PreparedStatement pstmt;
    private static Connection conn;
    private static ResultSet rs;

    // 화장실 위치로 검색
    public static ArrayList<ToiletDTO> inquireToiletByLocation(String selLatitude, String selLongitude, String range){
        ArrayList<ToiletDTO> dtos= new ArrayList<ToiletDTO>();
        try {
            String query = "SELECT *,(6371*acos(cos(radians(" + selLatitude + "))*cos(radians(latitude))*cos(radians(longitude)-radians(" + selLongitude + "))+sin(radians("+ selLatitude + "))*sin(radians(latitude)))) AS distance FROM toilet HAVING distance <= " + range;

            conn= DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while(rs.next()){
                String code = rs.getString("code");
                String name = rs.getString("name");
                String Do = rs.getString("do");
                String city = rs.getString("city");
                String address = rs.getString("address");
                String Public = rs.getString("public");
                String opening_time =rs.getString("opening_time");
                double latitude= rs.getDouble("latitude");
                double longitude= rs.getDouble("longitude");
                String management_agency = rs.getString("management_agency");

                ToiletDTO dto =new ToiletDTO(code,name,Do,city,address,Public,opening_time,latitude,longitude,management_agency);
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

    // 주차장 위치로 검색
    public static ArrayList<ParkingLotsDTO> inquireParkingLotByLocation(String selLatitude, String selLongitude, String range){
        ArrayList<ParkingLotsDTO> dtos= new ArrayList<ParkingLotsDTO>();
        try {
            String query = "SELECT *,(6371*acos(cos(radians(" + selLatitude + "))*cos(radians(latitude))*cos(radians(longitude)-radians(" + selLongitude + "))+sin(radians("+ selLatitude + "))*sin(radians(latitude)))) AS distance FROM parking_lot HAVING distance <= " + range;
            System.out.println(query);
            conn= DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
          
            rs = pstmt.executeQuery();

            while(rs.next()){
                String code = rs.getString("code");
                String name = rs.getString("name");
                String Do = rs.getString("do");
                String city = rs.getString("city");
                String address = rs.getString("address");
                int size = rs.getInt("size");
                String opening_days = rs.getString("opening_days");
                String weekdays_opening_time = rs.getString("weekdays_opening_time");
                String sat_opening_time = rs.getString("sat_opening_time");
                String holiday_opening_time =rs.getString("holiday_opening_time");
                String fee = rs.getString("fee");
                String phone_num = rs.getString("phone_num");
                double latitude= rs.getDouble("latitude");
                double longitude= rs.getDouble("longitude");

                ParkingLotsDTO dto =new ParkingLotsDTO(code,name,Do,city,address,size,opening_days,weekdays_opening_time,sat_opening_time,holiday_opening_time,fee,phone_num,latitude,longitude);
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
