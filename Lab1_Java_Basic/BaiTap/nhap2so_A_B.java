package BaiTap;
import java.util.Scanner;

public class nhap2so_A_B {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.print("Vui lòng nhập số hạng thứ nhất: ");
		int soA = scanner.nextInt();
		System.out.print("Vui lòng nhập số hạng thứ hai: ");
		int soB = scanner.nextInt();
		int kq = soA + soB;
		System.out.println("Tính tổng [" + soA + " + " + soB + "] = " + kq);

	}

}
