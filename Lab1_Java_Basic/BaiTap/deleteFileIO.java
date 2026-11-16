package BaiTap;
import java.io.File;

public class deleteFileIO {
	private void deleteFile(String source) {
		//new file
		File file = new File(source);
		//check file exist
		// neu ton tai
		if(file.exists()) {
		System.out.println("file ton tai");
		file.delete();
		System.out.print("xoa file thanh cong");
		}
		else {
		System.out.println("file khong ton tai");
		}
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		deleteFileIO deleteFileIO = new deleteFileIO();
		deleteFileIO.deleteFile("D:/HocJava/demo.txt");

	}

}
