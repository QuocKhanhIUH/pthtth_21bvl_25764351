package bai6;
import common.RmiServer;

public class BankServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		RmiServer.start("BankService", new BankAccountImpl(1_000_000));

	}

}
