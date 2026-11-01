package bai6;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankAccount extends Remote {
	double getBalance() throws RemoteException; // Xem số dư hiện tại
	void deposit(double amount) throws RemoteException; // Gửi tiền (không trả về gì)
	boolean withdraw(double amount) throws RemoteException;  // Rút tiền; true = thành công, false = thất bại

}
