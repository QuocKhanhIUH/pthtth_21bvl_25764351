package bai5;
import common.Input;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {

	protected ChatServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String sendMessage(String message) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Client: "+message); // Hiện tin nhắn vừa nhận được ở màn hình server
		return Input.line("Server trả lời"); // Người ngồi ở máy server gõ câu trả lời, rồi gửi ngược về client
	}

}
