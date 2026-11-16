package BaiTap;
import java.util.Scanner;

public class HiAm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner nameEnter = new Scanner(System.in);
		System.out.print("Bạn tên gì: ");
		String str = nameEnter.nextLine();
		
		Scanner ageEnter = new Scanner(System.in);
		System.out.print("Bạn bao nhiêu tuổi: ");
		String atr = ageEnter.nextLine(); 
		System.out.println("Xin chào, Tên tôi là "+str + ",tôi hiện tại "+atr);

	}

}
