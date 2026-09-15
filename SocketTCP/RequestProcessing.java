package SocketTCP;
import java.io.*;
import java.net.*;

public class RequestProcessing extends Thread {
	Socket channel; //socket cua kenh ao noi voi client 
	public RequestProcessing(Socket s) {
		channel = s;
	}
	public void run() {
		try {
			OutputStream os = channel.getOutputStream();
			InputStream is = channel.getInputStream();
			while(true) {
				int n =is.read();
				if(n == -1) 
					break;
				os.write(n);
			}
		}catch(IOException e) {
			System.out.println("Request Processing Error: "+e);
		}
		finally {
			try {
				if(channel != null) {
					channel.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
