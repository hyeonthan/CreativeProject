package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DBcontrol.DBconnection;
import DTO.BeachDTO;
import DTO.DestinationDTO;

public class InquireByLocationDAO {
    private PreparedStatement pstmt;
    private Connection conn;
    private ResultSet rs;

    //구분 선택없이 위치으로만 가져오기
    public ArrayList<DestinationDTO> inquireDestinationByLocation(String selLatitude, String selLongitude, String range) {
        ArrayList<DestinationDTO> dtos = new ArrayList<DestinationDTO>();
        try {
            String query = "select * from destination where beach_code in (select code from beach where 6371*acos(cos(radians("+selLatitude+"))*cos(radians(latitude))*cos(radians(longitude)-radians("+selLongitude+"))+sin(radians("+selLatitude+"))*sin(radians(latitude))) <="+range+") or forest_lodge_code in (select code from forest_lodge where 6371*acos(cos(radians("+selLatitude+"))*cos(radians(latitude))*cos(radians(longitude)-radians("+selLongitude+"))+sin(radians("+selLatitude+"))*sin(radians(latitude))) <="+range+") or tourist_spot_code in (select code from tourist_spot where 6371*acos(cos(radians("+selLatitude+"))*cos(radians(latitude))*cos(radians(longitude)-radians("+selLongitude+"))+sin(radians("+selLatitude+"))*sin(radians(latitude))) <="+range+")";
            conn = DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) { 
                String code = rs.getString("code");
                String sortation = rs.getString("sortation");
                String forest_lodge_code = rs.getString("forest_lodge_code");
                String beach_code = rs.getString("beach_code");
                String tourist_spot_code = rs.getString("tourist_spot_code");
                String name = rs.getString("name");
                String Do = rs.getString("do");
                String city = rs.getString("city");
                String address = rs.getString("address");
                double scope = rs.getDouble("scope");
                int views = rs.getInt("views");
                

                DestinationDTO dto = new DestinationDTO(code, sortation, forest_lodge_code, beach_code, tourist_spot_code, name, Do, city, address, scope, views);
                dtos.add(dto);
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
        return dtos;
    }

    // 해수욕장 위치으로 검색
    public ArrayList<DestinationDTO> inquireBeachByLocation(String selLatitude, String selLongitude, String range) {
        ArrayList<DestinationDTO> dtos = new ArrayList<DestinationDTO>();
        try {
            String query = "select * from destination where beach_code in (select code from beach where 6371*acos(cos(radians("+selLatitude+"))*cos(radians(latitude))*cos(radians(longitude)-radians("+selLongitude+"))+sin(radians("+selLatitude+"))*sin(radians(latitude))) <="+range+")";

            conn = DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String code = rs.getString("code");
                String sortation = rs.getString("sortation");
                String forest_lodge_code = rs.getString("forest_lodge_code");
                String beach_code = rs.getString("beach_code");
                String tourist_spot_code = rs.getString("tourist_spot_code");
                String name = rs.getString("name");
                String Do = rs.getString("do");
                String city = rs.getString("city");
                String address = rs.getString("address");
                double scope = rs.getDouble("scope");
                int views = rs.getInt("views");
                DestinationDTO dto = new DestinationDTO(code, sortation, forest_lodge_code, beach_code, tourist_spot_code, name, Do, city, address, scope, views);
                dtos.add(dto);
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
        return dtos;
    }

    // 휴양림 위치으로 검색
    public ArrayList<DestinationDTO> inquireForestLodgeByLocation(String selLatitude, String selLongitude, String range) {
        ArrayList<DestinationDTO> dtos = new ArrayList<DestinationDTO>();
        try {

            String query = "select * from destination where forest_lodge_code in (select code from forest_lodge where 6371*acos(cos(radians("+selLatitude+"))*cos(radians(latitude))*cos(radians(longitude)-radians("+selLongitude+"))+sin(radians("+selLatitude+"))*sin(radians(latitude))) <="+range+")";

            conn = DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String code = rs.getString("code");
                String sortation = rs.getString("sortation");
                String forest_lodge_code = rs.getString("forest_lodge_code");
                String beach_code = rs.getString("beach_code");
                String tourist_spot_code = rs.getString("tourist_spot_code");
                String name = rs.getString("name");
                String Do = rs.getString("do");
                String city = rs.getString("city");
                String address = rs.getString("address");
                double scope = rs.getDouble("scope");
                int views = rs.getInt("views");

                DestinationDTO dto = new DestinationDTO(code, sortation, forest_lodge_code, beach_code, tourist_spot_code, name, Do, city, address, scope, views);
                dtos.add(dto);
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
        return dtos;
    }

    // 관광지 위치로 검색
    public ArrayList<DestinationDTO> inquireTouristSpotByLocation(String selLatitude, String selLongitude, String range) {
        ArrayList<DestinationDTO> dtos = new ArrayList<DestinationDTO>();
        try {
            String query = "select * from destination where tourist_spot_code in (select code from tourist_spot where 6371*acos(cos(radians("+selLatitude+"))*cos(radians(latitude))*cos(radians(longitude)-radians("+selLongitude+"))+sin(radians("+selLatitude+"))*sin(radians(latitude))) <="+range+")";

            conn = DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String code = rs.getString("code");
                String sortation = rs.getString("sortation");
                String forest_lodge_code = rs.getString("forest_lodge_code");
                String beach_code = rs.getString("beach_code");
                String tourist_spot_code = rs.getString("tourist_spot_code");
                String name = rs.getString("name");
                String Do = rs.getString("do");
                String city = rs.getString("city");
                String address = rs.getString("address");
                double scope = rs.getDouble("scope");
                int views = rs.getInt("views");

                DestinationDTO dto = new DestinationDTO(code, sortation, forest_lodge_code, beach_code, tourist_spot_code, name, Do, city, address, scope, views);
                dtos.add(dto);
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
        return dtos;
    }
    //  통합검색 이름 위도 경도 가져오기
    public HashMap<String, String> inquireAllLatLng(String selLatitude, String selLongitude, String range){
        String queryBeach = "SELECT *,(6371*acos(cos(radians(?))*cos(radians(latitude))*cos(radians(longitude)-radians(?))+sin(radians(?))*sin(radians(latitude)))) AS distance FROM beach  HAVING distance <=?";
        String queryFoestLodge = "SELECT *,(6371*acos(cos(radians(?))*cos(radians(latitude))*cos(radians(longitude)-radians(?))+sin(radians(?))*sin(radians(latitude)))) AS distance FROM forest_lodge  HAVING distance <=?";
        String queryTouristSpot = "SELECT *,(6371*acos(cos(radians(?))*cos(radians(latitude))*cos(radians(longitude)-radians(?))+sin(radians(?))*sin(radians(latitude)))) AS distance FROM tourist_spot  HAVING distance <=?";
        HashMap<String, String> hsMap = new HashMap<String, String>();
        try{
            String name = "", latitude ="", longitude = "";
            int cnt = 0;
            conn = DBconnection.getConnection();
                
            pstmt = conn.prepareStatement(queryBeach);
            pstmt.setString(1, selLatitude);
            pstmt.setString(2, selLongitude);
            pstmt.setString(3, selLatitude);
            pstmt.setString(4, range);
            rs = pstmt.executeQuery();
            while(rs.next()){
                name += rs.getString("name") + " ";
                latitude += Double.toString(rs.getDouble("latitude")) + " ";
                longitude += Double.toString(rs.getDouble("longitude")) + " ";
                cnt++;
            }
            pstmt = conn.prepareStatement(queryFoestLodge);
            pstmt.setString(1, selLatitude);
            pstmt.setString(2, selLongitude);
            pstmt.setString(3, selLatitude);
            pstmt.setString(4, range);
            rs = pstmt.executeQuery();
            while(rs.next()){
                name += rs.getString("name") + " ";
                latitude += Double.toString(rs.getDouble("latitude")) + " ";
                longitude += Double.toString(rs.getDouble("longitude")) + " ";
                cnt++;
            }
            pstmt = conn.prepareStatement(queryTouristSpot);
            pstmt.setString(1, selLatitude);
            pstmt.setString(2, selLongitude);
            pstmt.setString(3, selLatitude);
            pstmt.setString(4, range);
            rs = pstmt.executeQuery();
            while(rs.next()){
                name += rs.getString("name") + " ";
                latitude += Double.toString(rs.getDouble("latitude")) + " ";
                longitude += Double.toString(rs.getDouble("longitude")) + " ";
                cnt++;
            }
            hsMap.put("이름", name);
            hsMap.put("위도", latitude);
            hsMap.put("경도", longitude);
            hsMap.put("INDEX", Integer.toString(cnt));
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
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
        return hsMap;
    }
     //  구분 선택 후 이름 위도 경도 가져오기
     public HashMap<String, String> inquireLatLng(String sortation, String selLatitude, String selLongitude, String range){
        String query = "";
        HashMap<String, String> hsMap = new HashMap<String, String>();
        if(sortation.equals("해수욕장")){
            query = "SELECT *,(6371*acos(cos(radians(?))*cos(radians(latitude))*cos(radians(longitude)-radians(?))+sin(radians(?))*sin(radians(latitude)))) AS distance FROM beach  HAVING distance <=?";
        }
        else if (sortation.equals("휴양림")){
            query = "SELECT *,(6371*acos(cos(radians(?))*cos(radians(latitude))*cos(radians(longitude)-radians(?))+sin(radians(?))*sin(radians(latitude)))) AS distance FROM forest_lodge  HAVING distance <=?";
        }
        else if (sortation.equals("관광지")){
            query = "SELECT *,(6371*acos(cos(radians(?))*cos(radians(latitude))*cos(radians(longitude)-radians(?))+sin(radians(?))*sin(radians(latitude)))) AS distance FROM tourist_spot  HAVING distance <=?";
        }
        try{
            String name = "", latitude ="", longitude = "";
            int cnt = 0;
            conn = DBconnection.getConnection();
                
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, selLatitude);
            pstmt.setString(2, selLongitude);
            pstmt.setString(3, selLatitude);
            pstmt.setString(4, range);
            rs = pstmt.executeQuery();
            while(rs.next()){
                name += rs.getString("name") + " ";
                latitude += Double.toString(rs.getDouble("latitude")) + " ";
                longitude += Double.toString(rs.getDouble("longitude")) + " ";
                cnt++;
            }
            
            hsMap.put("이름", name);
            hsMap.put("위도", latitude);
            hsMap.put("경도", longitude);
            hsMap.put("INDEX", Integer.toString(cnt));
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
                if(rs != null){
                    rs.close();
                }
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
        return hsMap;
    }
}
