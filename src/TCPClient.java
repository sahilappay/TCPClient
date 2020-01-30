
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) throws Exception {
        clientFileAsByteNew();
    }

    public static void clientText() throws Exception {
        try (Socket clientSocket = new Socket("localhost", 6789)) {//Autoclosable
            OutputStream os = clientSocket.getOutputStream();
            DataOutputStream outToStream = new DataOutputStream(os);// melumati gonderir
            Scanner sc = new Scanner(System.in);
            System.out.println("Ener value:");
            String text = sc.nextLine();
            outToStream.write(text.getBytes());
            System.out.println(text);
        }
    }

    public static void clientFileAsByte() throws Exception {
        try (Socket clientSocket = new Socket("localhost", 6789)) {//Autoclosable
            DataOutputStream outToStream = new DataOutputStream(clientSocket.getOutputStream());// melumati gonderir
            Scanner sc = new Scanner(System.in);
            System.out.println("Ener value:");
            String text = sc.nextLine();
            byte[] bytes = readBytes(text);
            outToStream.write(bytes);
            System.out.println(text);
        }
    }

    public static byte[] readBytes(String fileName) throws Exception {/// yarimchiq
        File file = new File(fileName);
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
            return bytes;
        }
    }

    ////////////////NEW METHODS\\\\\\\\\\\\\\\\
    public static void clientFileAsByteNew() throws Exception {
        try (Socket clientSocket = new Socket("localhost", 6789)) {//Autoclosable
            OutputStream os = clientSocket.getOutputStream();
            DataOutputStream outToStream = new DataOutputStream(os);// melumati gonderir
            Scanner sc = new Scanner(System.in);
            System.out.println("Ener value:");
            String file = sc.nextLine();
            byte[] bytes = readBytes(file);
            outToStream.writeInt(bytes.length);
            outToStream.write(bytes);
            System.out.println("Done!");
        }
    }
}
