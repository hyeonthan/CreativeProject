package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import DBcontrol.DBconnection;
import DTO.BeachDTO;
import DTO.FavoriteDTO;
import DTO.ForestLodgeDTO;
import DTO.ReviewDTO;
import DTO.TouristSpotDTO;
import DataSetControl.RegionList;

public class DetailDAO {
    private PreparedStatement pstmt;
    private Connection conn;
    private ResultSet rs;
    private Savepoint sp;

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

    // 리뷰 가져오기
    public ArrayList<ReviewDTO> inquireReview(String selDestination){
        ArrayList<ReviewDTO> dtos= new ArrayList<ReviewDTO>();
        try {
            String query = "select * from review where destination_code=?";

            conn= DBconnection.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,selDestination);
            rs = pstmt.executeQuery();

            while(rs.next()){
                int no = rs.getInt("no");
                String user_id= rs.getString("user_id");
                String content= rs.getString("content");
                int scope= rs.getInt("scope");
                Timestamp reporting_date = rs.getTimestamp("reporting_date");
                String destination_code =rs.getString("destination_code");
                String destination_name = rs.getString("destination_name");
                Timestamp modify_date = rs.getTimestamp("modify_date");
                byte[] image = rs.getBytes("image");

                ReviewDTO dto = new ReviewDTO(no,user_id, content,scope, destination_code, destination_name,modify_date, reporting_date, image);
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

    // 리뷰 추가
    public boolean insertReview(ReviewDTO dto){
        boolean check = false;
        try {
            String query = "insert into review(user_id, content, scope, reporting_date, destination_code, destination_name, modify_date, image) values(?,?,?,?,?,?,?,?)";
            String query2= "select sum(scope)/count(*) from review where destination_code =?";
            String query3 = "update destination set scope=? where code= ?";

            conn= DBconnection.getConnection();
            conn.setAutoCommit(false);
            sp = conn.setSavepoint("Savepoint1");

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,dto.getUser_id());
            pstmt.setString(2,dto.getContent());
            pstmt.setInt(3,dto.getScope());
            pstmt.setTimestamp(4, dto.getReporting_date());
            pstmt.setString(5,dto.getDestination_code());
            pstmt.setString(6,dto.getDestination_name());
            pstmt.setTimestamp(7,dto.getModify_date());
            pstmt.setBytes(8,dto.getImage());
            pstmt.executeUpdate();
            // pstmt.close();
            // conn.close();

            double scope=0;
            pstmt = conn.prepareStatement(query2, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1,dto.getDestination_code());
            rs= pstmt.executeQuery();
            rs.next();
            scope= rs.getDouble(1);
            // pstmt.close();
            // conn.close();

            pstmt = conn.prepareStatement(query3);
            pstmt.setDouble(1,scope);
            pstmt.setString(2,dto.getDestination_code());
            pstmt.executeUpdate();
            System.out.println("scope: " + scope);
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

    // 성별 통계 가져오기
    public String genderStatistic(String desCode){
        int manCnt=0;
        int womanCnt=0;
        try {
            String query = "select count(*) from review where destination_code = ? and user_id in (select id from user where gender = 'M')";
            String query2 = "select count(*) from review where destination_code = ? and user_id in (select id from user where gender = 'F')";

            conn= DBconnection.getConnection();

            pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1,desCode);
            rs = pstmt.executeQuery();
            rs.next();
            manCnt = rs.getInt(1);

            pstmt = conn.prepareStatement(query2, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1,desCode);
            rs = pstmt.executeQuery();
            rs.next();
            womanCnt = rs.getInt(1);

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
        return Integer.toString(manCnt)+"/"+Integer.toString(womanCnt);
    }

    // 지역별 통계 가져오기
    public HashMap<String,Integer> regionStatistic(String desCode){
        HashMap<String, Integer> hsMap = new HashMap<String, Integer>();
        try {
            final String[] region = RegionList.Do;
            for(int i = 0; i < region.length; i++){
                hsMap.put(region[i], 0);
            }

            String query = "select do from user WHERE id IN (select user_id from review where destination_code = ?)";
            conn= DBconnection.getConnection();

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,desCode);
            rs = pstmt.executeQuery();

            while(rs.next()){
                 hsMap.put(rs.getString("do"),hsMap.get(rs.getString("do"))+1);
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
        return hsMap;
    }

    // 나이별 통계 가져오기
    public HashMap<Integer,Integer> ageStatistic(String desCode){
        HashMap<Integer, Integer> hsMap = new HashMap<Integer, Integer>();
        try {
            final int[] age = {10,20, 30, 40, 50, 60};
            for(int i = 0; i < age.length; i++){
                hsMap.put(age[i], 0);
            }

            String query = "select age from user WHERE id IN (select user_id from review where destination_code = ?)";
            conn= DBconnection.getConnection();

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,desCode);
            rs = pstmt.executeQuery();

            while(rs.next()){
                switch (rs.getInt("age")/10){
                    case 0 :
                    case 1 :
                        hsMap.put(10,hsMap.get(10)+1);
                        break;
                    case 2 :
                        hsMap.put(20,hsMap.get(20)+1);
                        break;
                    case 3 :
                        hsMap.put(30,hsMap.get(30)+1);
                        break;
                    case 4 :
                        hsMap.put(40,hsMap.get(40)+1);
                        break;
                    case 5 :
                        hsMap.put(50,hsMap.get(50)+1);
                        break;
                    case 6 :
                    case 7:
                    case 8:
                    case 9:
                        hsMap.put(60,hsMap.get(60)+1);
                        break;
                }
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
        return hsMap;
    }
    //  조회수 증가
    public boolean viewsCountIncrease(String destinationCode){
        boolean check = false;
        try{
            String query = "UPDATE destination SET views = views+1 WHERE code = ?";

            conn = DBconnection.getConnection();
            conn.setAutoCommit(false);
            sp = conn.setSavepoint("Savepoint1");

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, destinationCode);
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
                e.printStackTrace();
            }
        }
        return check;
    }
      //  즐겨찾기 추가
      public boolean addFavorite(FavoriteDTO favoriteDTO){
        boolean check = false;
        String queryCheck = "select * from favorite where user_id =? and destination_code =?";
        String query = "INSERT INTO favorite(user_id, destination_code, destination_name, add_date, sortation) VALUES(?,?,?,?,?)";
        try{
            conn = DBconnection.getConnection();
            conn.setAutoCommit(false);
            sp = conn.setSavepoint("Savepoint1");

            pstmt=conn.prepareStatement(queryCheck, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1,favoriteDTO.getUser_id());
            pstmt.setString(2, favoriteDTO.getDestination_code());
            rs = pstmt.executeQuery();
            rs.last();
            if(rs.getRow() == 0) 	// 중복 ID 없을 시 false
            {
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, favoriteDTO.getUser_id());
                pstmt.setString(2, favoriteDTO.getDestination_code());
                pstmt.setString(3, favoriteDTO.getDestination_name());
                pstmt.setTimestamp(4,favoriteDTO.getAdd_date());
                pstmt.setString(5, favoriteDTO.getSortation());

                pstmt.executeUpdate();
                conn.commit();
                check = true;
            }

        }catch (SQLException sqlException) {
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
                if(rs!=null){
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return check;
    }
}
