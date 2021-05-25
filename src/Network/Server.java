package Network;

import DBcontrol.DBconnection;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

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



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePacket(String source) throws Exception{
        try{

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
