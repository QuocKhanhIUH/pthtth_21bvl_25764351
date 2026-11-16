package SocketTCP;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class udpEchoServer {

public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DatagramSocket ds = new DatagramSocket(6789);
			System.out.println("UDP SERVER đã được tạo" );
			while(true) {
				//for(int i = 0; i < 10; i++) {
					byte[] receiveBuffer = new byte[1024];
					
					DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
					ds.receive(receivePacket);
					
					// lay dlieu trong revicevePacket
					String receiveData = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
					System.out.println("SERVER NHAN: "+receiveData);
					
					// xu ly
					// tao goi tin chua dlieu sendPacket to Client
					byte[] sendBuffer = receiveData.getBytes();
					DatagramPacket dp = new DatagramPacket(sendBuffer, sendBuffer.length, receivePacket.getAddress(), receivePacket.getPort());
					ds.send(dp);
					
				}
			
		}catch(IOException e) {
			
			
			System.out.println(e);	}
		

	}

}
