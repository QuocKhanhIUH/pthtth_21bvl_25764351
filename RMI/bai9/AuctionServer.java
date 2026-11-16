package bai9;
import common.RmiServer;

public class AuctionServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		RmiServer.start("AuctionService", new AuctionServiceImpl());

	}

}
