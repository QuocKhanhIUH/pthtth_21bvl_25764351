package bai5;
import common.Input;
import common.RmiClient;

public class ChatClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ChatService chat = RmiClient.lookup("ChatService", ChatService.class); // Tìm dịch vụ, nhận stub kiểu ChatService
        System.out.println("Gõ exit để thoát.");
        while(true) { // Lặp mãi để chat nhiều lượt
        	String msg = Input.line("Bạn"); // Nhập tin nhắn
        	if(msg.equalsIgnoreCase("exit")) break; // Gõ exit (không phân biệt hoa thường) thì thoát vòng lặp
        	String reply = chat.sendMessage(msg);// Gửi tin sang server; client đứng chờ đến khi server trả lời xong
        	System.out.println("Server: "+reply); // In câu trả lời nhận được
        }
	}

}
