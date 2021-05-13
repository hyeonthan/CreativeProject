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
    private PreparedStatement pstmt;
    private Connection conn;
    private ResultSet rs;

    // 화장실 위치로 검색
    public ArrayList<ToiletDTO> inquireToiletByLocation(double selLatitude, double selLongitude, int range){
        ArrayList<ToiletDTO> dtos= new ArrayList<ToiletDTO>();
        try {
            String query = "select * from toilet where power((latitude-?),2)+power((longitude-?),2)<?";

            conn= DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1,selLatitude);
            pstmt.setDouble(2,selLongitude);
            pstmt.setInt(3,range);
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
    public ArrayList<ParkingLotsDTO> inquireParkingLotByLocation(double selLatitude, double selLongitude, int range){
        ArrayList<ParkingLotsDTO> dtos= new ArrayList<ParkingLotsDTO>();
        try {
            String query = "select * from parking_lot where power((latitude-?),2)+power((longitude-?),2)<?";

            conn= DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1,selLatitude);
            pstmt.setDouble(2,selLongitude);
            pstmt.setInt(3,range);
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
