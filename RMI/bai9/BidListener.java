package bai9;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BidListener extends Remote {
	void onBidUpdate(String message) throws RemoteException;

}
