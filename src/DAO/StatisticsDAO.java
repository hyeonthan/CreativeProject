package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DBcontrol.DBconnection;
import DTO.DestinationDTO;
import DataSetControl.RegionList;

public class StatisticsDAO {
    private Connection conn;
    private PreparedStatement psmt;
    private ResultSet rs;
    private ResultSet rs2;
    

    //조회수 구분 없이 모두 가져오기
    public ArrayList<DestinationDTO> loadViewsStat(){
        String sql = "SELECT code, sortation, name, views, forest_lodge_code, beach_code, tourist_spot_code FROM destination WHERE views != 0";
        ArrayList<DestinationDTO> list = new ArrayList<DestinationDTO>();
        try{
            conn = DBconnection.getConnection();

            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while(rs.next()){
                String code = rs.getString("code");
                String sortation = rs.getString("sortation");
                String name = rs.getString("name");
                int views = rs.getInt("views");
                String forest_lodge_code = rs.getString("forest_lodge_code");
                String beach_code = rs.getString("beach_code");
                String tourist_spot_code = rs.getString("tourist_spot_code");
                DestinationDTO destinationDTO = new DestinationDTO(code, sortation, name, forest_lodge_code , beach_code, tourist_spot_code);
                destinationDTO.setViews(views);
                list.add(destinationDTO);
            }

        }catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
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
        return list;
    }
     //조회수 모두 가져오기
     public ArrayList<DestinationDTO> loadViewsStat(String sortation){
        String sql = "SELECT code, name, views, forest_lodge_code, beach_code, tourist_spot_code FROM destination WHERE sortation = ? AND views != 0";
        ArrayList<DestinationDTO> list = new ArrayList<DestinationDTO>();
        try{
            conn = DBconnection.getConnection();

            psmt = conn.prepareStatement(sql);
            psmt.setString(1, sortation);
            rs = psmt.executeQuery();
            while(rs.next()){
                String code = rs.getString("code");
                String name = rs.getString("name");
                int views = rs.getInt("views");
                String forest_lodge_code = rs.getString("forest_lodge_code");
                String beach_code = rs.getString("beach_code");
                String tourist_spot_code = rs.getString("tourist_spot_code");
                DestinationDTO destinationDTO = new DestinationDTO(code, sortation, name, forest_lodge_code , beach_code, tourist_spot_code);
                destinationDTO.setViews(views);
                list.add(destinationDTO);
            }

        }catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
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
        return list;
    }
    // 여행지 평점 구분 없이 모두 가져오기
    public ArrayList<DestinationDTO> loadScopeStat(){
        String sql = "SELECT code, sortation, name, scope, forest_lodge_code, beach_code, tourist_spot_code FROM destination WHERE scope != 0";
        ArrayList<DestinationDTO> list = new ArrayList<DestinationDTO>();
        try{
            conn = DBconnection.getConnection();

            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while(rs.next()){
                String code = rs.getString("code");
                String sortation = rs.getString("sortation");
                String name = rs.getString("name");
                double scope = rs.getDouble("scope");
                String forest_lodge_code = rs.getString("forest_lodge_code");
                String beach_code = rs.getString("beach_code");
                String tourist_spot_code = rs.getString("tourist_spot_code");
                DestinationDTO destinationDTO = new DestinationDTO(code, sortation, name, scope, forest_lodge_code , beach_code, tourist_spot_code);
                list.add(destinationDTO);
            }

        }catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
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
        return list;
    }
    //  여행지 평점 모두 가져오기
    public ArrayList<DestinationDTO> loadScopeStat(String sortation){
        String sql = "SELECT code, name, scope, forest_lodge_code, beach_code, tourist_spot_code FROM destination WHERE sortation = ? AND scope != 0";
        ArrayList<DestinationDTO> list = new ArrayList<DestinationDTO>();
        try{
            conn = DBconnection.getConnection();

            psmt = conn.prepareStatement(sql);
            psmt.setString(1, sortation);
            rs = psmt.executeQuery();
            while(rs.next()){
                String code = rs.getString("code");
                String name = rs.getString("name");
                double scope = rs.getDouble("scope");
                String forest_lodge_code = rs.getString("forest_lodge_code");
                String beach_code = rs.getString("beach_code");
                String tourist_spot_code = rs.getString("tourist_spot_code");
                DestinationDTO destinationDTO = new DestinationDTO(code, sortation, name, scope,  forest_lodge_code , beach_code, tourist_spot_code);
                list.add(destinationDTO);
            }
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
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
        return list;
    }

    //  여행지별 사용자 출신지 통계
    public ArrayList<DestinationDTO> birthRegionStat(){
        //  hashMap을 사용하여 지역 구분
        final String[] region = RegionList.Do;
        HashMap<String, Integer> hsMap = new HashMap<String, Integer>();
        for(int i = 0; i < region.length; i++){
            hsMap.put(region[i], 0);
        }
        //  화면에 출력할 리스트
        ArrayList<DestinationDTO> list = new ArrayList<DestinationDTO>();

        String sql1 = "SELECT code, sortation, name, forest_lodge_code, beach_code, tourist_spot_code FROM destination WHERE scope != 0";
        Connection conn2 = null;
        PreparedStatement psmt2 = null;
        ResultSet rs2 = null;
        try{
            conn = DBconnection.getConnection();
            psmt = conn.prepareStatement(sql1);
            rs = psmt.executeQuery();
            while(rs.next()){
                String code = rs.getString("code");
                String sortation = rs.getString("sortation");
                String name = rs.getString("name");
                String forest_lodge_code = rs.getString("forest_lodge_code");
                String beach_code = rs.getString("beach_code");
                String tourist_spot_code = rs.getString("tourist_spot_code");
                String sql2 = "select do from user WHERE id IN (select user_id from review where destination_code = ?)";
              
                conn2 = DBconnection.getConnection();
                psmt2 = conn.prepareStatement(sql2);
                psmt2.setString(1, code);
                rs2 = psmt2.executeQuery();
                while(rs2.next()){
                    String Do = rs2.getString("do");
                    Integer value = hsMap.get(Do);
                    //  출신지 count : cnt + 1
                    int cnt = value.intValue() + 1;
                    hsMap.put(Do, cnt);
                }
                //  출신지 count max 구하기
                int max = 0; String maxRegion = "";
                for(int i = 0; i < region.length; i++){
                    if(max < hsMap.get(region[i])){
                        max = hsMap.get(region[i]).intValue();
                        maxRegion = region[i];
                    }
                }
                DestinationDTO destinationDTO = new DestinationDTO(code, sortation, name, maxRegion, forest_lodge_code , beach_code, tourist_spot_code);
                list.add(destinationDTO);
                // rs2.close();
                // psmt2.close();
                // conn2.close();
            }
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                if(rs2 != null){
                    rs2.close();
                }
                if (psmt2 != null) {
                    psmt2.close();
                }
                if(conn2 != null){
                    conn2.close();
                }
                if(rs != null){
                    rs.close();
                }
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
        return list;
    }
     //  여행지별 사용자 출신지 통계(sortation)
     public ArrayList<DestinationDTO> birthRegionStat(String sortation){
        //  hashMap을 사용하여 지역 구분
        final String[] region = RegionList.Do;
        HashMap<String, Integer> hsMap = new HashMap<String, Integer>();
        for(int i = 0; i < region.length; i++){
            hsMap.put(region[i], 0);
        }
        //  화면에 출력할 리스트
        ArrayList<DestinationDTO> list = new ArrayList<DestinationDTO>();

        String sql1 = "SELECT code, name, forest_lodge_code, beach_code, tourist_spot_code FROM destination WHERE scope != 0 AND sortation = ?";
        Connection conn2 = null;
        PreparedStatement psmt2 = null;
        ResultSet rs2 = null;
        try{
            conn = DBconnection.getConnection();
            psmt = conn.prepareStatement(sql1);
            psmt.setString(1, sortation);
            rs = psmt.executeQuery();
            while(rs.next()){
                String code = rs.getString("code");
                String name = rs.getString("name");
                String forest_lodge_code = rs.getString("forest_lodge_code");
                String beach_code = rs.getString("beach_code");
                String tourist_spot_code = rs.getString("tourist_spot_code");
                String sql2 = "select do from user WHERE id IN (select user_id from review where destination_code = ?)";

                psmt2 = conn.prepareStatement(sql2);
                psmt2.setString(1, code);
                rs2 = psmt2.executeQuery();
                while(rs2.next()){
                    String Do = rs2.getString("do");
                    Integer value = hsMap.get(Do);
                    //  출신지 count : cnt + 1
                    int cnt = value.intValue() + 1;
                    hsMap.put(Do, cnt);
                }
                //  출신지 count max 구하기
                int max = 0; String maxRegion = "";
                for(int i = 0; i < region.length; i++){
                    if(max < hsMap.get(region[i])){
                        max = hsMap.get(region[i]).intValue();
                        maxRegion = region[i];
                    }
                }
                DestinationDTO destinationDTO = new DestinationDTO(code, sortation, name, maxRegion, forest_lodge_code, beach_code, tourist_spot_code);
                list.add(destinationDTO);
                // rs2.close();
                // psmt2.close();
                // conn2.close();
            }
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                if(rs2 != null){
                    rs2.close();
                }
                if (psmt2 != null) {
                    psmt2.close();
                }
                if(conn2 != null){
                    conn2.close();
                }
                if(rs != null){
                    rs.close();
                }
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
        return list;
    }
    //  여행지 리뷰수 통계
    public ArrayList<DestinationDTO> reviewCntStat(){
        ArrayList<DestinationDTO> list = new ArrayList<DestinationDTO>();
        String sql = "SELECT code, name, sortation, forest_lodge_code, beach_code, tourist_spot_code FROM destination WHERE scope != 0";
        String sql2 = "SELECT COUNT(*) FROM review WHERE destination_code = ?";
     
        try{
            conn = DBconnection.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while(rs.next()){
                String code = rs.getString("code");
                System.out.println(code);
                String name = rs.getString("name");
                String sortation = rs.getString("sortation");
                String forest_lodge_code = rs.getString("forest_lodge_code");
                String beach_code = rs.getString("beach_code");
                String tourist_spot_code = rs.getString("tourist_spot_code");

                psmt = conn.prepareStatement(sql2);
                psmt.setString(1, code);
                rs2 = psmt.executeQuery();
                rs2.next();
                int count = rs2.getInt(1); // 임포스터
                DestinationDTO destinationDTO = new DestinationDTO(code, sortation, name, forest_lodge_code , beach_code, tourist_spot_code);
                destinationDTO.setCount(count);
                list.add(destinationDTO);
            }
            
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
                if(rs2!=null){
                    rs2.close();
                }
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
        return list;
    }
    //  여행지별 리뷰수 통계 (sortation)
    public ArrayList<DestinationDTO> reviewCntStat(String sortation){
        ArrayList<DestinationDTO> list = new ArrayList<DestinationDTO>();
        String sql = "SELECT code, name, forest_lodge_code, beach_code, tourist_spot_code FROM destination WHERE scope != 0 AND sortation = ?";
        String sql2 = "SELECT COUNT(*) FROM review WHERE destination_code = ?";
     
        try{
            conn = DBconnection.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, sortation);
            rs = psmt.executeQuery();
            while(rs.next()){
                String code = rs.getString("code");
                System.out.println(code);
                String name = rs.getString("name");
                String forest_lodge_code = rs.getString("forest_lodge_code");
                String beach_code = rs.getString("beach_code");
                String tourist_spot_code = rs.getString("tourist_spot_code");
                conn = DBconnection.getConnection();
                psmt = conn.prepareStatement(sql2);
                psmt.setString(1, code);
                rs2 = psmt.executeQuery(); // 진짜 한현택 임포스터
                rs2.next();
                int count = rs2.getInt(1);
                DestinationDTO destinationDTO = new DestinationDTO(code, sortation, name,  forest_lodge_code , beach_code, tourist_spot_code);
                destinationDTO.setCount(count);
                list.add(destinationDTO);
            }
            
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
                if (psmt != null) {
                    psmt.close();
                }
                if(rs2!=null){
                    rs2.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}


