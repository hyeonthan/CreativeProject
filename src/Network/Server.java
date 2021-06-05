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
	ArrayList<Object> objectList = new ArrayList<Object>();

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
                // String packet = bufferedReader.readLine();
                // String packetArr[] = packet.split("`");
                // String packetType = packetArr[0]; // 프로토콜 타입 구분
				objectList = (ArrayList<Object>) objectInputStream.readObject();
				String packetType = (String)objectList.get(0);

                switch (packetType) {
                    case Protocol.PT_EXIT:{
                        writePacket(Protocol.PT_EXIT);
                        program_stop = true;
                        break;
                    }
                    case Protocol.PT_REQ_LOGIN:{
                    	// String loginId = packetArr[1];
                    	// String loginPassword = packetArr[2];
						String loginId = (String)objectList.get(1);
						String loginPassword = (String)objectList.get(2);
                    	
                    	try {
                    		UserDAO userDao = new UserDAO();
                    		boolean isCorrectUser = userDao.checkUser(loginId,  loginPassword);
                    		if (isCorrectUser) {
								// writePacket(Protocol.PT_RES_LOGIN + "`" + Protocol.RES_LOGIN_Y);
								objectList.clear();
								objectList.add(Protocol.PT_RES_LOGIN);
								objectList.add(Protocol.RES_LOGIN_Y);
								writeObject(objectList);
								objectList.clear();
								id = loginId;
                    		}
                    		else {
                    			// writePacket(Protocol.PT_RES_LOGIN + "`" + Protocol.RES_LOGIN_N);
								objectList.clear();
								objectList.add(Protocol.PT_RES_LOGIN);
								objectList.add(Protocol.RES_LOGIN_N);
								writeObject(objectList);
								objectList.clear();
							}
                    	} catch (Exception e) {
                    		e.printStackTrace();
                    	}
                    	break;
                    }
                    case Protocol.PT_REQ_VIEW: {	//조회 요청
                    	// String packetCode = packetArr[1];
						String packetCode = (String) objectList.get(1);
                    	switch (packetCode) {
                    		case Protocol.REQ_DESTINATION_REGION:{	//지역으로 전체 검색
								InquireByRegionDAO inquireByRegionDAO = new InquireByRegionDAO();
								ArrayList<DestinationDTO> destinationDTOS = inquireByRegionDAO.inquireDestinationByRegion((String)objectList.get(2), (String)objectList.get(3), (String)objectList.get(4));
								
								if(destinationDTOS != null){
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_DESTINATION_REGION_Y);
									// writeObject(destinationDTOS);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_DESTINATION_REGION_Y);
									objectList.add(destinationDTOS);
									writeObject(objectList);
									objectList.clear();
								}
								else{
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_DESTINATION_REGION_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_DESTINATION_REGION_N);
									writeObject(objectList);
									objectList.clear();
								}
                    			break;
                    		}
							case Protocol.REQ_DESTINATION:{			//여행지 하나 조회
								MyPageDAO myPageDAO = new MyPageDAO();
								// DestinationDTO destinationDTO = myPageDAO.loadDestinationDTO(packetArr[2]);
								DestinationDTO destinationDTO = myPageDAO.loadDestinationDTO((String)objectList.get(2));

								if(destinationDTO!=null){
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_DESTINATION_Y);
									// writeObject(destinationDTO);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_DESTINATION_Y);
									objectList.add(destinationDTO);
									writeObject(objectList);
									objectList.clear();
								}
								else{
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_DESTINATION_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_DESTINATION_Y);
									writeObject(objectList);
									objectList.clear();
								}
								break;
							}
                    		case Protocol.REQ_TOURIST_DETAIL:{	//관광지 상세정보
								DetailDAO detailDAO = new DetailDAO();

								// TouristSpotDTO touristSpotDTO = detailDAO.detailTouristSpot(packetArr[2]);
								// ArrayList<ReviewDTO> arrayList = detailDAO.inquireReview(packetArr[3]);
								TouristSpotDTO touristSpotDTO = detailDAO.detailTouristSpot((String)objectList.get(2));
								ArrayList<ReviewDTO> arrayList = detailDAO.inquireReview((String)objectList.get(3));
								if (touristSpotDTO != null) {
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_TOURIST_DETAIL_Y);
									// writeObject(touristSpotDTO);
									// writeObject(arrayList);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_TOURIST_DETAIL_Y);
									objectList.add(touristSpotDTO);
									objectList.add(arrayList);
									writeObject(objectList);
									objectList.clear();
								}
								else {
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_TOURIST_DETAIL_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_TOURIST_DETAIL_N);
									writeObject(objectList);
									objectList.clear();
								}
                    			break;
                    		}
                    		case Protocol.REQ_FOREST_DETAIL:{		// 휴양림 상세정보
								DetailDAO detailDAO = new DetailDAO();

								// ForestLodgeDTO forestLodgeDTO = detailDAO.detailForestLodge(packetArr[2]);
								// ArrayList<ReviewDTO> arrayList = detailDAO.inquireReview(packetArr[3]);
								ForestLodgeDTO forestLodgeDTO = detailDAO.detailForestLodge((String)objectList.get(2));
								ArrayList<ReviewDTO> arrayList = detailDAO.inquireReview((String)objectList.get(3));
								if (forestLodgeDTO!=null) {
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_FOREST_DETAIL_Y);
									// writeObject(forestLodgeDTO);
									// writeObject(arrayList);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_FOREST_DETAIL_Y);
									objectList.add(forestLodgeDTO);
									objectList.add(arrayList);
									writeObject(objectList);
									objectList.clear();
								}
								else {
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_FOREST_DETAIL_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_FOREST_DETAIL_N);
									writeObject(objectList);
									objectList.clear();
								}
                    			break;
                    		}
                    		case Protocol.REQ_BEACH_DETAIL:{		//해수욕장 상세정보
								DetailDAO detailDAO = new DetailDAO();

								// BeachDTO beachDTO = detailDAO.detailBeach(packetArr[2]);
								// ArrayList<ReviewDTO> arrayList = detailDAO.inquireReview(packetArr[3]);
								BeachDTO beachDTO = detailDAO.detailBeach((String)objectList.get(2));
								ArrayList<ReviewDTO> arrayList = detailDAO.inquireReview((String)objectList.get(3));
								if (beachDTO!=null) {
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_BEACH_DETAIL_Y);
									// writeObject(beachDTO);
									// writeObject(arrayList);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_BEACH_DETAIL_Y);
									objectList.add(beachDTO);
									objectList.add(arrayList);
									writeObject(objectList);
									objectList.clear();
								}
								else {
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_BEACH_DETAIL_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_BEACH_DETAIL_N);
									writeObject(objectList);
									objectList.clear();
								}
                    			break;
                    		}
                    		case Protocol.REQ_TOILET:{		//화장실 정보 요청
								InquireToiletParkingDAO inquireToiletParkingDAO = new InquireToiletParkingDAO();
                    			// ArrayList<ToiletDTO> toiletDTOS = inquireToiletParkingDAO.inquireToiletByLocation(packetArr[2],packetArr[3],packetArr[4]);
								ArrayList<ToiletDTO> toiletDTOS = inquireToiletParkingDAO.inquireToiletByLocation((String)objectList.get(2), (String)objectList.get(3), (String)objectList.get(4));
								if(toiletDTOS!=null) {
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_TOILET_Y);
									// writeObject(toiletDTOS);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_TOILET_Y);
									objectList.add(toiletDTOS);
									writeObject(objectList);
									objectList.clear();
								}
								else{
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_TOILET_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_TOILET_Y);
									writeObject(objectList);
									objectList.clear();
								}
                    			break;
                    		}
                    		case Protocol.REQ_PARKING:{		//주차장 정보 요청
                    			InquireToiletParkingDAO inquireToiletParkingDAO = new InquireToiletParkingDAO();
                    			// ArrayList<ParkingLotsDTO> parkingLotsDTOS = inquireToiletParkingDAO.inquireParkingLotByLocation(packetArr[2],packetArr[3],packetArr[4]);
								ArrayList<ParkingLotsDTO> parkingLotsDTOS = inquireToiletParkingDAO.inquireParkingLotByLocation((String)objectList.get(2), (String)objectList.get(3), (String)objectList.get(4));
								if(parkingLotsDTOS!=null) {
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_PARKING_Y);
									// writeObject(parkingLotsDTOS);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_PARKING_Y);
									objectList.add(parkingLotsDTOS);
									writeObject(objectList);
									objectList.clear();
								}
								else{
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_PARKING_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_PARKING_N);
									writeObject(objectList);
									objectList.clear();
								}
                    			break;
                    		}
                    		case Protocol.REQ_DESTINATION_LOCATION:{	//위치로 검색
                    			InquireByLocationDAO inquireByLocationDAO = new InquireByLocationDAO();
                    			ArrayList<DestinationDTO> arrayList = null;
								
                    			switch((String)objectList.get(2)) {
									case " ":
										// arrayList = inquireByLocationDAO.inquireDestinationByLocation(packetArr[3], packetArr[4], packetArr[5]);
										arrayList = inquireByLocationDAO.inquireDestinationByLocation((String)objectList.get(3), (String)objectList.get(4), (String)objectList.get(5));
										break;
									case"해수욕장":
										// arrayList = inquireByLocationDAO.inquireBeachByLocation(packetArr[3], packetArr[4], packetArr[5]);
										arrayList = inquireByLocationDAO.inquireBeachByLocation((String)objectList.get(3), (String)objectList.get(4), (String)objectList.get(5));
										break;
									case"휴양림":
										// arrayList = inquireByLocationDAO.inquireForestLodgeByLocation(packetArr[3], packetArr[4], packetArr[5]);
										arrayList = inquireByLocationDAO.inquireForestLodgeByLocation((String)objectList.get(3), (String)objectList.get(4), (String)objectList.get(5));
										break;
									case"관광지":
										// arrayList = inquireByLocationDAO.inquireTouristSpotByLocation(packetArr[3], packetArr[4], packetArr[5]);
										arrayList = inquireByLocationDAO.inquireTouristSpotByLocation((String)objectList.get(3), (String)objectList.get(4), (String)objectList.get(5));
										break;
								}
								
								if(arrayList != null){
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_DESTINATION_LOCATION_Y);
									// writeObject(arrayList);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_DESTINATION_LOCATION_Y);
									objectList.add(arrayList);
									writeObject(objectList);
									objectList.clear();
								}
								else{
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_DESTINATION_LOCATION_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_DESTINATION_LOCATION_N);
									writeObject(objectList);
									objectList.clear();
								}
                    			break;
                    		}
                    		case Protocol.REQ_STATISTICS:{	//통계페이지 요청
                    			StatisticsDAO statisticsDAO = new StatisticsDAO();
                    			ArrayList<DestinationDTO> arrayList = null;
								if(((String)objectList.get(2)).equals(" ")){
									switch ((String)objectList.get(3)) {
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
									switch ((String)objectList.get(3)) {
										case "조회수":
											arrayList = statisticsDAO.loadViewsStat((String)objectList.get(2));
											break;
										case "별점":
											arrayList = statisticsDAO.loadScopeStat((String)objectList.get(2));
											break;
										case "출신지":
											arrayList=statisticsDAO.birthRegionStat((String)objectList.get(2));
											break;
										case"리뷰수":
											arrayList =statisticsDAO.reviewCntStat((String)objectList.get(2));
											break;
									}
								}

								if(arrayList!=null){
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_STATISTICS_Y);
									// writeObject(arrayList);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_STATISTICS_Y);
									objectList.add(arrayList);
									writeObject(objectList);
									objectList.clear();
								}
								else{
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_STATISTICS_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_STATISTICS_N);
									writeObject(objectList);
									objectList.clear();
								}
                    			break;
                    		}
                    		case Protocol.REQ_MYPAGE:{ //마이페이지 요청
                    			MyPageDAO myPageDAO = new MyPageDAO();
                    			UserDTO userDTO = myPageDAO.roadUser((String)objectList.get(2));
								ArrayList<ReviewDTO> arrayList = myPageDAO.inquireMyReview((String)objectList.get(2));
								ArrayList<FavoriteDTO> arrayList1 = myPageDAO.inquiryFavorite((String)objectList.get(2));

								if(userDTO != null){
									//writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_MYPAGE_Y);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_MYPAGE_Y);
									objectList.add(userDTO);
									objectList.add(arrayList);
									objectList.add(arrayList1);
									writeObject(objectList);
									objectList.clear();
									// writeObject(userDTO);
									// writeObject(arrayList);
									// writeObject(arrayList1);
								}
								else{
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_MYPAGE_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_MYPAGE_N);
									writeObject(objectList);
									objectList.clear();
								}

                    			break;
                    		}
							case Protocol.REQ_ID_DUPLICATION:{		//아이디 중복 체크 요청
								UserDAO userDAO = new UserDAO();
								boolean check = userDAO.duplicationId((String)objectList.get(2));

								if(!check){
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_ID_DUPLICATION_Y);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_ID_DUPLICATION_Y);
									writeObject(objectList);
									objectList.clear();
								}
								else{
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_ID_DUPLICATION_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_ID_DUPLICATION_N);
									writeObject(objectList);
									objectList.clear();
								}
								break;
							}
							case Protocol.REQ_STATISTICS_DETAIL:{
								DetailDAO detailDAO = new DetailDAO();
								switch((String)objectList.get(2)){
									case "출신지":
										HashMap<String,Integer> hashMap = detailDAO.regionStatistic((String)objectList.get(3));
										if(hashMap!=null){
											// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_STATISTICS_DETAIL_Y);
											// writeObject(hashMap);
											objectList.clear();
											objectList.add(Protocol.PT_RES_VIEW);
											objectList.add(Protocol.RES_STATISTICS_DETAIL_Y);
											objectList.add(hashMap);
											writeObject(objectList);
											objectList.clear();
										}
										else{
											// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_STATISTICS_DETAIL_N);
											objectList.clear();
											objectList.add(Protocol.PT_RES_VIEW);
											objectList.add(Protocol.RES_STATISTICS_DETAIL_N);
											writeObject(objectList);
											objectList.clear();
										}
										break;
									case"성별":
										String s = detailDAO.genderStatistic((String)objectList.get(3));
										if(s!=null){
											// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_STATISTICS_DETAIL_Y);
											// writePacket(s);
											objectList.clear();
											objectList.add(Protocol.PT_RES_VIEW);
											objectList.add(Protocol.RES_STATISTICS_DETAIL_Y);
											objectList.add(s);
											writeObject(objectList);
											objectList.clear();
										}
										else{
											// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_STATISTICS_DETAIL_N);
											objectList.clear();
											objectList.add(Protocol.PT_RES_VIEW);
											objectList.add(Protocol.RES_STATISTICS_DETAIL_N);
											writeObject(objectList);
											objectList.clear();
										}
										break;
									case "나이별":
										HashMap<Integer,Integer> hashMap1 = detailDAO.ageStatistic((String)objectList.get(3));
										if(hashMap1!=null){
											// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_STATISTICS_DETAIL_Y);
											// writeObject(hashMap1);
											objectList.clear();
											objectList.add(Protocol.PT_RES_VIEW);
											objectList.add(Protocol.RES_STATISTICS_DETAIL_Y);
											objectList.add(hashMap1);
											writeObject(objectList);
											objectList.clear();
										}
										else{
											// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_STATISTICS_DETAIL_N);
											objectList.clear();
											objectList.add(Protocol.PT_RES_VIEW);
											objectList.add(Protocol.RES_STATISTICS_DETAIL_N);
											writeObject(objectList);
											objectList.clear();
										}
										break;
								}
								break;
							}
							case Protocol.REQ_FAVORITES: {        // 즐겨찾기 요청
								MyPageDAO myPageDAO = new MyPageDAO();
								ArrayList<FavoriteDTO> arrayList = myPageDAO.inquiryFavorite((String)objectList.get(2));

								if (arrayList != null) {
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_FAVORITES_Y);
									// writeObject(arrayList);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_FAVORITES_Y);
									writeObject(objectList);
									objectList.clear();
								} else {
									// writePacket(Protocol.PT_RES_VIEW + "`" + Protocol.RES_FAVORITES_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_FAVORITES_N);
									writeObject(objectList);
									objectList.clear();
								}
								break;
							}
							case Protocol.REQ_REVIEWS: {            //리뷰 요청
								MyPageDAO myPageDAO = new MyPageDAO();
								ArrayList<ReviewDTO> arrayList = myPageDAO.inquireMyReview((String)objectList.get(2));

								if(arrayList!=null){
									// writePacket(Protocol.PT_RES_VIEW +"`"+ Protocol.RES_REVIEWS_Y);
									// writeObject(arrayList);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_REVIEWS_Y);
									objectList.add(arrayList);
									writeObject(objectList);
									objectList.clear();
								}
								else{
									// writePacket(Protocol.PT_RES_VIEW +"`"+ Protocol.RES_REVIEWS_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_VIEW);
									objectList.add(Protocol.RES_REVIEWS_N);
									writeObject(objectList);
									objectList.clear();
								}
								break;
							}
                    	}
                    	break;
                    }
                    case Protocol.PT_REQ_RENEWAL:{
                    	String packetCode = (String)objectList.get(1);
                    	switch (packetCode) {
                    		case Protocol.REQ_SIGNUP:{
                    			UserDAO userDAO = new UserDAO();
                    			// UserDTO userDTO = (UserDTO)objectInputStream.readObject();
								UserDTO userDTO = (UserDTO)objectList.get(2);
                    			
                    			boolean isInsertUser = userDAO.insertUser(userDTO);
                    			if (isInsertUser) {
                    				// writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_SIGNUP_Y);
									objectList.clear();
									objectList.add(Protocol.PT_RES_RENEWAL);
									objectList.add(Protocol.RES_SIGNUP_Y);
									writeObject(objectList);
									objectList.clear();
								}
                    			else {
                    				// writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_SIGNUP_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_RENEWAL);
									objectList.add(Protocol.RES_SIGNUP_N);
									writeObject(objectList);
									objectList.clear();
								}
                    			break;
                    		}
                    		case Protocol.REQ_CREATE_REVIEW:{
								System.out.println("뭐가문젤까");
                    			DetailDAO detailDAO = new DetailDAO();
                    			// ReviewDTO reviewDTO = (ReviewDTO)objectInputStream.readObject();
								ReviewDTO reviewDTO = (ReviewDTO)objectList.get(2);

                    			boolean isInsertReview = detailDAO.insertReview(reviewDTO);
								System.out.println("여기까진했는데");
                    			if(isInsertReview){
									// writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_CREATE_REVIEW_Y);
									objectList.clear();
									objectList.add(Protocol.PT_RES_RENEWAL);
									objectList.add(Protocol.RES_CREATE_REVIEW_Y);
									writeObject(objectList);
									System.out.println("전송완료");
									objectList.clear();
								}
                    			else{
									// writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_CREATE_REVIEW_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_RENEWAL);
									objectList.add(Protocol.RES_CREATE_REVIEW_N);
									writeObject(objectList);
									objectList.clear();
								}
                    			break;
                    		}
                    		case Protocol.REQ_DELETE_REVIEW:{
                    			MyPageDAO myPageDAO = new MyPageDAO();
                    			boolean isDeleteReview = myPageDAO.deleteReview((int)objectList.get(2), (String)objectList.get(3));

                    			if(isDeleteReview){
									// writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_DELETE_REVIEW_Y);
									objectList.clear();
									objectList.add(Protocol.PT_RES_RENEWAL);
									objectList.add(Protocol.RES_DELETE_REVIEW_Y);
									writeObject(objectList);
									objectList.clear();
								}
                    			else{
									//writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_DELETE_REVIEW_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_RENEWAL);
									objectList.add(Protocol.RES_DELETE_REVIEW_N);
									writeObject(objectList);
									objectList.clear();
								}

                    			break;
                    		}
                    		case Protocol.REQ_UPDATE_USER:{
                    			MyPageDAO myPageDAO = new MyPageDAO();
                    			// UserDTO userDTO = (UserDTO)objectInputStream.readObject();
								UserDTO userDTO = (UserDTO)objectList.get(2);

                    			boolean check = myPageDAO.reservationUser(userDTO);
								if(check){
									// writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_UPDATE_USER_Y);
									objectList.clear();
									objectList.add(Protocol.PT_RES_RENEWAL);
									objectList.add(Protocol.RES_UPDATE_USER_Y);
									writeObject(objectList);
									objectList.clear();
								}
								else{
									// writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_UPDATE_USER_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_RENEWAL);
									objectList.add(Protocol.RES_UPDATE_USER_N);
									writeObject(objectList);
									objectList.clear();
								}
                    			break;
                    		}
                    		case Protocol.REQ_CREATE_FAVORITES:{		//즐겨찾기 추가
                    			DetailDAO favoriteDAO = new DetailDAO();
                    			// FavoriteDTO favoriteDTO = (FavoriteDTO)objectInputStream.readObject();
								FavoriteDTO favoriteDTO = (FavoriteDTO)objectList.get(2);
								boolean check = favoriteDAO.addFavorite(favoriteDTO);

								if(check){
									// writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_CREATE_FAVORITES_Y);
									objectList.clear();
									objectList.add(Protocol.PT_RES_RENEWAL);
									objectList.add(Protocol.RES_CREATE_FAVORITES_Y);
									writeObject(objectList);
									objectList.clear();
								}
								else{
									// writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_CREATE_FAVORITES_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_RENEWAL);
									objectList.add(Protocol.RES_CREATE_FAVORITES_N);
									writeObject(objectList);
									objectList.clear();
								}

                    			break;
                    		}
                    		case Protocol.REQ_DELETE_FAVORITES:{
                    			MyPageDAO myPageDAO = new MyPageDAO();
                    			boolean check = myPageDAO.deleteFavorite((int)objectList.get(2));

                    			if(check){
									// writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_DELETE_FAVORITES_Y);
									objectList.clear();
									objectList.add(Protocol.PT_RES_RENEWAL);
									objectList.add(Protocol.RES_DELETE_FAVORITES_Y);
									writeObject(objectList);
									objectList.clear();
								}
                    			else{
									// writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_DELETE_FAVORITES_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_RENEWAL);
									objectList.add(Protocol.RES_DELETE_FAVORITES_N);
									writeObject(objectList);
									objectList.clear();
								}

                    			break;
                    		}
							case Protocol.REQ_UPDATE_VIEWSCOUNT:{
								DetailDAO detailDAO = new DetailDAO();
								boolean check = detailDAO.viewsCountIncrease((String)objectList.get(2));

								if(check){
									// writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_UPDATE_VIEWSCOUNT_Y);
									objectList.clear();
									objectList.add(Protocol.PT_RES_RENEWAL);
									objectList.add(Protocol.RES_UPDATE_VIEWSCOUNT_Y);
									writeObject(objectList);
									objectList.clear();
								}
								else{
									// writePacket(Protocol.PT_RES_RENEWAL + "`" + Protocol.RES_UPDATE_VIEWSCOUNT_N);
									objectList.clear();
									objectList.add(Protocol.PT_RES_RENEWAL);
									objectList.add(Protocol.RES_UPDATE_VIEWSCOUNT_N);
									writeObject(objectList);
									objectList.clear();
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
