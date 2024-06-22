package BTH15;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
public class ServerTCP1 {
    static String Key = "12345678";
    static SecretKeySpec secretKeySpec = new SecretKeySpec(Key.getBytes(), "DES");
    private static String decryptStr(String s) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException
            , BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] byteEncrypted = Base64.getDecoder().decode(s);
        byte[] byteDecrypted = cipher.doFinal(byteEncrypted);
        return new String(byteDecrypted);
    }
    public static void main(String[] args) throws Exception {
        ServerSocket welcome_socket = new ServerSocket(6543);
        while (true) {
            Socket connection_socket = welcome_socket.accept();
            BufferedReader in_from_client = new BufferedReader(new InputStreamReader(connection_socket.getInputStream()));
            DataOutputStream out_to_client = new DataOutputStream(connection_socket.getOutputStream());
            String MessFrom = in_from_client.readLine();
            System.out.println("CLIENT: " + MessFrom);
            String Mess = decryptStr(MessFrom);
            String MessTo = "";
            if (Mess.split("::")[1].compareTo(Key) == 0) {
                MessTo = "Right key! Server got \"" + Mess.split("::")[0] + "\"";
            } else {
                MessTo = "The received message has lost its integrity.";
            }
            out_to_client.writeBytes(MessTo + "\n");
            return;
        }
    }
}
