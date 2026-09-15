package SocketTCP;
import java.io.*;
import java.net.*;
public class TCPEchoServer1 {
	public final static int serverPort = 1601;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket ss = new ServerSocket(serverPort);
			System.out.println("Server da duoc tao");
			while(true) {
				try {
					Socket s = ss.accept();
					RequestProcessing rp = new RequestProcessing(s);
					rp.start();
				}catch(IOException ie1) {
					System.out.println("Connec Error: "+ie1);
				}
			}
		}catch(IOException ie) {
			System.out.println("Server Creation Error "+ie);
		}
	}
}
