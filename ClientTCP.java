package BTH15;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
//import java.util.Scanner;


public class ClientTCP {
	public static void main(String[] args) throws Exception {
            //Scanner sc = new Scanner(System.in);
            
                //String message = sc.nextLine();
                // gửi thông điệp đến server
		String Msto = "Hello , I am B21DCAT097 Client";
                
                // Khởi tạo địa chỉ và cổng
		Socket client_socket = new Socket("127.0.0.1" , 6543); 
                
                //gửi thông điệp đến server
		DataOutputStream out_to_server = new DataOutputStream(client_socket.getOutputStream());
                
                //đọc thông điệp từ server gửi đến
		BufferedReader in_from_server = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
                
		out_to_server.writeBytes(Msto + "\n");
		String Msfrom = in_from_server.readLine();
		System.out.println("SERVER: " + Msfrom);
		client_socket.close();
	}
}
