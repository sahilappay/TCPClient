import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        clientFileAsByte();
    }

    public static void clientText() throws Exception{
        try (Socket clientSocket = new Socket("localhost", 6789)){//Autoclosable
            DataOutputStream outToStream = new DataOutputStream(clientSocket.getOutputStream());// melumati gonderir
            Scanner sc =new Scanner(System.in);
            System.out.println("Ener value:");
            String text = sc.nextLine();
            outToStream.writeBytes(text);
            System.out.println(text);
        }
    }

    public static void clientFileAsByte() throws Exception{
        try (Socket clientSocket = new Socket("localhost", 6789)){//Autoclosable
            DataOutputStream outToStream = new DataOutputStream(clientSocket.getOutputStream());// melumati gonderir
            Scanner sc =new Scanner(System.in);
            System.out.println("Ener value:");
            String text = sc.nextLine();
            byte [] bytes = readUsingByte(text);
            outToStream.write(bytes);
            System.out.println(text);
        }
    }

    public static byte[] readUsingByte(String filePath){
        byte[] bytesArr = null;
        try(FileInputStream fileInputStream = new FileInputStream(filePath)){
            File file = new File(filePath);
            bytesArr = new byte[(int) file.length()];
            fileInputStream.read(bytesArr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bytesArr;
    }
}
