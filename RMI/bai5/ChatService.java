package bai5;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatService extends Remote { // Kế thừa Remote để client gọi được từ xa
	String sendMessage(String message) throws RemoteException; // Client gửi tin, nhận về câu phản hồi của server
}


