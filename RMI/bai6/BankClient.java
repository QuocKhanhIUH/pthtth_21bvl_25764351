package bai6;
import common.Input;
import common.RmiClient;

public class BankClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BankAccount acc = RmiClient.lookup("BankService", BankAccount.class);
		while(true) {
			System.out.println("\n==== NGÂN HÀNG ====");
			System.out.println("1. Xem số dư");
			System.out.println("2. Gửi tièn");
			System.out.println("3. Rút tiền");
			System.out.println("0. Thoát");
			String c = Input.line("Chọn: ");
			if(c.equals("0")) break;
			switch(c) {
			case "1":
				System.out.println("Số dư: " +acc.getBalance()); break;
			case "2":
				double dep = Input.real("Số tiền gửi");
				acc.deposit(dep);
				System.out.println("Đã gửi. Số dư mới : "+acc.getBalance()); break;
			case "3":
				double wd = Input.real("Số tiền rút");
				boolean ok = acc.withdraw(wd);
				System.out.println(ok
						? "Rút thành công. Số dư mới: " + acc.getBalance()
						: "Rút tiền thất bại (không đủ tiền hoặc số tiền không hợp lệ)"
						); break;
			default :
				System.out.println("Lựa chọn không hợp lệ");
			}
		}

	}

}
