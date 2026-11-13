package bai8;
import common.RmiServer;

public class FlightServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		RmiServer.start("FlightService", new FlightServiceImpl());
	}

}
