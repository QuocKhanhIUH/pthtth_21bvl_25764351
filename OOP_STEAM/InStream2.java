package OOP_STEAM;
import static java.lang.Thread.*;
import java.io.IOException;
import java.io.InputStream;
public class InStream2 {

	public static void main(String[] args)  throws InterruptedException , IOException{
		// TODO Auto-generated method stub
		InputStream is = System.in;

		try {
			while(true) {
				if(is.available() > 0) {
					byte[] buffer = new byte[is.available()];
					int bytesRead = is.read(buffer);
					if(bytesRead == -1) {
						break;
					}
					String str = new String(buffer,bytesRead);
					System.out.println(str);
				}
				else {
					System.out.println('.');
					sleep(100);
				}
			}
		}catch (IOException e) {}
		
	}

}
