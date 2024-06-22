package BTH15;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ClientTCP1 {
    static String Key = "12345679";
    static SecretKeySpec secretKeySpec = new SecretKeySpec(Key.getBytes(), "DES");
    private static String encryptStr(String s) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException
            ,BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] byteEncrypted = cipher.doFinal(s.getBytes());
        return Base64.getEncoder().encodeToString(byteEncrypted);
    }

    public static void main(String[] args) throws Exception {
        String Mess = "Hello. I am B21DCAT097 Client";
        String MessTo = Mess + "::" + Key;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(MessTo.getBytes(StandardCharsets.UTF_8));
        Socket client_socket = new Socket("127.0.0.1", 6543);
        DataOutputStream out_to_server = new DataOutputStream(client_socket.getOutputStream());
        BufferedReader in_from_server = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
        String go = encryptStr(MessTo);
        System.out.println("SENT: " + go);
        out_to_server.writeBytes(go + "\n");
        String MessFrom = in_from_server.readLine();
        System.out.println("SERVER: " + MessFrom);
        client_socket.close();
    }
}
