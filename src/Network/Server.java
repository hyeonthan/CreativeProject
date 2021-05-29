package Network;

import DBcontrol.DBconnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;

public class Server extends Thread{
    Socket socket;
    private static int curUser=0;
    BufferedReader bufferedReader= null;
    BufferedWriter bufferedWriter= null;
    ObjectInputStream objectInputStream = null;
    ObjectOutputStream objectOutputStream = null;

    public Server(Socket socket) throws ClassNotFoundException, SQLException{
        this.socket = socket;
        DBconnection.getConnection();
    }

    @Override
    public void run(){
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            writePacket(Protocol.PT_REQ_LOGIN_INFO);
            boolean program_stop = false;

            while(true)
            {
                String packet = bufferedReader.readLine();
                String packetArr[] = packet.split("|");
                String packetType = packetArr[0]; // 프로토콜 타입 구분

                switch (packetType)
                {
                    case Protocol.PT_EXIT:{
                        writePacket(Protocol.PT_EXIT);
                        program_stop = true;
                        break;
                    }
                    case Protocol.PT_REQ_LOGIN:{
                    	
                    	break;
                    }
                    case Protocol.PT_REQ_FILE:{

                    	break;
                    }
                    case Protocol.PT_REQ_VIEW: {
                    	String packetCode = packetArr[1];
                    	switch (packetCode) {
                    		case Protocol.REQ_DESTINATION_REGION:{

                    			break;
                    		}
                    		case Protocol.REQ_TOURIST_DETAIL:{

                    			break;
                    		}
                    		case Protocol.REQ_FOREST_DETAIL:{

                    			break;
                    		}
                    		case Protocol.REQ_BEACH_DETAIL:{

                    			break;
                    		}
                    		case Protocol.REQ_REVIEW_DETAIL:{

                    			break;
                    		}
                    		case Protocol.REQ_TOILET:{

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
                    		case Protocol.REQ_SINGUP:{
                    			
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

    public void writePacket(String source) throws Exception{
        try{
            bufferedWriter.write(source + "\n");
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
