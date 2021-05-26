package Network;

import DBcontrol.DBconnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public Server(Socket socket) throws ClassNotFoundException, SQLException{
        this.socket = socket;
        DBconnection.getConnection();
    }

    @Override
    public void run(){
        try{
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            writePacket(Protocol.PT_REQ_LOGIN_INFO);
            boolean program_stop = false;

            while(true)
            {
                String packet = bufferedReader.readLine();
                String packetArr[] = packet.split("`");
                String packetType = packetArr[0]; // 프로토콜 타입 구분

                switch (packetType)
                {
                    case Protocol.PT_EXIT:
                        writePacket(Protocol.PT_EXIT);
                        program_stop = true;
                        break;
                    case Protocol.REQ_ID_DUPLICATION:


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
}
