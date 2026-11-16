package bai9;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AuctionService extends Remote {
	boolean addItem(String seller, String itemName, double startPrice) throws RemoteException;
	List<Item> listItem() throws RemoteException;
	boolean placeBid(String itemName, String biider, double amount) throws RemoteException;
	void registerListener(String username, BidListener listenner) throws RemoteException;
	List<Item> listItems() throws RemoteException;

}
