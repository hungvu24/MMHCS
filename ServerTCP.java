package BTH15;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
    public static void main(String[] args) throws Exception {
        //khởi tạo PORT
        ServerSocket welcome_socket = new ServerSocket(6543);
        while (true) {
            Socket connection_socket = welcome_socket.accept(); 
            
            //đọc dữ liệu từ client gửi đến
            BufferedReader in_from_client = new BufferedReader(new InputStreamReader(connection_socket.getInputStream()));
            
            //gửi dữ liệu đến client
            DataOutputStream out_to_client = new DataOutputStream(connection_socket.getOutputStream());
            
            String Msfrom = in_from_client.readLine();
            System.out.println("CLIENT: " + Msfrom);
            
            //gửi thông điệp đến client
            String Msto = "Hello, I am B21DCAT097 Server \n";
            out_to_client.writeBytes(Msto);
            return;
        }
    }
}
