package SocketTCP;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
public class udpEchoClient {

	public static void main(String[] args) {
		int serverPort = 6789;
		// TODO Auto-generated method stub
          try {
        	  DatagramSocket ds = new DatagramSocket();
        	  InetAddress serverIP = InetAddress.getByName("localhost");
        	  System.out.println("UDP CLIENT da dc tao");
        	  
        	  for(char ch = '0'; ch <= '9';ch++) {
        	  String sendData = "data |" + ch;
        	  byte[] sendBuffer = sendData.getBytes();
        	  DatagramPacket dp = new DatagramPacket(sendBuffer, sendBuffer.length,serverIP,serverPort );
        	  ds.send(dp);
        	  
        	  byte[] resBuffer = new byte[1024];
        	  DatagramPacket resPacket = new DatagramPacket(resBuffer, resBuffer.length);
        	  ds.receive(resPacket);
        	  
        	  String resData = new String(resPacket.getData() , resPacket.getOffset(), resPacket.getLength());
        	  System.out.println("Server phan hoi:" +resData);
        	  
        	  Thread.sleep(2000);
          }
        	  
        	  System.out.println("Client da goi du 0-9 va nhan du phan hoi");
          }
          
          catch(Exception e) {
        	  System.out.println(e);
          }
	}
}
