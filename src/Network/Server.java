package Network;

import DAO.*;
import DBcontrol.DBconnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DTO.*;


public class Server extends Thread{
    Socket socket;
    private static int curUser = 0;
    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;
    ObjectInputStream objectInputStream = null;
    ObjectOutputStream objectOutputStream = null;

    public Server(Socket socket) throws ClassNotFoundException, SQLException {
        this.socket = socket;
        DBconnection.getConnection();
    }

    @Override
    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

//            writePacket(Protocol.PT_REQ_LOGIN_INFO);
            boolean program_stop = false;
            String id =null;

            while(true) {
                String packet = bufferedReader.readLine();
                String packetArr[] = packet.split("`");
                String packetType = packetArr[0]; // 프로토콜 타입 구분

                switch (packetType) {
                    case Protocol.PT_EXIT:{
                        writePacket(Protocol.PT_EXIT);
                        program_stop = true;
                        break;
                    }
                    case Protocol.PT_REQ_LOGIN:{
                    	String loginId = packetArr[1];
                    	String loginPassword = packetArr[2];
                    	
                    	try {
                    		UserDAO userDao = new UserDAO();
                    		boolean isCorrectUser = userDao.checkUser(loginId,  loginPassword);
                    		if (isCorrectUser) {
								writePacket(Protocol.PT_RES_LOGIN + "`" + Protocol.RES_LOGIN_Y);
								id = loginId;
                    		}
                    		else
                    			writePacket(Protocol.PT_RES_LOGIN + "`" + Protocol.RES_LOGIN_N);
                    	} catch (Exception e) {
                    		e.printStackTrace();
                    	}
                    	break;
                    }
                    case Protocol.PT_REQ_VIEW: {	//조회 요청
                    	String packetCode = packetArr[1];
                    	switch (packetCode) {
                    		case Protocol.REQ_DESTINATION_REGION:{	//지역으로 전체 검색
								InquireByRegionDAO inquireByRegionDAO = new InquireByRegionDAO();
								ArrayList<DestinationDTO> destinationDTOS = inquireByRegionDAO.inquireDestinationByRegion(packetArr[2],packetArr[3],packetArr[4]);
								
								if(destinationDTOS != null){
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_DESTINATION_REGION_Y);
									writeObject(destinationDTOS);
								}
								else{
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_DESTINATION_REGION_N);
								}
                    			break;
                    		}
                    		case Protocol.REQ_TOURIST_DETAIL:{	//관광지 상세정보
								DetailDAO detailDAO = new DetailDAO();
								DestinationDTO destinationDTO = (DestinationDTO) objectInputStream.readObject();

								TouristSpotDTO touristSpotDTO = detailDAO.detailTouristSpot(destinationDTO.getTouristSpot_code());
								ArrayList<ReviewDTO> arrayList = detailDAO.inquireReview(destinationDTO.getCode());

								if (touristSpotDTO != null) {
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_TOURIST_DETAIL_Y);
									writeObject(touristSpotDTO);
									writeObject(arrayList);
								}
								else
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_TOURIST_DETAIL_N);
                    			break;
                    		}
                    		case Protocol.REQ_FOREST_DETAIL:{		// 휴양림 상세정보
								DetailDAO detailDAO = new DetailDAO();
								DestinationDTO destinationDTO = (DestinationDTO) objectInputStream.readObject();

								ForestLodgeDTO forestLodgeDTO = detailDAO.detailForestLodge(destinationDTO.getForestLodge_code());
								ArrayList<ReviewDTO> arrayList = detailDAO.inquireReview(destinationDTO.getCode());

								if (forestLodgeDTO!=null) {
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_FOREST_DETAIL_Y);
									writeObject(forestLodgeDTO);
									writeObject(arrayList);
								}
								else
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_FOREST_DETAIL_N);
                    			break;
                    		}
                    		case Protocol.REQ_BEACH_DETAIL:{		//해수욕장 상세정보
								DetailDAO detailDAO = new DetailDAO();
								DestinationDTO destinationDTO = (DestinationDTO) objectInputStream.readObject();

								ForestLodgeDTO beachDTO = detailDAO.detailForestLodge(destinationDTO.getBeach_code());
								ArrayList<ReviewDTO> arrayList = detailDAO.inquireReview(destinationDTO.getCode());

								if (beachDTO!=null) {
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_BEACH_DETAIL_Y);
									writeObject(beachDTO);
									writeObject(arrayList);
								}
								else
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_BEACH_DETAIL_N);
                    			break;
                    		}
                    		case Protocol.REQ_TOILET:{		//화장실 정보 요청
								InquireToiletParkingDAO inquireToiletParkingDAO = new InquireToiletParkingDAO();
                    			ArrayList<ToiletDTO> toiletDTOS = inquireToiletParkingDAO.inquireToiletByLocation(packetArr[2],packetArr[3],packetArr[4]);

								if(toiletDTOS!=null) {
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_TOILET_Y);
									writeObject(toiletDTOS);
								}
								else{
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_TOILET_N);
								}
                    			break;
                    		}
                    		case Protocol.REQ_PARKING:{		//주차장 정보 요청
                    			InquireToiletParkingDAO inquireToiletParkingDAO = new InquireToiletParkingDAO();
                    			ArrayList<ParkingLotsDTO> parkingLotsDTOS = inquireToiletParkingDAO.inquireParkingLotByLocation(packetArr[1],packetArr[2],packetArr[3]);

								if(parkingLotsDTOS!=null) {
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_PARKING_Y);
									writeObject(parkingLotsDTOS);
								}
								else{
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_PARKING_N);
								}
                    			break;
                    		}
                    		case Protocol.REQ_DESTINATION_LOCATION:{	//위치로 검색
                    			InquireByLocationDAO inquireByLocationDAO = new InquireByLocationDAO();
                    			ArrayList<DestinationDTO> arrayList = null;

                    			switch(packetArr[2]) {
									case " ":
										arrayList = inquireByLocationDAO.inquireDestinationByLocation(packetArr[3], packetArr[4], packetArr[5]);
										break;
									case"해수욕장":
										arrayList = inquireByLocationDAO.inquireBeachByLocation(packetArr[3], packetArr[4], packetArr[5]);
										break;
									case"휴양림":
										arrayList = inquireByLocationDAO.inquireForestLodgeByLocation(packetArr[3], packetArr[4], packetArr[5]);
										break;
									case"관광지":
										arrayList = inquireByLocationDAO.inquireTouristSpotByLocation(packetArr[3], packetArr[4], packetArr[5]);
										break;
								}
								
								if(arrayList != null){
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_DESTINATION_LOCATION_Y);
									writeObject(arrayList);
								}
								else{
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_DESTINATION_LOCATION_N);
								}
                    			break;
                    		}
                    		case Protocol.REQ_STATISTICS:{	//통계페이지 요청
                    			StatisticsDAO statisticsDAO = new StatisticsDAO();
                    			ArrayList<DestinationDTO> arrayList = null;
								if(packetArr[2].equals(" ")){
									switch (packetArr[3]) {
										case "조회수":
											arrayList = statisticsDAO.loadViewsStat();
											break;
										case "별점":
											arrayList = statisticsDAO.loadScopeStat();
											break;
										case "출신지":
											arrayList=statisticsDAO.birthRegionStat();
											break;
										case"리뷰수":
											arrayList =statisticsDAO.reviewCntStat();
											break;
									}
								}
								else{
									switch (packetArr[3]) {
										case "조회수":
											arrayList = statisticsDAO.loadViewsStat(packetArr[2]);
											break;
										case "별점":
											arrayList = statisticsDAO.loadScopeStat(packetArr[2]);
											break;
										case "출신지":
											arrayList=statisticsDAO.birthRegionStat(packetArr[2]);
											break;
										case"리뷰수":
											arrayList =statisticsDAO.reviewCntStat(packetArr[2]);
											break;
									}
								}

								if(arrayList!=null){
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_STATISTICS_Y);
									writeObject(arrayList);
								}
								else{
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_STATISTICS_N);
								}
                    			break;
                    		}
                    		case Protocol.REQ_MYPAGE:{ //마이페이지 요청
                    			MyPageDAO myPageDAO = new MyPageDAO();
                    			UserDTO userDTO = myPageDAO.roadUser(id);
								ArrayList<ReviewDTO> arrayList = myPageDAO.inquireMyReview(id);
								ArrayList<FavoriteDTO> arrayList1 = myPageDAO.inquiryFavorite(id);

								if(userDTO != null){
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_MYPAGE_Y);
									writeObject(userDTO);
									writeObject(arrayList);
									writeObject(arrayList1);
								}
								else{
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_MYPAGE_N);
								}

                    			break;
                    		}
							case Protocol.REQ_ID_DUPLICATION:{		//아이디 중복 체크 요청
								UserDAO userDAO = new UserDAO();
								boolean check = userDAO.duplicationId(packetArr[2]);

								if(!check){
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_ID_DUPLICATION_Y);
								}
								else{
									writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_ID_DUPLICATION_N);
								}
								break;
							}
							case Protocol.REQ_STATISTICS_DETAIL:{
								DetailDAO detailDAO = new DetailDAO();
								switch(packetArr[2]){
									case "출신지":
										HashMap<String,Integer> hashMap = detailDAO.regionStatistic(packetArr[3]);
										if(hashMap!=null){
											writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_STATISTICS_DETAIL_Y);
											writeObject(hashMap);
										}
										else{
											writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_STATISTICS_DETAIL_N);
										}
										break;
									case"성별":
										String s = detailDAO.genderStatistic(packetArr[3]);
										if(s!=null){
											writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_STATISTICS_DETAIL_Y);
											writePacket(s);
										}
										else{
											writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_STATISTICS_DETAIL_N);
										}
										break;
									case "나이별":
										HashMap<Integer,Integer> hashMap1 = detailDAO.ageStatistic(packetArr[3]);
										if(hashMap1!=null){
											writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_STATISTICS_DETAIL_Y);
											writeObject(hashMap1);
										}
										else{
											writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_STATISTICS_DETAIL_N);
										}
										break;
								}
							}
                    	}
                    	break;
                    }
                    case Protocol.PT_REQ_RENEWAL:{
                    	String packetCode = packetArr[1];
                    	switch (packetCode) {
                    		case Protocol.REQ_SIGNUP:{
                    			UserDAO userDAO = new UserDAO();
                    			UserDTO userDTO = (UserDTO)objectInputStream.readObject();
                    			
                    			boolean isInsertUser = userDAO.insertUser(userDTO);
                    			if (isInsertUser) 
                    				writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_SIGNUP_Y);
                    			else
                    				writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_SIGNUP_N);
                    			break;
                    		}
                    		case Protocol.REQ_CREATE_REVIEW:{
                    			DetailDAO detailDAO = new DetailDAO();
                    			ReviewDTO reviewDTO = (ReviewDTO)objectInputStream.readObject();

                    			boolean isInsertReview = detailDAO.insertReview(reviewDTO);
                    			if(isInsertReview){
									writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_CREATE_REVIEW_Y);
								}
                    			else{
									writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_CREATE_REVIEW_N);
								}
                    			break;
                    		}
                    		case Protocol.REQ_DELETE_REVIEW:{
                    			MyPageDAO myPageDAO = new MyPageDAO();
                    			boolean isDeleteReview = myPageDAO.deleteReview( Integer.parseInt(packetArr[2]));

                    			if(isDeleteReview){
									writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_DELETE_REVIEW_Y);
								}
                    			else{
									writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_DELETE_REVIEW_N);
								}

                    			break;
                    		}
                    		case Protocol.REQ_UPDATE_USER:{
                    			MyPageDAO myPageDAO = new MyPageDAO();
                    			UserDTO userDTO = (UserDTO)objectInputStream.readObject();

                    			boolean check = myPageDAO.reservationUser(userDTO);
								if(check){
									writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_UPDATE_REVIEW_Y);
								}
								else{
									writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_UPDATE_REVIEW_N);
								}
                    			break;
                    		}
                    		case Protocol.REQ_CREATE_FAVORITES:{
                    			DetailDAO favoriteDAO = new DetailDAO();
                    			FavoriteDTO favoriteDTO = (FavoriteDTO)objectInputStream.readObject();
								boolean check = favoriteDAO.addFavorite(favoriteDTO);

								if(check){
									writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_CREATE_FAVORITES_Y);
								}
								else{
									writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_CREATE_FAVORITES_N);
								}

                    			break;
                    		}
                    		case Protocol.REQ_DELETE_FAVORITES:{
                    			MyPageDAO myPageDAO = new MyPageDAO();
                    			boolean check = myPageDAO.deleteFavorite(Integer.parseInt(packetArr[2]));

                    			if(check){
									writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_DELETE_FAVORITES_Y);
								}
                    			else{
									writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_DELETE_FAVORITES_N);
								}

                    			break;
                    		}
							case Protocol.REQ_UPDATE_VIEWSCOUNT:{
								DetailDAO detailDAO = new DetailDAO();
								boolean check = detailDAO.viewsCountIncrease(packetArr[2]);

								if(check){
									writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_UPDATE_VIEWSCOUNT_Y);
								}
								else{
									writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_UPDATE_VIEWSCOUNT_N);
								}
							}
                    	}
                    	break;
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writePacket(String packet) {
        try{
			bufferedWriter.write(packet + "\n");
            bufferedWriter.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

	public void writeObject(Object obj) {
		try {
			objectOutputStream.writeObject(obj);
			objectOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
