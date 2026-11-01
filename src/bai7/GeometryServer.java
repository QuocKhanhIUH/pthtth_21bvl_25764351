package bai7;
import common.RmiServer;
public class GeometryServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		RmiServer.start("GeometryService", new GeometryServiceImpl());

	}

}
