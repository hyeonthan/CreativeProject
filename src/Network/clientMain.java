package Network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class clientMain extends Application {
	private static BufferedReader bufferedReader;
	private static BufferedWriter bufferedWriter;
	private static ObjectInputStream objectInputStream;
	private static ObjectOutputStream objectOutputStream;
	
	public static void main(String[] args) {
		try {
			String localHostAddress = InetAddress.getLocalHost().getHostAddress(); // ip 주소
			Socket socket = new Socket("192.168.225.151", 5000);
			
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			
			launch(args);

			objectInputStream.close();
			objectOutputStream.close();
			bufferedWriter.close();
			bufferedReader.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(clientMain.class.getResource("../FXML/login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String readPacket() {
		try {
			return bufferedReader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void writePacket(String packet) {
		try {
			bufferedWriter.write(packet + "\n");
			bufferedWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object readObject() {
		try {
			return objectInputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void writeObject(Object obj) {
		try {
			objectOutputStream.writeObject(obj);
			objectOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
