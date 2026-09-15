package SocketTCP;
import java.io.*;
import java.net.*;
public class TCPEchoServer {

	public final static int serverPort = 1601;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket ss = new ServerSocket(serverPort);
			System.out.println("Server da dc tao");
			while(true) {
				try {
					Socket s = ss.accept();
					OutputStream os = s.getOutputStream();
					InputStream is = s.getInputStream();
					int ch = 0;
					while(true) {
						ch = is.read();
						if(ch == -1)
							break;
						System.out.println((char)ch);
						os.write(ch);
					}
					s.close();
				}catch(IOException e1) {
					System.out.println("Connection Error: "+e1);
				}
			}
		}catch(IOException e2) {
			System.out.println("Server creation Error: "+e2);
		}  
	}
}
