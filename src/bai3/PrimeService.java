package bai3;
import java.rmi.Remote;// Interface đánh dấu đối tượng từ xa
import java.rmi.RemoteException; // Interface đánh dấu đối tượng từ xa

public interface PrimeService extends Remote { // Kế thừa Remote để client gọi được từ xa
	boolean isPrime(int n) throws RemoteException; // Kiểm tra n có phải số nguyên tố không; true = nguyên tố

}
