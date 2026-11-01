package bai7;
import common.Input;
import common.RmiClient;

public class GeometryClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		GeometryService g = RmiClient.lookup("GeometryService", GeometryService.class);
		while(true) {
			System.out.println("\n==== TÍNH DIỆN TÍCH =====");
			System.out.println("1. Hình chữ nhật");
			System.out.println("2. Hình tròn");
			System.out.println("3. Hình tam giác");
			System.out.println("0. Thoát");
			String c = Input.line("Chọn: ");
			if(c.equals("0")) break;
			switch(c) {
			case "1": {
				double w = Input.real("Chiều rộng: ");
				double h = Input.real("Chiều cao: ");
				System.out.println("Diện tích hình chữ nhật =" +g.rectangleArea(w, h));
				break;}
			case "2":{
				double r = Input.real("Bán Kính: ");
				System.out.println("Diện tích hình tròn = " + g.circleArea(r));
				break;}
			case "3":{
				double b = Input.real("Đáy: ");
				double h = Input.real("Chiều cao: ");
				System.out.println("Diện tích hình tam giác =" + g.triangleArea(b, h));
				break;
			}
			default: 
				System.out.println("Lựa chọn không hợp lệ!.");
			}
		}

	}

}
