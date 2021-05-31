package Network;

import DAO.DetailDAO;
import DAO.InquireToiletParkingDAO;
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
import java.util.Iterator;
import java.util.function.ToLongBiFunction;

import DTO.*;

import DAO.UserDAO;

import javax.xml.soap.Detail;

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

            writePacket(Protocol.PT_REQ_LOGIN_INFO);
            boolean program_stop = false;

            while(true) {
                String packet = bufferedReader.readLine();
                String packetArr[] = packet.split("|");
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
                    		if (isCorrectUser) 
                    			writePacket(Protocol.PT_RES_LOGIN + "|" + Protocol.RES_LOGIN_Y);
                    		else
                    			writePacket(Protocol.PT_RES_LOGIN + "|" + Protocol.RES_LOGIN_N);
                    	} catch (Exception e) {
                    		e.printStackTrace();
                    	}
                    	break;
                    }
                    case Protocol.PT_REQ_VIEW: {
                    	String packetCode = packetArr[1];
                    	switch (packetCode) {
                    		case Protocol.REQ_DESTINATION_REGION:{
                    			
                    			break;
                    		}
                    		case Protocol.REQ_TOURIST_DETAIL:{
								DetailDAO detailDAO = new DetailDAO();

								TouristSpotDTO touristSpotDTO = detailDAO.detailTouristSpot(packetArr[1]);
								ArrayList<ReviewDTO> arrayList = detailDAO.inquireReview(packetArr[1]);
								Iterator<ReviewDTO> iter = arrayList.iterator();

								if (touristSpotDTO!=null) {
									bufferedWriter.write(Protocol.PT_RES_VIEW + "|" + Protocol.RES_TOURIST_DETAIL_Y + "|" + touristSpotDTO);
									while(iter.hasNext()){
										ReviewDTO reviewDTO = iter.next();
										bufferedWriter.write(Protocol.PT_RES_VIEW + "|" + Protocol.RES_TOURIST_DETAIL_Y + "|" + reviewDTO);
									}
								}
								else
									bufferedWriter.write(Protocol.PT_RES_VIEW + "|" + Protocol.RES_TOURIST_DETAIL_N);
                    			break;
                    		}
                    		case Protocol.REQ_FOREST_DETAIL:{
								DetailDAO detailDAO = new DetailDAO();

								ForestLodgeDTO forestLodgeDTO = detailDAO.detailForestLodge(packetArr[1]);
								if (forestLodgeDTO!=null)
									bufferedWriter.write(Protocol.PT_RES_VIEW + "|" + Protocol.RES_FOREST_DETAIL_Y+"|"+forestLodgeDTO);
								else
									bufferedWriter.write(Protocol.PT_RES_VIEW + "|" + Protocol.RES_FOREST_DETAIL_N);
                    			break;
                    		}
                    		case Protocol.REQ_BEACH_DETAIL:{
								DetailDAO detailDAO = new DetailDAO();

								BeachDTO beachDTO = detailDAO.detailBeach(packetArr[1]);
								if (beachDTO!=null)
									bufferedWriter.write(Protocol.PT_RES_VIEW + "|" + Protocol.RES_BEACH_DETAIL_Y+"|"+beachDTO);
								else
									bufferedWriter.write(Protocol.PT_RES_VIEW + "|" + Protocol.RES_BEACH_DETAIL_N);
                    			break;
                    		}
                    		case Protocol.REQ_REVIEW_DETAIL:{
								 detailDAO = new DetailDAO();

								BeachDTO beachDTO = detailDAO.detailBeach(packetArr[1]);
								if (beachDTO!=null)
									bufferedWriter.write(Protocol.PT_RES_VIEW + "|" + Protocol.RES_BEACH_DETAIL_Y+"|"+beachDTO);
								else
									bufferedWriter.write(Protocol.PT_RES_VIEW + "|" + Protocol.RES_BEACH_DETAIL_N);
                    			break;
                    		}
                    		case Protocol.REQ_TOILET:{
								InquireToiletParkingDAO inquireToiletParkingDAO = new InquireToiletParkingDAO();

                    			ArrayList<ToiletDTO> toiletDTOS = inquireToiletParkingDAO.inquireToiletByLocation(packetArr[1],packetArr[2],packetArr[3]);
								Iterator<ToiletDTO> iter = toiletDTOS.iterator();

								if(iter!=null) {
									while (iter.hasNext()) {
										bufferedWriter.write(Protocol.PT_RES_VIEW + "|" + Protocol.RES_TOILET_Y + "|" + iter.next());
									}
								}
								else{
									bufferedWriter.write(Protocol.PT_RES_VIEW + "|" + Protocol.RES_TOILET_N);
								}
                    			break;
                    		}
                    		case Protocol.REQ_PARKING:{

                    			break;
                    		}
                    		case Protocol.REQ_DESTINATION_LOCATION:{

                    			break;
                    		}
                    		case Protocol.REQ_STATISTICS:{

                    			break;
                    		}
                    		case Protocol.REQ_MYPAGE:{

                    			break;
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
                    			
                    			boolean isDuplicationId = userDAO.duplicationId(userDTO.getId());
                    			if (!isDuplicationId)
                    				bufferedWriter.write(Protocol.PT_RES_RENEWAL + "|" + Protocol.RES_SIGNUP_N);
                    			
                    			boolean isInsertUser = userDAO.insertUser(userDTO);
                    			if (isInsertUser) 
                    				bufferedWriter.write(Protocol.PT_RES_RENEWAL + "|" + Protocol.RES_SIGNUP_Y);
                    			else
                    				bufferedWriter.write(Protocol.PT_RES_RENEWAL + "|" + Protocol.RES_SIGNUP_N);
                    			break;
                    		}
                    		case Protocol.REQ_CREATE_REVIEW:{
                    			
                    			break;
                    		}
                    		case Protocol.REQ_UPDATE_REVIEW:{
                    			
                    			break;
                    		}
                    		case Protocol.REQ_DELETE_REVIEW:{
                    			
                    			break;
                    		}
                    		case Protocol.REQ_UPDATE_USER:{
                    			
                    			break;
                    		}
                    		case Protocol.REQ_CREATE_BOOKMARK:{
                    			
                    			break;
                    		}
                    		case Protocol.REQ_DELETE_BOOKMARK:{
                    			
                    			break;
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
