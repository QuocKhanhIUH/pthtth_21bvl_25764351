package bai3;
import java.rmi.RemoteException; // Lỗi RMI
import java.rmi.server.UnicastRemoteObject; // Lớp cha giúp xuất đối tượng ra mạng

public class PrimeServerImpl extends UnicastRemoteObject implements PrimeService { // Đối tượng từ xa cài đặt PrimeService
	public PrimeServerImpl() throws RemoteException{ // Constructor, phải throws vì xuất đối tượng có thể lỗi
		super(); // Gọi constructor cha để mở cổng và xuất đối tượng
	}
	@Override // Ghi đè hàm của interface PrimeService
	public boolean isPrime(int n) throws RemoteException{ // Hàm client gọi từ xa
		if(n < 2) return false; // 0, 1 và số âm không phải số nguyên tố
		for(int i = 2 ; (long) i*i <= n; i++) { // Thử chia cho i từ 2 đến căn bậc hai của n; ép long để i*i không tràn int
			if(n % i == 0) return false; // Chia hết cho i nghĩa là có ước khác 1 và n, nên không phải nguyên tố
		}
		return true; // Không tìm thấy ước nào, vậy n là số nguyên tố
	}

}
