package clientPackage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
public class Client {
    private static final String SERVER_IP = "192.168.1.18";   // ← IPv4 de la machine SERVEUR (A)
    private static final int PORT = 1234;
    public static void main(String[] args) {
        try {
            System.out.println("Je suis un client pas encore connecté…");
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(InetAddress.getByName(SERVER_IP), PORT), 5000);
            System.out.println("je suis un client connecté");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            int x;
            do {System.out.print("Entrez un entier (0 pour arrêter) : ");
                x = sc.nextInt();
                out.writeInt(x);
                out.flush();
                if (x != 0) {
                    int resultat = in.readInt();
                    System.out.println("Résultat (x * 5) = " + resultat);}
            } while (x != 0);
            System.out.println("Fin de la communication");
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}