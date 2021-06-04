package Network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;


public class ServerSystem {
	private static final int SERVER_PORT = 5000;
	
	ServerSystem() throws ClassNotFoundException, SQLException, IOException
	{
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException
	{
		new ServerSystem().start();
	}
	
	// 메인문과 같은 원리 대부분의 시스템 동작흐름
	public void start()
	{
		ServerSocket serverSocket;
		Socket socket;
		
		try
		{
			serverSocket = new ServerSocket();
			String localHostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress("192.168.225.151", SERVER_PORT));
			System.out.println("[server] binding! \n[server] address:" + localHostAddress + ", port:" + SERVER_PORT);
			System.out.println("클라이언트 접속 대기중...");
			while (true)
			{
				socket = serverSocket.accept();
				System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + "에서 접속 하였습니다.");
				Server serverThread = new Server(socket);
				serverThread.start();
			}
			
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}
}