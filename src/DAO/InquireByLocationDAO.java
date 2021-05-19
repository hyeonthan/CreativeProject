package DAO;

import DBcontrol.DBconnection;
import DTO.BeachDTO;
import DTO.DestinationDTO;
import DTO.ForestLodgeDTO;
import DTO.TouristSpotDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InquireByLocationDAO {
    private PreparedStatement pstmt;
    private Connection conn;
    private ResultSet rs;

    //구분 선택없이 위치으로만 가져오기
    public ArrayList<DestinationDTO> inquireDestinationByLocation(double selLatitude, double selLongitude, int range){
        ArrayList<DestinationDTO> dtos= new ArrayList<DestinationDTO>();
        try {

            String query = "select * from destination where power((latitude-?),2)+power((longitude-?),2)<?";

            conn= DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1,selLatitude);
            pstmt.setDouble(2,selLongitude);
            pstmt.setInt(3,range);
            rs = pstmt.executeQuery();

            while(rs.next()){
                String code = rs.getString("code");
                String sortation = rs.getString("sortation");
                String forest_lodge_code = rs.getString("forest_lodge_code");
                String beach_code = rs.getString("beach_code");
                String tourist_code = rs.getString("tourist_code");
                String name = rs.getString("name");
                String Do = rs.getString("do");
                String city = rs.getString("city");
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

    // 해수욕장 위치으로 검색
    public ArrayList<BeachDTO> inquireBeachByLocation(double selLatitude, double selLongitude, int range){
        ArrayList<BeachDTO> dtos= new ArrayList<BeachDTO>();
        try {
            String query = "select * from beach where power((latitude-?),2)+power((longitude-?),2)<?";

            conn= DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1,selLatitude);
            pstmt.setDouble(2,selLongitude);
            pstmt.setInt(3,range);
            rs = pstmt.executeQuery();

            while(rs.next()){
                String code = rs.getString("code");
                String name = rs.getString("name");
                String  introduction= rs.getString("introduction");
                String Do = rs.getString("do");
                String city = rs.getString("city");
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

    // 휴양림 위치으로 검색
    public ArrayList<ForestLodgeDTO> inquireForestLodgeByLocation(double selLatitude, double selLongitude, int range){
        ArrayList<ForestLodgeDTO> dtos= new ArrayList<ForestLodgeDTO>();
        try {

            String query = "select * from forest_lodge where power((latitude-?),2)+power((longitude-?),2)<?";

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

    // 관광지 위치로 검색
    public ArrayList<TouristSpotDTO> inquireTouristSpotByLocation(double selLatitude, double selLongitude, int range){
        ArrayList<TouristSpotDTO> dtos= new ArrayList<TouristSpotDTO>();
        try {
            String query = "select * from tourist_spot where power((latitude-?),2)+power((longitude-?),2)<?";

            conn= DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1,selLatitude);
            pstmt.setDouble(2,selLongitude);
            pstmt.setInt(3,range);
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