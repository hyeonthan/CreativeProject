package DataSetControl;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import DBcontrol.DBconnection;
import DTO.BeachDTO;
import DTO.DestinationDTO;
import DTO.ForestLodgeDTO;
import DTO.ParkingLotsDTO;
import DTO.ToiletDTO;
import DTO.TouristSpotDTO;
public class ExcelToDB {
    private static PreparedStatement psmt;
    private static Connection conn;
    ExcelToDB(){
        psmt = null;
        conn = null;
    }
    public static void insertBeach(ArrayList<BeachDTO> list){
        String SQL = "INSERT INTO BEACH VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        conn = DBconnection.getConnection();
        
        Iterator<BeachDTO> it = list.iterator();
        BeachDTO beachDTO;
        try{
            psmt = conn.prepareStatement(SQL);
        while(it.hasNext()){
                beachDTO = it.next();
                psmt.setString(1,beachDTO.getCode());
                psmt.setString(2, beachDTO.getName());
                psmt.setString(3, beachDTO.getIntroduction());
                psmt.setString(4, beachDTO.getDo());
                psmt.setString(5, beachDTO.getCity());
                psmt.setString(6, beachDTO.getAddress());
                psmt.setDouble(7, beachDTO.getLatitude());
                psmt.setDouble(8, beachDTO.getLongitude());
                psmt.setString(9, beachDTO.getPhone_num());
                psmt.setString(10, beachDTO.getHome_page());
                psmt.setString(11, beachDTO.getOpening_period());
                psmt.setString(12, beachDTO.getAvailable_time());
                psmt.setString(13, beachDTO.getAmenities());
            
                psmt.executeUpdate();
                //conn.commit();
            }
        }catch (SQLException sqle) {
            sqle.printStackTrace();
       }finally {
           try {
               conn.setAutoCommit(true); 
               if (psmt != null) {
                psmt.close();
               }				
               if (conn != null) {
                   conn.close();
               }
           } catch (Exception e) {
               throw new RuntimeException(e.getMessage());
           }
       }
    }
    public static void insertForestLodge(ArrayList<ForestLodgeDTO> list){
        String SQL = "INSERT INTO forest_lodge VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        conn = DBconnection.getConnection();
        
        Iterator<ForestLodgeDTO> it = list.iterator();
        ForestLodgeDTO forestLodgeDTO;
        try{
            psmt = conn.prepareStatement(SQL);
        while(it.hasNext()){
                forestLodgeDTO = it.next();
                psmt.setString(1,forestLodgeDTO.getCode());
                psmt.setString(2, forestLodgeDTO.getName());
                psmt.setString(3, forestLodgeDTO.getDo());
                psmt.setString(4, forestLodgeDTO.getCity());
                psmt.setString(5, forestLodgeDTO.getAddress());
                psmt.setString(6, forestLodgeDTO.getPhone_num());
                psmt.setDouble(7, forestLodgeDTO.getLatitude());
                psmt.setDouble(8, forestLodgeDTO.getLongitude());
                psmt.setString(9, forestLodgeDTO.getAmenities());
                psmt.setString(10, forestLodgeDTO.getCapacity_people());
                psmt.setString(11, forestLodgeDTO.getEnter_fee());
                psmt.setString(12, forestLodgeDTO.getAccommodation());
                psmt.setString(13, forestLodgeDTO.getHome_page());

                psmt.executeUpdate();
            }
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }finally {
           try {
               conn.setAutoCommit(true); 
               if (psmt != null) {
                psmt.close();
               }				
               if (conn != null) {
                   conn.close();
               }
           } catch (Exception e) {
               throw new RuntimeException(e.getMessage());
           }
       }
    }
    public static void insertTouristSpot(ArrayList<TouristSpotDTO> list){
        String SQL = "INSERT INTO tourist_spot VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        conn = DBconnection.getConnection();
        
        Iterator<TouristSpotDTO> it = list.iterator();
        TouristSpotDTO touristSpotDTO;
        try{
            psmt = conn.prepareStatement(SQL);
        while(it.hasNext()){
            touristSpotDTO = it.next();
                psmt.setString(1, touristSpotDTO.getCode());
                psmt.setString(2, touristSpotDTO.getName());
                psmt.setString(3, touristSpotDTO.getIntroduction());
                psmt.setString(4, touristSpotDTO.getPhone_num());
                psmt.setString(5, touristSpotDTO.getDo());
                psmt.setString(6, touristSpotDTO.getCity());
                psmt.setString(7, touristSpotDTO.getAddress());
                psmt.setDouble(8, touristSpotDTO.getLatitude());
                psmt.setDouble(9, touristSpotDTO.getLongitude());
                psmt.setString(10, touristSpotDTO.getAmenities());
                psmt.setInt(11, touristSpotDTO.getPossibleParking());
                psmt.setString(12, touristSpotDTO.getManagementAgency());

                psmt.executeUpdate();
            }
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }finally {
           try {
               conn.setAutoCommit(true); 
               if (psmt != null) {
                psmt.close();
               }				
               if (conn != null) {
                   conn.close();
               }
           } catch (Exception e) {
               throw new RuntimeException(e.getMessage());
           }
       }
    }
    public static void insertParkingLots(ArrayList<ParkingLotsDTO> list){
        String SQL = "INSERT INTO parking_lot VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        conn = DBconnection.getConnection();
        
        Iterator<ParkingLotsDTO> it = list.iterator();
        ParkingLotsDTO parkingLotsDTO;
        try{
            psmt = conn.prepareStatement(SQL);
        while(it.hasNext()){
            parkingLotsDTO = it.next();
                psmt.setString(1, parkingLotsDTO.getCode());
                psmt.setString(2, parkingLotsDTO.getName());
                psmt.setString(3, parkingLotsDTO.getDo());
                psmt.setString(4, parkingLotsDTO.getCity());
                psmt.setString(5, parkingLotsDTO.getAddress());
                psmt.setInt(6, parkingLotsDTO.getSize());
                psmt.setString(7, parkingLotsDTO.getOpening_days());
                psmt.setString(8, parkingLotsDTO.getWeekdays_opening_time());
                psmt.setString(9, parkingLotsDTO.getSat_opening_time());
                psmt.setString(10, parkingLotsDTO.getHoliday_opening_time());
                psmt.setString(11, parkingLotsDTO.getFee());
                psmt.setString(12, parkingLotsDTO.getPhone_num());
                psmt.setDouble(13, parkingLotsDTO.getLatitude());
                psmt.setDouble(14, parkingLotsDTO.getLongitude());
                
                psmt.executeUpdate();
            }
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }finally {
           try {
               conn.setAutoCommit(true); 
               if (psmt != null) {
                psmt.close();
               }				
               if (conn != null) {
                   conn.close();
               }
           } catch (Exception e) {
               throw new RuntimeException(e.getMessage());
           }
       }
    }
    public static void insertToilet(ArrayList<ToiletDTO> list){
        String SQL = "INSERT INTO toilet VALUES(?,?,?,?,?,?,?,?,?,?)";
        conn = DBconnection.getConnection();
        Iterator<ToiletDTO> it = list.iterator();
        ToiletDTO toiletDTO;
        try{
            psmt = conn.prepareStatement(SQL);
        while(it.hasNext()){
                toiletDTO = it.next();
                psmt.setString(1, toiletDTO.getCode());
                psmt.setString(2, toiletDTO.getName());
                psmt.setString(3, toiletDTO.getDo());
                psmt.setString(4, toiletDTO.getCity());
                psmt.setString(5, toiletDTO.getAddress());
                psmt.setString(6, toiletDTO.getPublic());
                psmt.setString(7, toiletDTO.getOpening_time());
                psmt.setDouble(8, toiletDTO.getLatitude());
                psmt.setDouble(9, toiletDTO.getLongitude());
                psmt.setString(10, toiletDTO.getManagementAgency());

                psmt.executeUpdate();
            }
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }finally {
           try {
               conn.setAutoCommit(true); 
               if (psmt != null) {
                psmt.close();
               }				
               if (conn != null) {
                   conn.close();
               }
           } catch (Exception e) {
               throw new RuntimeException(e.getMessage());
           }
       }
    }
    public static void insertDestination(ArrayList<DestinationDTO> list){
        String SQL = "INSERT INTO destination VALUES(?,?,?,?,?,?,?,?,?,?)";
        conn = DBconnection.getConnection();
        Iterator<DestinationDTO> it = list.iterator();
        DestinationDTO destinationDTO;
        try{
            psmt = conn.prepareStatement(SQL);
        while(it.hasNext()){
                destinationDTO = it.next();
                psmt.setString(1, destinationDTO.getCode());
                psmt.setString(2, destinationDTO.getSortation());
                psmt.setString(3, destinationDTO.getForestLodge_code());
                psmt.setString(4, destinationDTO.getBeach_code());
                psmt.setString(5, destinationDTO.getTouristSpot_code());
                psmt.setString(6, destinationDTO.getName());
                psmt.setString(7, destinationDTO.getDo());
                psmt.setString(8, destinationDTO.getCity());
                psmt.setString(9, destinationDTO.getAddress());
                psmt.setDouble(10, destinationDTO.getScope());

                psmt.executeUpdate();
            }
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }finally {
           try {
               conn.setAutoCommit(true); 
               if (psmt != null) {
                psmt.close();
               }				
               if (conn != null) {
                   conn.close();
               }
           } catch (Exception e) {
               throw new RuntimeException(e.getMessage());
           }
       }
    }
    
    public static void testImage2(){
        String SQL = "INSERT INTO review(user_id, content, scope, reporting_date, destination_code, destination_name, modify_date, image) VALUES(?,?,?,?,?,?,?,?)";
        try{
            conn = DBconnection.getConnection();
            psmt = conn.prepareStatement(SQL);
            File imgfile = new File("C:\\Users\\ehskf\\OneDrive\\바탕 화면\\KakaoTalk_20210509_125649868.jpg");
            FileInputStream fin = new FileInputStream(imgfile);
            psmt.setString(1, null);
            psmt.setString(2, "애인이랑 왔는데 여기서 헤어지재요");
            psmt.setInt(3, 1);
            psmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));;
            psmt.setString(5, "dt0001");
            psmt.setString(6, "광안리 해수욕장");
            psmt.setTimestamp(7, null);
            psmt.setBinaryStream(8, fin, (int)imgfile.length());

            psmt.executeUpdate();
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                conn.setAutoCommit(true); 
                if (psmt != null) {
                 psmt.close();
                }				
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
