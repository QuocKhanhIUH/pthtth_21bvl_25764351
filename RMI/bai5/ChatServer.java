package bai5;
import common.RmiServer;

public class ChatServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		RmiServer.start("ChatService", new ChatServiceImpl());

	}

}
